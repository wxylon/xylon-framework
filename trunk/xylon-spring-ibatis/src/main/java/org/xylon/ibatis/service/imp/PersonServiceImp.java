package org.xylon.ibatis.service.imp;

import java.util.Iterator;
import java.util.List;

import org.xylon.ibatis.bean.Person;
import org.xylon.ibatis.dao.PersonDao;
import org.xylon.ibatis.service.PersonService;

public class PersonServiceImp implements PersonService {
	
	private PersonDao personDao;
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public List<Person> getAllPerson() {
		return personDao.getAllPerson();
	}

	public Person getPersonById(String id) {
		return personDao.getPersonById(id);
	}

	public void updateBlob(Person person) {
		personDao.updateBlob(person);
	}

	public int insertInBatch(final List<Person> applications)throws Exception{
		int count = personDao.insertInBatch(applications);
		return count;
	}

	public void insert(Person person)throws Exception{
		personDao.insert(person);
	}

	public void insert(List<Person> persons)throws Exception{
		Iterator<Person> psons = persons.iterator();
		for(Person person : persons){
			personDao.insert(person);
		}
		System.out.println(1/0);
	}
}
