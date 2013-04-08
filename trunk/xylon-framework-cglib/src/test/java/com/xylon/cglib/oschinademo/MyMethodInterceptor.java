/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.cglib.oschinademo;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-24
 */
public class MyMethodInterceptor implements MethodInterceptor {

	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println(">>>("+method.getName()+")MethodInterceptor start...");
		Object result = methodProxy.invokeSuper(object, args);
		System.out.println(">>>("+method.getName()+")MethodInterceptor ending...");
		return result;
	}
}
