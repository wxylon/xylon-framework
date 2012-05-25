/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.algorithm;

import org.junit.Test;

import com.xylon.BaseLog;

/**
 * @author wxylon@gmail.com
 * @date 2012-5-24
 */
public class FastSortTest extends BaseLog{
	
	@Test
	public void testOne(){
		Object[] t = {0};
		debug("初始化数组", t);
		FastSort.sort(t);
		debug("排序后数组", t);
	}
	
	@Test
	public void testTwo(){
		Object[] t = {0, 100};
		debug("初始化数组", t);
		FastSort.sort(t);
		debug("排序后数组", t);
	}
	
	@Test
	public void testTen(){
		Object[] t = {0, 6, 99, 100, 23, 21, 332, 354, 1000, 60};
		debug("初始化数组", t);
		FastSort.sort(t);
		debug("排序后数组", t);
	}
	
	@Test
	public void testMore(){
		int size = 100;
		Object[] t = new Object[size];
		for(int i = 0; i < size; i++){
			t[i] = Double.valueOf(Math.random()*100000).intValue();
		}
		long start = System.currentTimeMillis();
		FastSort.sort(t);
		System.out.println(System.currentTimeMillis() - start);
	}
}

