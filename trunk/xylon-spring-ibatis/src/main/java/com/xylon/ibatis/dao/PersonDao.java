package com.xylon.ibatis.dao;

import java.util.List;

import com.xylon.ibatis.bean.Person;


public interface PersonDao {
	public List<Person> getAllPerson();
	public Person getPersonById(String id);
	public void updateBlob(Person person);
	public int insertInBatch(final List<Person> applications) throws Exception;
	public void insert(Person person)throws Exception;
}
