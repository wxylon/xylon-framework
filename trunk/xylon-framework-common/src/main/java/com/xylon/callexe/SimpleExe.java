/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.callexe;

/**
 * http://yearsaaaa123789.iteye.com/blog/1404865
 * @author wxylon@gmail.com
 * @date 2012-9-13
 */
public class SimpleExe {
	public static void main(String[] args) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("F:\\exetest\\test1.exe 10");
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("我想被打印...");
	}
}
