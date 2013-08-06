package com.sync;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.sync.extractor.mysql.MySQLExtractor;


/**
 * IO 线程用于从 mysql 获取 binlog event
 * @author ben
 */
public  class Mysql_IO_Thread implements Runnable {
	
	private static Logger logger = Logger.getLogger(Mysql_IO_Thread.class);

	MySQLExtractor extractor = null;

	public Mysql_IO_Thread() {
		MysqlSync._IO_THREAD_STATUS = true;
		
		String url = MysqlSyncProperties.getProperty("extractor.mysql.url");
		String login = MysqlSyncProperties.getProperty("extractor.mysql.login");
		String password = MysqlSyncProperties.getProperty("extractor.mysql.password");
		
		extractor = new MySQLExtractor(url, login, password);
	}

	@Override
	public void run() {
		MysqlSync._IO_THREAD_STATUS = true;
		
		try {
			String binlogFileAndPos = FileUtils.readFileToString(MysqlSync._POS_FILE);
			String[] binlogs = binlogFileAndPos.split(";");
			
			MySQLExtractor._BINLOG_FILE = binlogs[0]; // "mysql-bin.000006";
			MySQLExtractor._BINLOG_OFFSET = Integer.parseInt(binlogs[1]); // 4;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Connect and process data.
		try {
			extractor.connect();
			extractor.processEvent();
		} catch (Exception e) {
			MysqlSync._IO_THREAD_STATUS = false;
			logger.fatal("Relay client failed with unexpected exception: " + e.getMessage(), e);
		} finally {
			extractor.disconnect();
		}
	}
}
