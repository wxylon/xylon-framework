package com.sync.applier;

import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.update.Update;

import redis.clients.jedis.Jedis;


/**
 * 将 mysql 数据存储到 redis 中去，对一致性要求不是特别高的场景
 * 这种方式将大大加速应用的访问速度
 * 
 * @author ben
 *
 */
public class RedisApplier extends NoSQLApplier {
	
	private Jedis jedis = null;
	private String ip = null;
	private int port = 6379;
	private int timeout = 15000;
	
	public RedisApplier(String ip, int port, int timeout) {
		this.ip = ip;
		this.port = port;
		this.timeout = timeout;
	}


	@Override
	public void connect() throws Exception {
		jedis = new Jedis(ip, port, timeout);	
		jedis.connect();
	}

	@Override
	public void disconnect() {
		jedis.disconnect();		
	}


	@Override
	protected void insertStatement(Insert insert) {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	protected void updateStatement(Update update) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void deleteStatement(Delete delete) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void replaceStatement(Replace replace) {
		// TODO Auto-generated method stub
		
	}


}
