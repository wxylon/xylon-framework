/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.callexe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * http://stackoverflow.com/questions/4122850/use-cmd-commands-in-java-program
 * @author wxylon@gmail.com
 * @date 2012-9-13
 */
public class Dos {
	public static void main(String[] args) {
		try {
			String[] command = new String[4];
			command[0] = "cmd";
			command[1] = "/C";
			command[2] = "F:/exetest/test1.exe";// path
																		// of
																		// the
																		// compiler
			command[3] = "10";

			Process p = Runtime.getRuntime().exec(command);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command

			String s = null;
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command

			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
			System.out.println("I am In try");
		}

		catch (Exception e) {
			System.out.println("I am In catch");
		}
	}
}
