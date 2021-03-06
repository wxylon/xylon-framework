/**
 * Tungsten: An Application Server for uni/cluster.
 * Copyright (C) 2007-2010 Continuent Inc.
 * Contact: tungsten@continuent.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of version 2 of the GNU General Public License as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 * Initial developer(s): Scott Martin
 * Contributor(s): Robert Hodges, Stephane Giron
 */

package com.sync.database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sync.dbms.OneRowChange;


/**
 * Provides a generic implementation for Database interface. Subclasses must
 * supply at least the implementation for method columnToTypeString(), which
 * converts values from java.sql.Types to DBMS-specific column specifications.
 * 
 * @author <a href="mailto:scott.martin@continuent.com">Scott Martin</a>
 * @version 1.0
 */
/*
 * public abstract class Database implements Runnable
 */
public abstract class AbstractDatabase implements Database
{
    private static Logger    logger        = Logger
                                                   .getLogger(AbstractDatabase.class);

    protected DBMS           dbms;
    protected String         dbDriver      = null;
    protected String         dbUri         = null;
    protected String         dbUser        = null;
    protected String         dbPassword    = null;
    protected Connection     dbConn        = null;
    protected boolean        autoCommit    = false;
    protected String         defaultSchema = null;

    protected static boolean driverLoaded  = false;
    protected boolean        connected     = false;

    /**
     * Create a new database instance. To use the database instance you must at
     * minimum set the url, host, and password properties.
     */
    public AbstractDatabase()
    {
    }

    public Connection getConnection()
    {
        return dbConn;
    }

    public DBMS getType()
    {
        return dbms;
    }

    public abstract SqlOperationMatcher getSqlNameMatcher() throws DatabaseException;

    public void setUrl(String dbUri)
    {
        this.dbUri = dbUri;
    }

    public void setUser(String dbUser)
    {
        this.dbUser = dbUser;
    }

    public void setPassword(String dbPassword)
    {
        this.dbPassword = dbPassword;
    }

    public String getPlaceHolder(OneRowChange.ColumnSpec col, Object colValue, String typeDesc)
    {
        return " ? ";
    }

    public boolean nullsBoundDifferently(OneRowChange.ColumnSpec col)
    {
        return false;
    }

    public boolean nullsEverBoundDifferently()
    {
        return false;
    }

    /**
     * Return a properly constructed type specification for the column. Concrete
     * Database subclasses must implement at least this method if no others.
     * 
     * @param c Column for which specification is required
     * @return String containing specification
     */
    abstract protected String columnToTypeString(Column c);

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#connect()
     */
    public synchronized void connect() throws SQLException
    {
        connect(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#connect(boolean)
     */
    public synchronized void connect(boolean binlog) throws SQLException
    {
        if (dbConn == null)
        {
            if (!driverLoaded && dbDriver != null)
            {
                try
                {
                    logger.info("Loading database driver: " + dbDriver);
                    Class.forName(dbDriver);
                    driverLoaded = true;
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Unable to load driver: "
                            + dbDriver, e);
                }
            }

            dbConn = DriverManager.getConnection(dbUri, dbUser, dbPassword);
            connected = (dbConn != null);

            if (connected)
            {
                if (supportsControlSessionLevelLogging())
                {
                    this.controlSessionLevelLogging(!binlog);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#disconnect()
     */
    public synchronized void disconnect()
    {
        if (dbConn != null)
        {
            try
            {
                dbConn.close();
            }
            catch (SQLException e)
            {
                logger.warn("Unable to close connection", e);
            }
            dbConn = null;
            connected = false;
        }
    }

    public DatabaseMetaData getDatabaseMetaData() throws SQLException
    {
        return dbConn.getMetaData();
    }

    /**
     * Returns false by default as only some database types allow schema to be
     * created dynamically.
     * 
     * @see com.continuent.tungsten.replicator.database.Database#supportsCreateDropSchema()
     */
    public boolean supportsCreateDropSchema()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#createSchema(java.lang.String)
     */
    public void createSchema(String schema) throws SQLException
    {
        throw new UnsupportedOperationException(
                "Creating schema is not supported");
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#dropSchema(java.lang.String)
     */
    public void dropSchema(String schema) throws SQLException
    {
        throw new UnsupportedOperationException(
                "Dropping schema is not supported");
    }

    /**
     * Returns false by default as only some database types allow schema to
     * change.
     * 
     * @see com.continuent.tungsten.replicator.database.Database#supportsUseDefaultSchema()
     */
    public boolean supportsUseDefaultSchema()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#useDefaultSchema(java.lang.String)
     */
    public void useDefaultSchema(String schema) throws SQLException
    {
        throw new UnsupportedOperationException(
                "Setting the default schema is not supported");
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#getUseSchemaQuery(java.lang.String)
     */
    public String getUseSchemaQuery(String schema)
    {
        throw new UnsupportedOperationException(
                "Getting the default schema is not supported");
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#supportsControlSessionLevelLogging()
     */
    public boolean supportsControlSessionLevelLogging()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#controlSessionLevelLogging(boolean)
     */
    public void controlSessionLevelLogging(boolean suppressed)
            throws SQLException
    {
        throw new UnsupportedOperationException(
                "Controlling session level logging is not supported");
    }

    /**
     * By default we do not support controlling the timestamp. {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#supportsControlTimestamp()
     */
    public boolean supportsControlTimestamp()
    {
        return false;
    }

    /**
     * Returns a query that can be used to set the timestamp.
     * 
     * @param timestamp Time in milliseconds according to Java standard
     * @see #supportsControlTimestamp()
     */
    public String getControlTimestampQuery(Long timestamp)
    {
        throw new UnsupportedOperationException(
                "Controlling session level logging is not supported");
    }

    /**
     * By default we do not support setting session variables. {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#supportsSessionVariables()
     */
    public boolean supportsSessionVariables()
    {
        return false;
    }

    /**
     * Sets a variable on the current session. {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#setSessionVariable(java.lang.String,
     *      java.lang.String)
     */
    public void setSessionVariable(String name, String value)
            throws SQLException
    {
        throw new UnsupportedOperationException(
                "Session variables are not supported");
    }

    /**
     * Gets a variable on the current session. {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#getSessionVariable(java.lang.String)
     */
    public String getSessionVariable(String name) throws SQLException
    {
        throw new UnsupportedOperationException(
                "Session variables are not supported");
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#execute(java.lang.String)
     */
    public void execute(String SQL) throws SQLException
    {
        Statement sqlStatement = null;
        try
        {
            sqlStatement = dbConn.createStatement();
            logger.debug(SQL);
            sqlStatement.execute(SQL);
        }
        finally
        {
            if (sqlStatement != null)
                sqlStatement.close();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#executeUpdate(java.lang.String)
     */
    public void executeUpdate(String SQL) throws SQLException
    {
        Statement sqlStatement = null;

        try
        {
            sqlStatement = dbConn.createStatement();
            logger.debug(SQL);
            sqlStatement.executeUpdate(SQL);
        }
        finally
        {
            sqlStatement.close();
        }
    }

    private String buildWhereClause(ArrayList<Column> columns)
    {
        String retval = "WHERE ";

        Iterator<Column> i = columns.iterator();
        boolean comma = false;
        while (i.hasNext())
        {
            Column c = i.next();
            if (comma)
                retval += " AND ";
            comma = true;
            retval += assignString(c);
        }
        return retval;
    }

    private String buildCommaAssign(ArrayList<Column> columns)
    {
        StringBuffer retval = new StringBuffer();
        Iterator<Column> i = columns.iterator();
        boolean comma = false;
        while (i.hasNext())
        {
            Column c = i.next();
            if (comma)
                retval.append(", ");
            comma = true;
            retval.append(assignString(c));
        }
        return retval.toString();
    }

    private String buildCommaValues(ArrayList<Column> columns)
    {
        StringBuffer retval = new StringBuffer();
        for (int i = 0; i < columns.size(); i++)
        {
            if (i > 0)
                retval.append(", ");
            retval.append("?");
        }
        return retval.toString();
    }

    private String assignString(Column c)
    {
        return c.getName() + "= ?";
    }

    private void executePrepareStatement(List<Column> columns,
            PreparedStatement statement) throws SQLException
    {
        int bindNo = 1;

        for (Column c : columns)
        {
            statement.setObject(bindNo++, c.getValue());
        }
        // System.out.format("%s (%d binds)\n", SQL, bindNo - 1);
        statement.executeUpdate();
    }

    private void executePrepareStatement(Table table,
            PreparedStatement statement) throws SQLException
    {
        executePrepareStatement(table.getAllColumns(), statement);
    }

    private PreparedStatement executePrepare(List<Column> columns, String SQL,
            boolean keep) throws SQLException
    {
        int bindNo = 1;
        PreparedStatement statement = null;

        try
        {
            statement = dbConn.prepareStatement(SQL);

            for (Column c : columns)
            {
                statement.setObject(bindNo++, c.getValue());
            }
            // System.out.format("%s (%d binds)\n", SQL, bindNo - 1);
            statement.executeUpdate();
        }
        finally
        {
            if (statement != null && !keep)
            {
                statement.close();
                statement = null;
            }
        }
        return statement;
    }

    private PreparedStatement executePrepare(List<Column> columns, String SQL)
            throws SQLException
    {
        return executePrepare(columns, SQL, false);
    }

    private PreparedStatement executePrepare(Table table, String SQL)
            throws SQLException
    {
        return executePrepare(table.getAllColumns(), SQL, false);
    }

    private PreparedStatement executePrepare(Table table, String SQL,
            boolean keep) throws SQLException
    {
        return executePrepare(table.getAllColumns(), SQL, keep);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#insert(com.continuent.tungsten.replicator.database.Table)
     */
    public void insert(Table table) throws SQLException
    {
        String SQL = "";
        PreparedStatement statement = null;
        boolean caching = table.getCacheStatements();

        if (caching && (statement = table.getStatement(Table.INSERT)) != null)
        {
            executePrepareStatement(table, statement);
            return;
        }
        else
        {
            SQL += "INSERT INTO " + table.getSchema() + "." + table.getName()
                    + " VALUES (";
            SQL += buildCommaValues(table.getAllColumns());
            SQL += ")";
        }

        statement = executePrepare(table, SQL, caching);
        if (caching)
            table.setStatement(Table.INSERT, statement);
    }

    public void update(Table table, ArrayList<Column> whereClause,
            ArrayList<Column> values) throws SQLException
    {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(table.getSchema());
        sb.append(".");
        sb.append(table.getName());
        sb.append(" SET ");
        sb.append(buildCommaAssign(values));
        if (whereClause != null)
        {
            sb.append(" ");
            sb.append(buildWhereClause(whereClause));
        }
        String SQL = sb.toString();

        ArrayList<Column> allColumns = new ArrayList<Column>(values);
        if (whereClause != null)
        {
            allColumns.addAll(whereClause);
        }
        this.executePrepare(allColumns, SQL);
    }

    public boolean supportsReplace()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#replace(com.continuent.tungsten.replicator.database.Table)
     */
    public void replace(Table table) throws SQLException
    {
        if (supportsReplace())
        {
            String SQL = "";
            SQL += "REPLACE INTO " + table.getSchema() + "." + table.getName()
                    + " VALUES (";
            SQL += buildCommaValues(table.getAllColumns());
            SQL += ")";

            executePrepare(table, SQL);
        }
        else
        {
            try
            {
                delete(table);
            }
            catch (SQLException e)
            {
            }
            insert(table);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#delete(com.continuent.tungsten.replicator.database.Table)
     */
    public void delete(Table table) throws SQLException
    {
        String SQL = "DELETE FROM " + table.getSchema() + "." + table.getName()
                + " ";
        SQL += buildWhereClause(table.getPrimaryKey().getColumns());

        executePrepare(table.getPrimaryKey().getColumns(), SQL);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#prepareStatement(java.lang.String)
     */
    public PreparedStatement prepareStatement(String statement)
            throws SQLException
    {
        logger.debug("prepareStatement" + statement);
        return dbConn.prepareStatement(statement);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#createStatement()
     */
    public Statement createStatement() throws SQLException
    {
        logger.debug("createStatement");
        return dbConn.createStatement();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#commit()
     */
    public void commit() throws SQLException
    {
        logger.debug("commit");
        dbConn.commit();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#rollback()
     */
    public void rollback() throws SQLException
    {
        logger.debug("rollback");
        dbConn.rollback();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#setAutoCommit(boolean)
     */
    public void setAutoCommit(boolean autoCommit) throws SQLException
    {
        this.autoCommit = autoCommit;
        if (logger.isDebugEnabled())
            logger.debug("setAutoCommit = " + autoCommit);
        if (dbConn.getAutoCommit() != autoCommit)
            dbConn.setAutoCommit(autoCommit);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#createTable(com.continuent.tungsten.replicator.database.Table,
     *      boolean)
     */
    public void createTable(Table t, boolean replace) throws SQLException
    {
        boolean comma = false;

        if (replace)
            dropTable(t);

        String SQL = "CREATE TABLE " + t.getSchema() + "." + t.getName() + " (";

        Iterator<Column> i = t.getAllColumns().iterator();
        while (i.hasNext())
        {
            Column c = i.next();
            SQL += (comma ? ", " : "") + c.getName() + " "
                    + columnToTypeString(c)
                    + (c.isNotNull() ? " NOT NULL" : "");
            comma = true;
        }
        Iterator<Key> j = t.getKeys().iterator();

        while (j.hasNext())
        {
            Key key = j.next();
            SQL += ", ";
            switch (key.getType())
            {
                case Key.Primary :
                    SQL += "PRIMARY KEY (";
                    break;
                case Key.Unique :
                    SQL += "UNIQUE (";
                    break;
                case Key.NonUnique :
                    SQL += "KEY (";
                    break;
            }
            i = key.getColumns().iterator();
            comma = false;
            while (i.hasNext())
            {
                Column c = i.next();
                SQL += (comma ? ", " : "") + c.getName();
                comma = true;
            }
            SQL += ")";
        }
        SQL += ")";

        // Create the table.
        execute(SQL);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#dropTable(com.continuent.tungsten.replicator.database.Table)
     */
    public void dropTable(Table table)
    {
        String SQL = "DROP TABLE " + table.getSchema() + "." + table.getName()
                + " ";

        try
        {
            execute(SQL);
        }
        catch (SQLException e)
        {
            logger.debug("Unable to drop table; this may be expected", e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#close()
     */
    public void close()
    {
        disconnect();
    }

    public int nativeTypeToJavaSQLType(int nativeType) throws SQLException
    {
        return nativeType;
    }

    public int javaSQLTypeToNativeType(int javaSQLType) throws SQLException
    {
        return javaSQLType;
    }

    public Table findTable(int tableID) throws SQLException
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#findTable(int,
     *      java.lang.String)
     */
    public Table findTable(int tableID, String scn) throws SQLException
    {
        return null;
    }

    /**
     * This function should be implemented in concrete class.
     * 
     * @param md DatabaseMetaData object
     * @param schemaName schema name
     * @param tableName table name
     * @return ResultSet as produced by DatabaseMetaData.getColumns() for a
     *         given schema and table
     * @throws SQLException
     */
    public abstract ResultSet getColumnsResultSet(DatabaseMetaData md,
            String schemaName, String tableName) throws SQLException;

    /**
     * This function should be implemented in concrete class.
     * 
     * @param md DatabaseMetaData object
     * @param schemaName schema name
     * @param tableName table name
     * @return ResultSet as produced by DatabaseMetaData.getPrimaryKeys() for a
     *         given schema and table
     * @throws SQLException
     */
    protected abstract ResultSet getPrimaryKeyResultSet(DatabaseMetaData md,
            String schemaName, String tableName) throws SQLException;

    /**
     * This function should be implemented in concrete class.
     * 
     * @param md DatabaseMetaData object
     * @param schemaName schema name
     * @param baseTablesOnly If true, return only base tables, not catalogs or
     *            views
     * @return ResultSet as produced by DatabaseMetaData.getTables() for a given
     *         schema
     * @throws SQLException
     */
    protected abstract ResultSet getTablesResultSet(DatabaseMetaData md,
            String schemaName, boolean baseTablesOnly) throws SQLException;

    public Table findTable(String schemaName, String tableName)
            throws SQLException
    {
        DatabaseMetaData md = this.getDatabaseMetaData();
        Table table = null;

        ResultSet rsc = getColumnsResultSet(md, schemaName, tableName);
        if (rsc.isBeforeFirst())
        {
            // found columns
            Map<String, Column> cm = new HashMap<String, Column>();
            table = new Table(schemaName, tableName);
            while (rsc.next())
            {
                String colName = rsc.getString("COLUMN_NAME");
                int colType = rsc.getInt("DATA_TYPE");
                long colLength = rsc.getLong("COLUMN_SIZE");
                boolean isNotNull = rsc.getInt("NULLABLE") == DatabaseMetaData.columnNoNulls;
                String valueString = rsc.getString("COLUMN_DEF");

                Column column = new Column(colName, colType, colLength,
                        isNotNull, valueString);
                column.setPosition(rsc.getInt("ORDINAL_POSITION"));
                table.AddColumn(column);
                cm.put(column.getName(), column);
            }

            ResultSet rsk = getPrimaryKeyResultSet(md, schemaName, tableName);
            if (rsk.isBeforeFirst())
            {
                // primary key found
                Key pKey = new Key(Key.Primary);
                while (rsk.next())
                {
                    String colName = rsk.getString("COLUMN_NAME");
                    Column column = cm.get(colName);
                    pKey.AddColumn(column);
                }
                table.AddKey(pKey);
            }
            rsk.close();
        }
        rsc.close();

        return table;
    }

    /**
     * Implement ability to fetch tables. {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#getTables(java.lang.String,
     *      boolean)
     */
    public ArrayList<Table> getTables(String schemaName, boolean baseTablesOnly)
            throws SQLException
    {
        DatabaseMetaData md = this.getDatabaseMetaData();
        ArrayList<Table> tables = new ArrayList<Table>();

        try
        {
            ResultSet rst = getTablesResultSet(md, schemaName, baseTablesOnly);
            if (rst.isBeforeFirst())
            {
                while (rst.next())
                {
                    String tableName = rst.getString("TABLE_NAME");
                    Table table = findTable(schemaName, tableName);
                    if (table != null)
                    {
                        tables.add(table);
                    }
                }
            }
            rst.close();
        }
        finally
        {
        }

        return tables;
    }

    public void createTable(Table table, boolean replace,
            String tungstenTableType) throws SQLException
    {
        createTable(table, replace);
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#prepareOptionSetStatement(java.lang.String,
     *      java.lang.String)
     */
    public String prepareOptionSetStatement(String optionName,
            String optionValue)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#getBlobAsBytes(ResultSet,
     *      int)
     */
    public byte[] getBlobAsBytes(ResultSet resultSet, int column)
            throws SQLException
    {
        Blob blob = resultSet.getBlob(column);
        return blob.getBytes(1L, (int) blob.length());
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.continuent.tungsten.replicator.database.Database#getDatabaseObjectName(java.lang.String)
     */
    public String getDatabaseObjectName(String name)
    {
        return name;
    }

}
