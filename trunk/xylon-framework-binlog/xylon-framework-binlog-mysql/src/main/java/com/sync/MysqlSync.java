package com.sync;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.sync.web.JettyServer;

/**
 * 
 * MYSQL 同步复制引擎
 * 
 * @author ben
 * 
 */
public class MysqlSync {
	
	private static Logger logger = Logger.getLogger(MysqlSync.class);
	
	public static boolean _IO_THREAD_STATUS = false;
	public static boolean _SQL_THREAD_STATUS = false;
	public static String _POS_INFO = null;
	public static File _POS_FILE = null;
	
	private static JettyServer jettyServer = new JettyServer();
	private static ScheduledExecutorService scheduledExecutor = new ScheduledThreadPoolExecutor(2);


	// 监视 IO 及 SQL 线程的执行
	static class HOOK implements Runnable {
		@Override
		public void run() {
			if (!_IO_THREAD_STATUS) {							
				new Thread(new Mysql_IO_Thread()).start(); 
				logger.info(" IO thread idle. ");
			}
			if (!_SQL_THREAD_STATUS) {
				new Thread(new Mysql_SQL_Thread()).start(); 
				logger.info(" SQL thread idle. ");
			}
		}
	}

	public static void main(String[] args) {
		
//		String syncHome = System.getProperty("sync.home");
		String syncHome = "D:\\maven-xylon\\xylon-framework-2\\xylon-framework-binlog\\xylon-framework-binlog-mysql\\target\\classes";
		if (syncHome == null) {
			syncHome = System.getProperty("user.dir");
			System.setProperty("sync.home", syncHome);
		}
		logger.info("sync home path : " + syncHome);

		MysqlSyncProperties.setHomeDirectory(syncHome);
		String log4jfile = syncHome + File.separator+ "log4j.xml";
		DOMConfigurator.configure(log4jfile);
		
		_POS_FILE = new File(syncHome + File.separator + "sync.position");
		scheduledExecutor.scheduleWithFixedDelay(new HOOK(), 3, 3, TimeUnit.SECONDS);
		
		
//		jettyServer.start();
	}
}