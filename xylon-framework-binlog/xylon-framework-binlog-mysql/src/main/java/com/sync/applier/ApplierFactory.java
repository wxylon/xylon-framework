package com.sync.applier;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.sync.MysqlSyncProperties;

/**
 * 
 * @author ben
 *
 */
public class ApplierFactory {
	
	private static Logger logger = Logger.getLogger(ApplierFactory.class);
	
	public static List<Applier> createAppliers() {
		
		List<Applier> appliers = new ArrayList<Applier>();
		
		//MySQL
		String appliersProp = MysqlSyncProperties.getProperty("appliers");		
		if (appliersProp == null) {
			
			logger.error("Applers not found, can not be initialized");
			
		} else {
			
			String[] arr = appliersProp.split(";");
			for (int i = 0; i < arr.length; i++) {
				
				if (arr[i].equalsIgnoreCase("mysql")) {
					
					String url = MysqlSyncProperties.getProperty("applier.mysql.url");	
					String login = MysqlSyncProperties.getProperty("applier.mysql.login");
					String password = MysqlSyncProperties.getProperty("applier.mysql.password");
					String schema = MysqlSyncProperties.getProperty("applier.mysql.schema");
					
					appliers.add( new MysqlApplier(url, login, password, schema) );
					
				}else if (arr[i].equalsIgnoreCase("redis")) {
					
					String ip = MysqlSyncProperties.getProperty("applier.redis.ip");
					String port = MysqlSyncProperties.getProperty("applier.redis.port");	
					
					appliers.add( new RedisApplier(ip, Integer.parseInt(port), 15000) );
				}
			}
		}		
		
		return appliers;
	}

}
