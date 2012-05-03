package com.xylon.ibatis.dao.imp;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.xylon.ibatis.bean.Person;
import com.xylon.ibatis.dao.PersonDao;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

public class PersonDaoImp extends SqlMapClientDaoSupport implements PersonDao {

	@SuppressWarnings("unchecked")
	public List<Person> getAllPerson() {
		return getSqlMapClientTemplate().queryForList("Person.queryAll");
	}

	public Person getPersonById(String id ) {
		return (Person)getSqlMapClientTemplate().queryForObject("Person.queryById",id);
	}

	public void updateBlob(Person person) {
		getSqlMapClientTemplate().update("Person.updateBlob",person);
	}
	
	public void insert(Person person) throws Exception{
		getSqlMapClientTemplate().insert("Person.insertPerson", person);
	}

	public int insertInBatch(final List<Person> applications) throws Exception{
        Integer updateCount = (Integer) getSqlMapClientTemplate().execute( new SqlMapClientCallback() {
            public Object doInSqlMapClient( SqlMapExecutor executor ) throws SQLException {
                executor.startBatch();
                Iterator<Person> iter = applications.iterator();
                while( iter.hasNext() ) {
                    executor.insert("Person.insertPerson", iter.next());
                }
                return executor.executeBatch();	
            }
        });
        return updateCount.intValue();
	}
}
