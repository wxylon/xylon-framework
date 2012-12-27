/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter7.a7_1;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-27
 */
public class SubClass extends SuperClass{
	
	//public static int value = 456;
	
	{
		System.out.println("SubClass not-static block init");
	}
	
	static {
		System.out.println("SubClass static block init");
	}
	
	public SubClass(){
		System.out.println("SubClass constructor  init");
	}
}

