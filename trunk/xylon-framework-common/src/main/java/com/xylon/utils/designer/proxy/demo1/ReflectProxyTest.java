/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.proxy.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * http://wenku.baidu.com/view/2b0f21297375a417866f8f3b.html
 * @author wxylon@gmail.com
 * @date 2012-11-27
 */
public class ReflectProxyTest {
	public static void main(String[] args) {
		try {
			// 代理List接口对象
			Object obj = ReflectProxy.factory(new ArrayList<Object>(0));
			List list = (List) obj;
			list.add("A");
			list.add("B");

			// 代理IHelloWorld接口对象
			obj = ReflectProxy.factory(new HelloWorld());
			IHelloWorld helloWorld = (IHelloWorld) obj;
			helloWorld.sayHello();
			helloWorld.saySomething("How is everything!");
			helloWorld.sayBye();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
