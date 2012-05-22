package com.xylon.utils.algorithm;

import org.junit.Assert;
import org.junit.Test;

import com.xylon.BaseLog;

public class BinarysearchTest extends BaseLog{
	
//	@Test
	public void test(){
		int size = 10002;
		int[] t = new int[size];
		for(int i = 0; i < size; i++){
			t[i] = i;
		}
		long start = System.currentTimeMillis();
		for(int i = 0; i < size; i++){
			int index = Binarysearch.binarysearch(t, i);
			debug("索引:" + index, new Object[0]);
		}
		System.out.println(System.currentTimeMillis() - start);
	}
	
//	@Test
	public void test1(){
		int size = 1;
		int[] t = new int[size];
		for(int i = 0; i < size; i++){
			t[i] = i;
		}
		for(int i = 0; i < size; i++){
			int index = Binarysearch.binarysearch(t, i);
			debug("索引:" + index, new Object[0]);
		}
	}
	
//	@Test
	public void test2(){
		int size = 2;
		int[] t = new int[size];
		for(int i = 0; i < size; i++){
			t[i] = i;
		}
		for(int i = 0; i < size; i++){
			int index = Binarysearch.binarysearch(t, i);
			debug("索引:" + index, new Object[0]);
		}
	}
	
	/**
	 * 符号测试
	 */
	@Test
	public void testSign(){
		int first = Integer.MAX_VALUE,  second = Integer.MAX_VALUE;
		Assert.assertEquals(2147483647, first);
		Assert.assertEquals(2147483647, second);
		System.out.println((first + second) >> 1);
		//  >>> 无符号移位
		System.out.println((first + second) >>> 1);
	}
}
