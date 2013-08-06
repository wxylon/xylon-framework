package com.sync.applier;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.sync.database.Database;
import com.sync.database.DatabaseFactory;
import com.sync.dbms.DBMSData;
import com.sync.dbms.StatementData;
import com.sync.event.DBMSEvent;
import com.sync.event.ReplOption;
import com.sync.event.ReplOptionParams;


/**
 * 
 * @author ben
 *
 */
public class MysqlApplier implements Applier {

	static Logger logger = Logger.getLogger(MysqlApplier.class);

	protected Database conn = null;
	protected Statement statement = null;
	protected Pattern ignoreSessionPattern = null;

	private boolean transactionStarted = false;

	// Values of schema and timestamp which are buffered to avoid
	// unnecessary commands on the SQL connection.
	protected String currentSchema = null;
	protected long currentTimestamp = -1;

	// Statistics.
	protected long eventCount = 0;
	protected long commitCount = 0;

    // Options.
    private String           url          = null;
    private String           login        = null;
    private String           password     = null;
    private String			 schema 	  = null;

	protected HashMap<String, String> currentOptions;
	
	/**
	 * Maximum length of SQL string to log in case of an error. This is needed
	 * because some statements may be very large. TODO: make this configurable
	 * via replicator.properties
	 */
	private int maxSQLLogLength = 1000;
	
	public MysqlApplier (String url, String login, String password, String schema) {
		this.url = url;
		this.login = login;
		this.password = password;
		
		this.schema = schema;
	}

	/* (non-Javadoc)
	 * @see com.sync.applier.Applier#connect()
	 */
	@Override
	public void connect() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new Exception("Unable to load driver: " , e);
		}
	
		// Create the database.
		conn = DatabaseFactory.createDatabase(url, login, password);
		conn.connect(false);
		statement = conn.createStatement();
	}

	/**
	 * startTransaction starts a new transaction.
	 * 
	 * @throws SQLException
	 *             if a problem occurs.
	 */
	private void startTransaction() throws SQLException {
		transactionStarted = true;
		conn.setAutoCommit(false);
	}

	private void commitTransaction() throws SQLException {
		try {
			conn.commit();
			commitCount++;
		} catch (SQLException e) {
			logger.error("Failed to commit : " + e);
			throw e;
		} finally {
			transactionStarted = false;
			// Switch connection back to autocommit
			conn.setAutoCommit(true);
		}
	}

	private void rollbackTransaction() throws SQLException {
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("Failed to rollback : " + e);
			throw e;
		} finally {
			// Switch connection back to autocommit
			conn.setAutoCommit(true);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sync.applier.Applier#disconnect()
	 */
	@Override
	public void disconnect() {
		if (conn != null) {
			conn.disconnect();
		}
	}

	/* (non-Javadoc)
	 * @see com.sync.applier.Applier#apply(com.sync.event.DBMSEvent, boolean)
	 */
	@Override
	public void apply(DBMSEvent event, boolean doCommit) throws SQLException {
		
		boolean transactionCommitted = false;
		try {
			if (!transactionStarted) {
				startTransaction();
			}
		} catch (SQLException e) {
			throw new SQLException("Failed to start new transaction", e);
		}
		
		try {
			ArrayList<DBMSData> data = event.getData();
			for (DBMSData dataElem : data) {
				if (dataElem instanceof StatementData) {
					applyStatementData((StatementData) dataElem);
				}
			}
			if (doCommit) {
				commitTransaction();
				transactionCommitted = true;
			}
		} catch (SQLException e) {
			if (!transactionCommitted) {
				try {
					rollbackTransaction();
				} catch (SQLException e1) {
					e1.initCause(e);
					e = e1;
				}
			}
			throw new SQLException(e);
		}

		// Update statistics.
		this.eventCount++;
		if (logger.isDebugEnabled() && eventCount % 20000 == 0)
			logger.debug("Apply statistics: events=" + eventCount + " commits="+ commitCount);
	}

	protected void applyStatementData(StatementData data)
			throws SQLException {
		/*
		 * TODO: This was mentioned in code review about batch updates: if one
		 * statement fails you may get a situation where a partial batch commits
		 * and statements after the failure will not be recorded. Need to
		 * investigate if this is the case and fix applying code below
		 * accordingly.
		 */
		try {
			int[] updateCount = null;
			//String schema = data.getDefaultSchema();
			if (schema == null) {
				schema = data.getDefaultSchema();
			}
			Long timestamp = data.getTimestamp();
			List<ReplOption> options = data.getOptions();

			applyUseSchema(schema);
			applySetTimestamp(timestamp);
			applySessionVariables(options);

			if (data.getQuery() != null)
				statement.addBatch(data.getQuery());
			else
				statement.addBatch(new String(data.getQueryAsBytes()));

			statement.setEscapeProcessing(false);
			try {
				updateCount = statement.executeBatch();
			} catch (SQLWarning e) {
				String msg = "While applying SQL event:\n" + data.toString() + "\nWarning: " + e.getMessage();
				logger.warn(msg);
				updateCount = new int[1];
				updateCount[0] = statement.getUpdateCount();
			} catch (SQLException e) {
				if (data.getErrorCode() == 0) {
					SQLException sqlException = new SQLException("Statement failed on slave but succeeded on master");
					sqlException.initCause(e);
					throw sqlException;
				}
				// Check if the query produced the same error on master
				if (e.getErrorCode() == data.getErrorCode()) {
					logger.info("Ignoring statement failure as it also failed "
							+ "on master with the same error code ("
							+ e.getErrorCode() + ")");
				} else {
					SQLException sqlException = new SQLException(
							"Statement failed on slave with error code "
									+ e.getErrorCode()
									+ " but failed on master with a different one ("
									+ data.getErrorCode() + ")");
					sqlException.initCause(e);
					throw sqlException;
				}
			}

			while (statement.getMoreResults()) {
				statement.getResultSet();
			}
			statement.clearBatch();

			if (logger.isDebugEnabled() && updateCount != null) {
				int cnt = 0;
				for (int i = 0; i < updateCount.length; cnt += updateCount[i], i++)
					;

				logger.debug("Applied event (update count " + cnt + "): "+ data.toString());
			}
		} catch (SQLException e) {
			logFailedStatementSQL(data.getQuery());
			throw new SQLException(e);
		}
	}

	/**
	 * applySetUseSchema adds to the batch the query used to change the current
	 * schema where queries should be executed, if needed and if possible (if
	 * the database support such a feature)
	 * 
	 * @param schema
	 *            the schema to be used
	 * @throws SQLException
	 *             if an error occurs
	 */
	protected void applyUseSchema(String schema) throws SQLException {
		if (schema != null && schema.length() > 0
				&& !schema.equals(this.currentSchema)) {
			currentSchema = schema;
			if (conn.supportsUseDefaultSchema())
				statement.addBatch(conn.getUseSchemaQuery(schema));
		}
	}

	/**
	 * applySetTimestamp adds to the batch the query used to change the server
	 * timestamp, if needed and if possible (if the database support such a
	 * feature)
	 * 
	 * @param timestamp
	 *            the timestamp to be used
	 * @throws SQLException
	 *             if an error occurs
	 */
	protected void applySetTimestamp(Long timestamp) throws SQLException {
		if (timestamp != null && conn.supportsControlTimestamp()) {
			if (timestamp.longValue() != currentTimestamp) {
				currentTimestamp = timestamp.longValue();
				statement.addBatch(conn.getControlTimestampQuery(timestamp));
			}
		}
	}

	/**
	 * applyOptionsToStatement adds to the batch queries used to change the
	 * connection options, if needed and if possible (if the database support
	 * such a feature)
	 * 
	 * @param options
	 * @return true if any option changed
	 * @throws SQLException
	 */
	protected boolean applySessionVariables(List<ReplOption> options)
			throws SQLException {
		boolean sessionVarChange = false;

		if (options != null && conn.supportsSessionVariables()) {
			if (currentOptions == null)
				currentOptions = new HashMap<String, String>();

			for (ReplOption statementDataOption : options) {
				// if option already exists and have the same value, skip it
				// Otherwise, we need to set it on the current connection
				String optionName = statementDataOption.getOptionName();
				String optionValue = statementDataOption.getOptionValue();
				// Ignore internal Tungsten options.
				if (optionName.startsWith(ReplOptionParams.INTERNAL_OPTIONS_PREFIX))
					continue;

				// If we are ignoring this option, just continue.
				if (ignoreSessionPattern != null) {
					if (ignoreSessionPattern.matcher(optionName).matches()) {
						if (logger.isDebugEnabled())
							logger.debug("Ignoring session variable: "	+ optionName);
						continue;
					}
				}

				if (optionName.equals(StatementData.CREATE_OR_DROP_DB)) {
					// Clearing current used schema, so that it will force a new
					// "use" statement to be issued for the next query
					currentSchema = null;
					continue;
				}

				String currentOptionValue = currentOptions.get(optionName);
				if (currentOptionValue == null
						|| !currentOptionValue.equalsIgnoreCase(optionValue)) {
					String optionSetStatement = conn.prepareOptionSetStatement(
							optionName, optionValue);
					if (optionSetStatement != null) {
						if (logger.isDebugEnabled())
							logger.debug("Issuing " + optionSetStatement);
						statement.addBatch(optionSetStatement);
					}
					currentOptions.put(optionName, optionValue);
					sessionVarChange = true;
				}
			}
		}
		return sessionVarChange;
	}

	/**
	 * Logs SQL into error log stream. Trims the message if it exceeds
	 * maxSQLLogLength.
	 * 
	 * @see #maxSQLLogLength
	 * @param sql
	 *            the sql statement to be logged
	 */
	protected void logFailedStatementSQL(String sql) {
		try {
			String log = "Statement failed: " + sql;
			if (log.length() > maxSQLLogLength)
				log = log.substring(0, maxSQLLogLength);
			logger.error(log);
		} catch (Exception e) {
			logger.debug("logFailedStatementSQL failed to log, because: "+ e.getMessage());
		}
	}
}
