/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */
package com.xylon.algorithm.stringsearch;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-5
 */
public class PlainAlgorithmTest {
	
	@Test
	public void plainTest(){
		String sources = "abcdefghijcdefklmnopqcdefrstuvcdefwxyz";
		String searchTarget = "cdef";
		int count = PlainAlgorithm.plainFor(sources, searchTarget);
		Assert.assertSame(count, 4);
	}
}

