package com.sync.extractor.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.sync.commons.mysql.MySQLConstants;
import com.sync.commons.mysql.MySQLIOs;
import com.sync.commons.mysql.MySQLPacket;
import com.sync.database.MySQLOperationMatcher;
import com.sync.database.SqlOperation;
import com.sync.database.SqlOperationMatcher;
import com.sync.dbms.DBMSData;
import com.sync.dbms.RowIdData;
import com.sync.dbms.StatementData;
import com.sync.event.DBMSEvent;
import com.sync.event.ReplOption;
import com.sync.event.ReplOptionParams;

/**
 * Mysql binlog 抽取
 * 
 * @author ben
 *
 */
public class MySQLExtractor
{
    private static Logger    logger       = Logger.getLogger(MySQLExtractor.class);
    
    private boolean          useBytesForStrings      = true;

    // Options.
    private String           url          = null;
    private String           login        = null;
    private String           password     = null;
    
    // Database connection information.
    private Connection       conn;
    private InputStream      input        = null;
    private OutputStream     output       = null;
    
    public static LinkedBlockingQueue<DBMSEvent> _QUEUE = new LinkedBlockingQueue<DBMSEvent>();
    public static String     					_BINLOG_FILE       = "mysql-bin.000006";
    public static long       					_BINLOG_OFFSET     = 4;

    
    // SQL parser. 
    SqlOperationMatcher sqlMatcher = new MySQLOperationMatcher(); 

    public MySQLExtractor(String url, String login, String password) {
    	this.url = url;
    	this.login = login;
    	this.password = password;
    }

	/**
	 * Connect to database and set up relay log transfer. If successful we are
	 * ready to transfer binlogs.
	 */
	public void connect() throws ExtractorException {
		try {
			logger.info("Connecting to master MySQL server: url=" + url);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, login, password);
		} catch (ClassNotFoundException e) {
			throw new ExtractorException("Unable to load JDBC driver", e);
		} catch (SQLException e) {
			throw new ExtractorException("Unable to connect", e);
		}

		// Get underlying IO streams for network communications.
		try {
			MySQLIOs io = MySQLIOs.getMySQLIOs(conn);
			input = io.getInput();
			output = io.getOutput();
		} catch (Exception e) {
			throw new ExtractorException("Unable to access IO streams for connection", e);
		}

		// Ask for binlog data.
		try {
			logger.info("Requesting binlog data from master: " + _BINLOG_FILE + ":"+ _BINLOG_OFFSET);
			sendBinlogDumpPacket(output);
		} catch (IOException e) {
			throw new ExtractorException(
					"Error sending request to dump binlog", e);
		}
	}
    
    boolean inTransaction = false;
    boolean autocommitMode = true;
    Timestamp startTime = null;
    long sessionId = 0;
    String defaultDb = null;
    int serverId = -1;
    
    ArrayList<DBMSData> dataArray = new ArrayList<DBMSData>();
    LinkedList<ReplOption> savedOptions = new LinkedList<ReplOption>();   
    DBMSEvent dbmsEvent = null;
    
    private void resetVariables() {
    	inTransaction = false;
    	autocommitMode = true;
    	startTime = null;
    	sessionId = 0;
    	defaultDb = null;
    	serverId = -1;
    	dbmsEvent = null;
    	savedOptions = new LinkedList<ReplOption>();
    	dataArray = new ArrayList<DBMSData>();
    }
	
    public void processEvent() throws Exception,
            InterruptedException
    {
    	FormatDescriptionLogEvent description_event = new FormatDescriptionLogEvent(4);
    	while (true) {
    		
	        MySQLPacket packet = MySQLPacket.readPacket(input);
	        int length = packet.getDataLength();
	        int number = packet.getPacketNumber();
	        short type = packet.getUnsignedByte();
	
	        if (logger.isDebugEnabled()) {
	            logger.debug("Received packet: number=" + number + " length=" + length + " type=" + type);
	        }
	
	        // Switch on the type.
	        switch (type)
	        {
	            case 0 :
                    // Read the header. Note we can only handle V4 headers (5.0+).
                    long timestamp = packet.getUnsignedInt32();
                    int typeCode = packet.getUnsignedByte();
                    serverId = (int) packet.getUnsignedInt32();
                    long eventLength = packet.getUnsignedInt32();
                    long nextPosition = packet.getUnsignedInt32();
                    int flags = packet.getUnsignedShort();

                    if (logger.isDebugEnabled())
                    {
                        StringBuffer sb = new StringBuffer("Reading binlog event:");
                        sb.append(" timestamp=").append(timestamp);
                        sb.append(" type_code=").append(typeCode);
                        sb.append(" server_id=").append(serverId);
                        sb.append(" event_length=").append(eventLength);
                        sb.append(" next_position=").append(nextPosition);
                        sb.append(" flags=").append(flags);
                        logger.debug(sb.toString());
                    }

                    try {        
                          byte[] fullEvent = new byte[description_event.commonHeaderLength + (int)eventLength];
                          System.arraycopy(packet.getByteBuffer(), 5, fullEvent, 0, packet.getByteBuffer().length -5);
	        			  LogEvent logEvent = LogEvent.readLogEvent(true, fullEvent, (int)eventLength, description_event, true);	
	        			  
						  if (logEvent == null) {
							  logger.debug("Unknown binlog field, skipping");
							  continue;
						  }
	
	                      if (serverId == -1)
	                          serverId = logEvent.serverId;
	
	                      if (startTime == null)
	                          startTime = logEvent.getWhen();
	
	                      boolean doCommit = false;
	
	                      if (logEvent.getClass() == QueryLogEvent.class)
	                      {
	                          QueryLogEvent event = (QueryLogEvent) logEvent;
	                          String queryString = event.getQuery();
	                          StatementData statement = new StatementData(queryString);
	
	                          // Extract the charset name if it can be found.
	                          String charsetName = event.getCharsetName();
	                          if (charsetName != null)
	                              statement.addOption(ReplOptionParams.JAVA_CHARSET_NAME, event.getCharsetName());
	                          if (logger.isDebugEnabled())
	                              logger.debug("Query extracted: " + queryString + " charset=" + charsetName);
	
	                          // Parse for SQL metadata and add to the statement. 
	                          String query;
	
	                          if (!useBytesForStrings)
	                              query = queryString;
	                          else
	                          {
	                              // Translate only a few bytes, in order to eventually
	                              // detect some keywords.
	                              int len = Math.min(event.getQueryAsBytes().length, 200);
	                              if (charsetName == null)
	                                  query = new String(event.getQueryAsBytes(), 0, len);
	                              else
	                                  query = new String(event.getQueryAsBytes(), 0, len, charsetName);
	                          }
	                          SqlOperation sqlOperation = sqlMatcher.match(query);
	                          statement.setParsingMetadata(sqlOperation);
	
	                          // We must commit on DDLs and the like except for BEGIN or
	                          // START TRANSACTION, since they start new transaction at
	                          // the same time
	                          doCommit = !inTransaction || sqlOperation.isAutoCommit();
	                          if (sqlOperation.getOperation() == SqlOperation.BEGIN)
	                          {
	                              inTransaction = true;
	                              doCommit = false;
	                              // This a a BEGIN statement : buffer session variables
	                              // for following row events if any and skip it                        
	                              
	                              /* Adding statement options */
	                              savedOptions.add(new ReplOption("autocommit", event.getAutocommitFlag()));
	                              savedOptions.add(new ReplOption("sql_auto_is_null", event.getAutoIsNullFlag()));
	                              savedOptions.add(new ReplOption("foreign_key_checks", event.getForeignKeyChecksFlag()));
	                              savedOptions.add(new ReplOption("unique_checks", event.getUniqueChecksFlag()));
	                              savedOptions.add(new ReplOption("sql_mode", event.getSqlMode()));
	                              savedOptions.add(new ReplOption("character_set_client", String.valueOf(event.getClientCharsetId())));
	                              savedOptions.add(new ReplOption("collation_connection", String.valueOf(event.getClientCollationId())));
	                              savedOptions.add(new ReplOption("collation_server", String.valueOf(event.getServerCollationId())));
	                              
	                              if (event.getAutoIncrementIncrement() >= 0)
	                                  savedOptions.add(new ReplOption("auto_increment_increment", String.valueOf(event.getAutoIncrementIncrement())));
	
	                              if (event.getAutoIncrementOffset() >= 0)
	                                  savedOptions.add(new ReplOption("auto_increment_offset", String.valueOf(event.getAutoIncrementOffset())));
	                              
	                              continue;
	                          }
	
	                          if (sqlOperation.getOperation() == SqlOperation.COMMIT) {
	                              // This is a COMMIT statement : dropping it for now
	                              // Temporary workaround for TREP-243
	                              doCommit = true;
	                              inTransaction = !autocommitMode;
	                          } else {
	                              // some optimisation: it makes sense to check for
	                              // 'CREATE DATABASE' only if we know that it is not
	                              // regular DML - this is a fix for TREP-52 - attempt
	                              // to use DB whis hasn't been created yet.
	                              boolean isCreateOrDropDB = sqlOperation.getObjectType() == SqlOperation.SCHEMA;
	                              boolean prependUseDb = !(sqlOperation.isAutoCommit() && isCreateOrDropDB);
	
	                              if (defaultDb == null)
	                              {
	                                  // first query in transaction
	                                  sessionId = event.getSessionId();
	                                  if (prependUseDb)
	                                  {
	                                      defaultDb = event.getDefaultDb();
	                                      statement.setDefaultSchema(defaultDb);
	                                  }
	                              }
	                              else
	                              {
	                                  // check that session ID is the same
	                                  assert (sessionId == event.getSessionId());
	                                  // check if default database has changed
	                                  String newDb = event.getDefaultDb();
	                                  if (!defaultDb.equals(newDb) && prependUseDb)
	                                  {
	                                      defaultDb = newDb;
	                                      statement.setDefaultSchema(newDb);
	                                  }
	                              }
	                              if (isCreateOrDropDB)
	                                  statement.addOption(StatementData.CREATE_OR_DROP_DB, "");
	
	                              statement.setTimestamp(event.getWhen().getTime());
	                              if (!useBytesForStrings)
	                              {
	                                  statement.setQuery(queryString);
	                              }
	                              else
	                              {
	                                  byte[] bytes = event.getQueryAsBytes();
	                                  statement.setQuery(bytes);
	                              }
	
	                              /* Adding statement options */
	                              statement.addOption("autocommit", event.getAutocommitFlag());
	                              statement.addOption("sql_auto_is_null", event.getAutoIsNullFlag());
	                              statement.addOption("foreign_key_checks", event.getForeignKeyChecksFlag());
	                              statement.addOption("unique_checks", event.getUniqueChecksFlag());
	
	                              if (event.getAutoIncrementIncrement() >= 0)
	                                  statement.addOption("auto_increment_increment", String.valueOf(event.getAutoIncrementIncrement()));
	
	                              if (event.getAutoIncrementOffset() >= 0)
	                                  statement.addOption("auto_increment_offset", String.valueOf(event.getAutoIncrementOffset()));
	
	                              /* Adding statement sql_mode */
	                              statement.addOption("sql_mode", event.getSqlMode());
	
	                              /* Adding character set / collation information */
	                              statement.addOption("character_set_client", String.valueOf(event.getClientCharsetId()));
	                              statement.addOption("collation_connection", String.valueOf(event.getClientCollationId()));
	                              statement.addOption("collation_server", String.valueOf(event.getServerCollationId()));
	                              statement.setErrorCode(event.getErrorCode());
	
	                              dataArray.add(statement);
	                          }
	                      } else if (logEvent.getClass() == UserVarLogEvent.class) {
	                          logger.debug("USER_VAR_EVENT detected: "+ ((UserVarLogEvent) logEvent).getQuery());
	                          dataArray.add(new StatementData(((UserVarLogEvent) logEvent).getQuery()));
	
	                      } else if (logEvent.getClass() == RandLogEvent.class) {
	                          logger.debug("RAND_EVENT detected: "+ ((RandLogEvent) logEvent).getQuery());
	                          dataArray.add(new StatementData(((RandLogEvent) logEvent).getQuery()));
	                      } else if (logEvent.getClass() == IntvarLogEvent.class) {
	                          logger.debug("INTVAR_EVENT detected, value: " + ((IntvarLogEvent) logEvent).getValue());
	                          dataArray.add(new RowIdData(((IntvarLogEvent) logEvent).getValue()));
	                      } else if (logEvent.getClass() == XidLogEvent.class) {
	                          logger.debug("Commit extracted: " + ((XidLogEvent) logEvent).getXid());
	                          // If there's nothing to commit, just ignore.
	                          // Should happen for InnoDB tables in AUTOCOMMIT mode.
	                          if (!dataArray.isEmpty()) {
	                              doCommit = true;
	                          }
	                          // It seems like there's always explicit COMMIT event if
	                          // transaction is implicitely committed,
	                          // but does transaction start implicitely?
	                          inTransaction = !autocommitMode;
	                      } else if (logEvent.getClass() == StopLogEvent.class) {	                    	  
		                    	String[] arr = _BINLOG_FILE.split("\\.");
		              			String binlogPrefix = arr[0];
		              			String binlogIndex = arr[1];
		              			int index = Integer.parseInt(binlogIndex);
		              			index++;
		              			if (index < 10)
		              				binlogIndex = "00000" + index;
		              			else if (index > 10 && index < 100)
		              				binlogIndex = "0000" + index;
		              			else
		              				binlogIndex = "000" + index;
		              			String stop_nextBinlog = binlogPrefix + "." + binlogIndex;
		              			_BINLOG_FILE = stop_nextBinlog;
		              			_BINLOG_OFFSET = logEvent.logPos;
	  
	                            logger.debug("Stop event extracted: ");
	                            String stopEventId = getDBMSEventId(_BINLOG_FILE, logEvent.logPos, sessionId);
	                            logger.info("Skipping over server stop event in log: " + stopEventId);
	                            
	                      } else if (logEvent.getClass() == RotateLogEvent.class) {
	                    	  
	                          String newBinlogFilename = ((RotateLogEvent) logEvent).getNewBinlogFilename();
	                          _BINLOG_FILE = newBinlogFilename;
	                          _BINLOG_OFFSET = logEvent.logPos;
	                          logger.debug("Rotate log event: new binlog="+ newBinlogFilename);
	
						  } else {
								logger.debug("got binlog event: " + logEvent);
						  }
	
	                      if (doCommit)
	                      {
	                          logger.debug("Performing commit processing in extractor");
	                          String eventId = getDBMSEventId(_BINLOG_FILE, logEvent.logPos, sessionId);
	                          dbmsEvent = new DBMSEvent(eventId, dataArray, startTime);

	                          logger.debug("Clearing Table Map events");
	                          savedOptions.clear();
	                      }
	                   
	                      if (dbmsEvent != null)
	                      {
	                          dbmsEvent.addMetadataOption(ReplOptionParams.SERVER_ID, String.valueOf(serverId));   
	                          _QUEUE.put(dbmsEvent);
	                          resetVariables();
	                          break;
	                      }
                    } catch (MySQLExtractException e) {
                    	e.printStackTrace();
                    }
	                break;
	            case 0xFE :
	                // Indicates end of stream. It's not clear when this would be sent.
	                throw new Exception("EOF packet received");
	            case 0xFF :
	                // Indicates an error, for example trying to restart at wrong binlog offset.
	                int errno = packet.getShort();
	                packet.getByte();
	                String sqlState = packet.getString(5);
	                String errMsg = packet.getString();
	                String msg = "Error packet received: errno=" + errno + " sqlstate=" + sqlState + " error=" + errMsg;
	                throw new Exception(msg);
	            default :
	                // Should not happen.
	                throw new Exception("Unexpected response while fetching binlog data: packet=" + packet.toString());
	        }
	        
	        if (_QUEUE.size() > 300)
	        	Thread.sleep(1000);
    	}
    }

	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn("Unable to close connection", e);
			}
		}
	}
	
    private static String getDBMSEventId(String fileName, long position, long sessionId) {
    	/*String binlogNumber = fileName.substring(fileName.lastIndexOf('.') + 1);
        return String.format("%s:%s;%d", binlogNumber, position, sessionId);*/	
    	
    	return String.format("%s;%d", fileName, position);
    }

	private void sendBinlogDumpPacket(OutputStream out) throws IOException {
		MySQLPacket packet = new MySQLPacket(200, (byte) 0);
		packet.putByte((byte) MySQLConstants.COM_BINLOG_DUMP);
		packet.putInt32((int) _BINLOG_OFFSET);
		packet.putInt16(0);
		packet.putInt32(25);
		if (_BINLOG_FILE != null)
			packet.putString(_BINLOG_FILE);
		packet.write(out);
		out.flush();
	}
}