/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.callexe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http://gongxue.cn/xuexishequ/Print.asp?ArticleID=131944
 * @author wxylon@gmail.com
 * @date 2012-9-13
 */
public class CallExe {
	public static void main(String[] args) {
		openMyExe();
	}

	// 2.0调用其他的可执行文件，例如：自己制作的exe，或是下载安装的软件
	public static void openMyExe() {
		String text = null;
		String command = "D:\\csv2sqlserver\\dist\\main.exe \"autoTest\" \"ftp download\" \"78\"";// exe,bat文件名OR
																	// DOS命令
		try {
			Process proc = Runtime.getRuntime().exec(command, null, new File("D:\\csv2sqlserver\\dist\\"));
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			while ((text = in.readLine()) != null) {
				System.out.println(text); // 输出测试
			}
		} catch (IOException ioError) {
			ioError.printStackTrace();
			System.exit(0);
		}
	}
}
