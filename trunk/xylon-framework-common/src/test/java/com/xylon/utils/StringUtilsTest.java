package com.xylon.utils;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
	
	@Test
	public void testTrim(){
		Assert.assertEquals("", StringUtils.trim(null));
		Assert.assertEquals("", StringUtils.trim(""));
		Assert.assertEquals("fd fs", StringUtils.trim(" fd fs "));
	}
	
	public void t(){
	}
}
