package com.sync;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.sync.applier.Applier;
import com.sync.applier.ApplierFactory;
import com.sync.event.DBMSEvent;
import com.sync.extractor.mysql.MySQLExtractor;


/**
 * 执行 DBMSEvent 入目标库
 * @author ben
 *
 */
public class Mysql_SQL_Thread implements Runnable {
	
	private static Logger logger = Logger.getLogger(Mysql_SQL_Thread.class);
	
	static List<Applier> appliers = new ArrayList<Applier>();

	public Mysql_SQL_Thread() {	
		MysqlSync._SQL_THREAD_STATUS = true;
		
		appliers = ApplierFactory.createAppliers();	
		if (appliers.isEmpty()) {
			System.exit(0);
		} 		
	}

	@Override
	public void run() {
		
		//初始化连接
		for(Applier applier: appliers) {
			try {
				applier.connect();
			} catch (Exception e) {
				logger.error("applier connect :" + e);
			}
		}
		
		while (MysqlSync._SQL_THREAD_STATUS) {

			logger.debug("Queue size:" + MySQLExtractor._QUEUE.size());

			if (!MySQLExtractor._QUEUE.isEmpty()) {
				DBMSEvent dbmsEvent = MySQLExtractor._QUEUE.poll();
				try {
					//持久化操作
					for(Applier applier: appliers) {
						applier.apply(dbmsEvent, true);
					}
										
					//成功后记录其相关binlog位置
					try {
						MysqlSync._POS_INFO = dbmsEvent.getEventId();
						FileUtils.write(MysqlSync._POS_FILE, dbmsEvent.getEventId());
					} catch (IOException e) {
						logger.error("binlog position to write error : " + e);
					}
					
				} catch (SQLException e) {
					MysqlSync._SQL_THREAD_STATUS = false;
					logger.error("SQL Thread pos : " + dbmsEvent.getEventId() + "  " + e);
				}
			}
		}
		
		// 断开连接
		for(Applier applier: appliers) 
			applier.disconnect();
		appliers.clear();	
	}
}
