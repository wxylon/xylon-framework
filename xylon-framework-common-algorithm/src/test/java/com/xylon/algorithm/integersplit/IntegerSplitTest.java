/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.algorithm.integersplit;

import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-14
 */
public class IntegerSplitTest {
	
//	@Test
	public void testIntegerSplitByEunm(){
		System.out.println(IntegerSplit.integerSplitByEunm(3));
	}
	
	@Test
	public void testRecursive(){
		System.out.println(IntegerSplit.recursive(3, 3));
	}
}

