/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.cglib.oschinademo;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;

/**
 * http://www.oschina.net/code/explore/cglib-2.2
 * http://www.iteye.com/topic/799827
 * http://www.oschina.net/question/12_4239
 * @author wxylon@gmail.com
 * @date 2012-12-24
 */
public class TestCglibProxy {
	
	@Test
	public void main() {
		Target target = new Target();
		TestCglibProxy test = new TestCglibProxy();
		Target proxyTarget = (Target) test.createProxy(Target.class);
		String res = proxyTarget.execute();
		System.out.println(res);
	}

	public Object createProxy(Class targetClass) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(targetClass);
		enhancer.setCallback(new MyMethodInterceptor());
		return enhancer.create();
	}
}
