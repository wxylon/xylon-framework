/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.proxy.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-27
 */
public class HelloWorld implements IHelloWorld {
	public void sayBye() {
		System.out.println("Hello World!");
	}

	public void sayHello() {
		System.out.println("Bye!");
	}

	public void saySomething(String msg) {
		System.out.println(msg);
	}
}
