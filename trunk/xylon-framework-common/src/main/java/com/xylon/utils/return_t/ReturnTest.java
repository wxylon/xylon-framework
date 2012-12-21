/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.return_t;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-20
 */
public class ReturnTest {
	public static void main(String args[]) {
		ReturnTest hw = new ReturnTest();
		System.out.println(hw.get());
	}

	public String get() {
		String a = "";
		try {
			a = "1";
			System.out.println("1.a = " + a);
			return a;
		} catch (Exception e) {
			System.out.println("2.a = " + a);
		} finally {
			a = "2";
			System.out.println("3.a = " + a);
			return a;
		}
	}
}
