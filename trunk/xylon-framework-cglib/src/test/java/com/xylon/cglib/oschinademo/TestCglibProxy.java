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
		TestCglibProxy test = new TestCglibProxy();
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Target.class);
		enhancer.setCallback(new MyMethodInterceptor());
		Target proxyTarget = (Target)enhancer.create();
		
		String res = proxyTarget.execute();
		System.out.println(res);
	}
}
