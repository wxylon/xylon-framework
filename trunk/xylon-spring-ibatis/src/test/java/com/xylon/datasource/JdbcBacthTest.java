package com.xylon.datasource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import com.xylon.ibatis.bean.Person;

public class JdbcBacthTest{
	
	Connection conn;
	@Before
	public void before(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://192.168.8.100:3306/ibatis", "root", "000000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testJdbc() throws Exception {
		conn.setAutoCommit(false);
		Statement sta = conn.createStatement();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			sta.addBatch("insert into person values (null, 'name"+i+"', 'info"+i+"')");
			sta.addBatch("insert into person_his values (null, 'name"+i+"', 'info"+i+"')");
		}
		int[] rs = sta.executeBatch();
		conn.commit();
		int count = 0;
		for(int k : rs){
			count++;
		}
		System.out.println(count);
	}
}
