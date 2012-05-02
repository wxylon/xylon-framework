package org.xylon.ibatis.service;

import java.util.List;

import org.xylon.ibatis.bean.Person;

public interface PersonService {
	public List<Person> getAllPerson();
	public Person getPersonById(String id);
	public void updateBlob(Person person);
	public int insertInBatch(List<Person> applications )throws Exception;
	public void insert(Person person)throws Exception;
	public void insert(List<Person> person)throws Exception;
}
