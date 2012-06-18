package com.xylon.utils.string;

/**
 * http://www.cppblog.com/abilitytao/archive/2010/04/23/113385.html
 * @author wangxiong
 */
public class StringTest {
	
	public static void main(String[] args) {
		test();
	}
	
	
	public static void test(){
		String s = new String("abc");
		String s1 = "abc";
		String s2 = new String("abc");
				  
		System.out.println(s == s1);
		System.out.println(s == s2);
		System.out.println(s1 == s2);
		
//		false
//		false
//		false
	}
}
