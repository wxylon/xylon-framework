/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.collection;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

/**
 * TreeSet测试：
 * 	1.不容许<b>null</b>对象值;
 * 	2.有序的集合;
 *	3.底层存储使用TreeMap()--红黑树的key;
 * @author wxylon@gmail.com
 * @date 2012-8-21
 */
public class TreeSetTest {
	
	@Test
	public void testAdd(){
		Set<String> set = new TreeSet<String>();
		set.add("");
		set.add("11");
		set.add("11");
		set.add("33");
		set.add("11");
		for(String s : set){
			System.out.println(s);
		}
	}
}

