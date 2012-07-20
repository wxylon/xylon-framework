/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.rpc.hadoop.one;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-20
 */
public class TestRPCCustom {
	public static void main(String[] args) {
		InvocationHandler invacationHandler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("hello,word");
				return null;
			}
		};

		Hello hello = (Hello) Proxy.newProxyInstance(TestRPCCustom.class.getClassLoader(), new Class[] { Hello.class }, invacationHandler);
		hello.hello();
	}

	interface Hello {
		public void hello();
	}
}
