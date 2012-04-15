package com.xylon.framework.test;

import org.junit.Assert;
import org.junit.Test;

public class IncludeTest {
	
	@Test
	public void test(){
		Assert.assertEquals("", Include.trim(null));
	}
}
