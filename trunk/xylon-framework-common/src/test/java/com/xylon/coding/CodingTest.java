package com.xylon.coding;

import java.io.UnsupportedEncodingException;

/**
 * http://www.ibm.com/developerworks/cn/java/j-lo-chinesecoding/index.html?ca=drs-
 * @author Administrator
 */
public class CodingTest {
	
	public static String codes = "��a";
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		try {
			byte[] b = codes.getBytes();
			for(byte k : b){
				System.out.println("    ʮ���ƣ�"+k);
				System.out.println("ʮ�����ƣ�"+Integer.toHexString(k));
				System.out.println("    �����ƣ�"+Integer.toBinaryString(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
