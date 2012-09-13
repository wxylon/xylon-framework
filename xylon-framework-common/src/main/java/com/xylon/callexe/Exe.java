/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.callexe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * http://blog.sina.com.cn/s/blog_59c701350100q9l9.html
 * @author wxylon@gmail.com
 * @date 2012-9-13
 */
public class Exe {

	public static void main(String[] args) throws Exception{
		String command = "F:\\exetest\\test1.exe 10";
		Process process = Runtime.getRuntime().exec(command);
		InputStream is1 = process.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is1));
		try {
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println("1输出结果为：" + line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		InputStream is2 = process.getErrorStream();
		if (null != is2) {
			BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
			String line = null;
			try {
				while (br2.readLine() != null)
					System.out.println("2输出结果为：" + line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
