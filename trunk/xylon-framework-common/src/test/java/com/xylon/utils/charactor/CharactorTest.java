package com.xylon.utils.charactor;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

/**
 * 关于字符的一些测试
 * @author wangx
 * @date 2012-5-23
 */
public class CharactorTest {
	
	
	/**
	 * gbk字符的测试
	 * @author wangx
	 * @date 2012-5-23
	 */
	@Test
	public void testGBK(){
		final String A = "a";
		final String WO = "我";
		try {
			Assert.assertEquals(A.getBytes("gbk").length, 1);
			Assert.assertEquals(A.getBytes("utf-8").length, 1);
			
			Assert.assertEquals(WO.getBytes("gbk").length, 2);
			Assert.assertEquals(WO.getBytes("utf-8").length, 3);
			
			//取得Unicode编码
			Assert.assertEquals((int)(WO.charAt(0)), 25105);
			//code转char
			Assert.assertEquals(new String(Character.toChars(25105)).charAt(0), WO.charAt(0));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
