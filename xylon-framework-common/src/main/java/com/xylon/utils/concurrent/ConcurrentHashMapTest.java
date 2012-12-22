/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.concurrent;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

/**
 * http://www.ibm.com/developerworks/cn/java/java-lo-concurrenthashmap/index.html?ca=drs-
 * http://www.goldendoc.org/2011/06/juc_concurrenthashmap/
 * http://www.infoq.com/cn/articles/ConcurrentHashMap
 * @author wxylon@gmail.com
 * @date 2012-12-16
 */
public class ConcurrentHashMapTest {
	ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
	
	@Test
	public void testPut(){
		concurrentHashMap.putIfAbsent(null, "2");
	}
}

