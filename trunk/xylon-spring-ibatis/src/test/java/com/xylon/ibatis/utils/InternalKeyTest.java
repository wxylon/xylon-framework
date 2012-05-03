package com.xylon.ibatis.utils;

import java.nio.ByteBuffer;
import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class InternalKeyTest {
	
	@Test
	public void test(){
		int dbid = 1;
		Date date = new Date(106, 0, 2, 0, 0, 0);
//		final DateTime START = new DateTime(2006, 1, 1, 0, 0, 0);
//		System.out.println(START.toDate().toLocaleString());
		InternalKey one = new InternalKey(dbid, date);
		ByteBuffer buffer = ByteBuffer.allocate(6);
		buffer.putInt(dbid);
		buffer.putShort((short)1);
		InternalKey another = new InternalKey(buffer.array());
		Assert.assertNotSame(one, another);
		Assert.assertEquals(one, another);
		Assert.assertEquals(one.hashCode(), another.hashCode());
	}
}
