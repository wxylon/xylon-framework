/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.algorithm.arithmetic;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-10
 */
public class ArithmeticTest {
	public static void main(String[] args){
		testOr();
	}
	
	public static void testOr(){
		long t = 0;
		char a = 0x48;
		char b = 0x52; 
		t = b<<8 | a;
		System.out.println(t);
		System.out.println(b<<8 | a);
		System.out.println((int)0xB7AD);
		System.out.println((int)0x37AD);
	}
}

