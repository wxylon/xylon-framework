package com.xylon.utils.charactor;

import java.io.IOException;
import java.io.InputStream;

/**
 * 关于字符的一些测试
 * @author wangx
 * @date 2012-5-23
 */
public class CharTest{

	public static void main(String[] args) throws IOException {
		String p = "weare.txt";
		InputStream in = CharTest.class.getResourceAsStream(p);
		int read = in.read(new byte[1204]);
		System.out.println(read);
		
	}

}
