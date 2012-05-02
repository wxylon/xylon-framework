package com.xylon.datasource;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xylon.ibatis.bean.Person;
import org.xylon.ibatis.service.PersonService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;
import org.junit.Before;
import org.junit.Test;

public class PersonServiceTest{

	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		Log4jConfigurer.initLogging("classpath:log4j.properties");
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public void testPersonService() {
		long start = System.currentTimeMillis();
		PersonService personService = (PersonService) context.getBean("personService");
		List<Person> list = new LinkedList<Person>();
		list = personService.getAllPerson();
		long end = System.currentTimeMillis();
		System.out.println(" " + (end - start));
		for (Person p : list) {
			System.out.println(p.getId());
			System.out.println(p.getName());
			System.out.println(p.getInfo());
			System.out.println(new String(p.getInfo_blob()));
		}
	}
	
	@Test
	public void insertPersonInBatch(){
		try {
			PersonService personService = (PersonService) context.getBean("personService");
			final List<Person> persons = new ArrayList<Person>(1000);
			Person person;
			for (int i = 0; i < 1000; i++) {
				person = new Person();
				person.setName("test"+i);
				person.setInfo("info"+i);
				persons.add(person);
			}
			long start = System.currentTimeMillis();
//			try {
//				System.out.println(personService.insertInBatch(persons));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println("insertInBatch time: " + (System.currentTimeMillis() - start));
			start = System.currentTimeMillis();
			try {
				personService.insert(persons);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("insert time: " + (System.currentTimeMillis() - start));
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
