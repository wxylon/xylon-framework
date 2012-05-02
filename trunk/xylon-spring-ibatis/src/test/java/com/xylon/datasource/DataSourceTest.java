package com.xylon.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest{
	
	@Test
	public void testDataSource() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			BasicDataSource dataSource = (BasicDataSource)context.getBean("dataSource");
			Connection conn = dataSource.getConnection();
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery("select * from person");
			while(rs.next()){
				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
