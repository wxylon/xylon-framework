package com.xylon.utils.algorithm;

import org.junit.Test;

import com.xylon.BaseLog;

public class BubbleSortTest extends BaseLog{
	
	@Test
	public void test(){
		Object[] t = {0, 6, 99, 100, 23, 21, 332, 354, 1000, 1};
		debug("初始化数组", t);
		BubbleSort.sort(t);
		debug("排序后数组", t);
	}
	
}
