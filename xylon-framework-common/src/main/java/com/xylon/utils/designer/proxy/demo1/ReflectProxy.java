/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.proxy.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-27
 */
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//通过实现InvocationHandler接口的invoke方法 促使 被代理对象的方法别调用时都会出发invoke方法
public class ReflectProxy implements InvocationHandler {
	// 被代理的对象
	private Object proxyObject;

	public ReflectProxy(Object proxyObject) {
		this.proxyObject = proxyObject;
	}

	// 通过工厂模式生成一个代理对象
	public static Object factory(Object proxyObject) {
		Class cls = proxyObject.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), new ReflectProxy(proxyObject));
	}

	// InvocationHandler 接口中的方法
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// 在调用被代理类的实际方法前的操作
		doBeforeCalling(method);
		// 输出被代理类的实际方法中的参数
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("args[" + i + "]=" + args[i] + "");
			}
		}

		// 调用被代理类的实际方法
		Object o = method.invoke(proxyObject, args);
		// 在调用被代理类的实际方法后的操作
		doAfterCalling(method);
		return o;
	}

	// 在调用被代理类的实际方法前的操作
	private void doBeforeCalling(Method method) {
		System.out.println("before calling [" + method + "]");
	}

	// 在调用被代理类的实际方法后的操作
	private void doAfterCalling(Method method) {
		System.out.println("after calling [" + method + "]");
	}
}
