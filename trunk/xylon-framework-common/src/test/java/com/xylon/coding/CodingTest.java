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
			for(int i = 0; i < codes.length(); i++){
				char k = codes.charAt(i);
				System.out.println("    十进制："+(long)k );
				System.out.println("十六进制："+Long.toHexString(k));
				System.out.println("    二进制："+Long.toBinaryString(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
