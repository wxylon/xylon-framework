package com.xylon.utils.map;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-25
 */
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TreeMapTest{
	Map<Integer,String> map = new TreeMap<Integer, String>();
	int key = 0;
	
	
	@Before
	public void before() throws Exception {
		Random r = new Random();
		String uuid = "";
		
		for(int i = 0; i < 100; i++){
			uuid = UUID.randomUUID().toString();
			key = r.nextInt(100);
			map.put(key,uuid);
			System.out.println("i:" + key + "\t uuid:" + uuid);
		}
		
	}
	
	@Test
	public void testTreeMap(){
		System.out.println(map.size());
		System.out.println(map.get(key));
	}
}


