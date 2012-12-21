/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.exception;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-21
 */
public class ExceptionTest {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		System.out.println("start");
		try {
			test0();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	public static void test0() throws Exception{
		System.out.println(Thread.currentThread().getName());
		throw new Exception("异常测试");
	}
}

