package com.xylon.utils.algorithm;

import org.junit.Test;

import com.xylon.BaseLog;

public class BinarysearchTest extends BaseLog{
	
	@Test
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
}
