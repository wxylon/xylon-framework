package com.xylon.utils.algorithm;

import java.util.Arrays;

import org.junit.Test;
import com.xylon.BaseLog;

public class MergeSortTest extends BaseLog{
	
	@Test
	public void test(){
		Object[] t = {0, 6, 99, 100, 23, 21, 332, 354, 1000, 1};
		debug("初始化数组", t);
		Arrays.sort(t, 0, t.length);
		debug("排序后数组", t);
	}
}
