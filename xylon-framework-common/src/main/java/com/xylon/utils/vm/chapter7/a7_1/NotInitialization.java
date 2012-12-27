/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter7.a7_1;

import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-27
 */
public class NotInitialization {
	
//	@Test
	public void testStaticBolck(){
		System.out.println(SubClass.value);
		System.out.println(SuperClass.value);
	}
	
	//@Test
	public void testConstructor(){
		System.out.println("++++++++++++++++++++++++");
		SubClass subClass1 = new SubClass();
		System.out.println("++++++++++++++++++++++++");
		SubClass subClass2 = new SubClass();
		System.out.println("++++++++++++++++++++++++");
	}
	
//	@Test
	public void testByClassForName(){
		try {
			Class class1 = Class.forName("com.xylon.utils.vm.chapter7.a7_1.SubClass");
			System.out.println("++++++++++++++++++++++++");
			SubClass subClass = (SubClass)class1.newInstance();
			System.out.println("++++++++++++++++++++++++");
			SubClass subClass2 = new SubClass();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  
	}
	
//	@Test
	public void testArray(){
		SubClass[] subClasses = new SubClass[10];
	}
	
	@Test
	public void testFinalStatic(){
		System.out.println(SuperClass.HELLOWORLD);
	}
}



