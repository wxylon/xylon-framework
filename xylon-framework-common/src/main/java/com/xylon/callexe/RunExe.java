/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.callexe;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * http://freehello.blogspot.com/2009/04/java-execmd.html
 * @author wxylon@gmail.com
 * @date 2012-9-13
 */
public class RunExe {
	public static void main(String[] arg) {
		Runtime r = Runtime.getRuntime();
		try {
			String line;
			// Process p =
			// Runtime.getRuntime().exec("cmd /c svm-train.exe -c 32 -g 0.0078125 -v 5 trainset",null,new
			// File("C:\\libsvm\\windows")); 此时输出到控制台
			// Process p =
			// Runtime.getRuntime().exec("cmd /c start svm-train.exe -c 32 -g 0.0078125 -v 5 trainset",null,new
			// File("C:\\libsvm\\windows"));此时弹出dos窗口运行

			Process p = Runtime.getRuntime().exec("cmd /c F:\\exetest\\test1.exe 10", null, new File("F:\\exetest\\"));

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((line = br.readLine()) != null)
				System.out.println(line);

			 p.waitFor();
		}

		catch (Exception e) {
			System.out.println("Error!");

		}
	}

}
