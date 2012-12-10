/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package test.threads;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-10
 */
public class SqlMapTest {
	
	@Test
	public void init() throws Exception{
		 String resource = "test/threads/sql-map-config.xml";
		 Reader reader = Resources.getResourceAsReader(resource);
		 SqlMapClientImpl sqlMap = (SqlMapClientImpl)SqlMapClientBuilder.buildSqlMapClient(reader);
		 
		Iterator iterator = sqlMap.getDelegate().getMappedStatementNames();
		while(iterator.hasNext()){
			Object o = iterator.next();
			System.out.println(o.toString());
			MappedStatement mst = sqlMap.getDelegate().getMappedStatement((String)o);
			System.out.println(" id : "+mst.getId());
			System.out.println(" resource : "+mst.getResource());
			System.out.println(" SQL : "+mst.getSql().getSql(null, null));
		}
	}
}

