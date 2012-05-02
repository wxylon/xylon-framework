package com.xylon.datasource;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.xylon.ibatis.bean.Person;

public class JdbcTest{
	public static void main(String[] args) {
		try {
			JdbcTest.testJdbc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testJdbc() throws Exception {
		long start = System.currentTimeMillis();
		Class.forName("com.mysql.jdbc.Driver");
		for (int i = 1; i < 10; i++) {
			java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.8.100:3306/ibatis", "root", "000000");
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery("select * from person");
			List<Person> list = new LinkedList<Person>();
			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setInfo(rs.getString("info"));
				list.add(p);
			}
			long end = System.currentTimeMillis();
			System.out.println("time：" + (end - start));
			for (Person item : list) {
				System.out.println(item.getId());
				System.out.println(item.getName());
				System.out.println(item.getInfo());
			}
		}
	}
}
