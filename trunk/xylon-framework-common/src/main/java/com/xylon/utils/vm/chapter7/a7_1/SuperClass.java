/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter7.a7_1;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-27
 */
public class SuperClass {
	
	public final static String HELLOWORLD = "hello world!";
	
	public static int value = 123;
	
	{
		System.out.println("SuperClass not-static block init");
	}
	
	static{
		System.out.println("SuperClass static block init");
	}
	
	public SuperClass(){
		System.out.println("SuperClass constructor init");
	}
}

