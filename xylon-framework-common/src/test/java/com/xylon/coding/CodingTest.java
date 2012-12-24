package com.xylon.coding;

/**
 * http://www.ibm.com/developerworks/cn/java/j-lo-chinesecoding/index.html?ca=drs-
 * @author Administrator
 */
public class CodingTest {
	
	public static String codes = "你a";
	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		try {
			byte[] b = codes.getBytes();
			for(byte k : b){
				System.out.println("    十进制："+k);
				System.out.println("十六进制："+Integer.toHexString(k));
				System.out.println("    二进制："+Integer.toBinaryString(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
