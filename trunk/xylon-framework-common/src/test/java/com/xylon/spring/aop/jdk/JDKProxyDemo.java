package com.xylon.spring.aop.jdk;

import java.lang.reflect.Proxy;

import com.xylon.spring.aop.model.Business;
import com.xylon.spring.aop.model.IBusiness;
import com.xylon.spring.aop.model.IBusiness2;

public class JDKProxyDemo {
	
	public static void main(String[] args) {
	    aop();
	}
	
	public static void aop() {
	    //需要代理的接口，被代理类实现的多个接口都必须在这里定义
	    Class[] proxyInterface = new Class[] { IBusiness.class, IBusiness2.class };
	    //构建AOP的Advice
	    JDKInvocationHandler handler = new JDKInvocationHandler(new Business());
	    //生成代理类的类加载器
	    ClassLoader classLoader = JDKProxyDemo.class.getClassLoader();
	    //生成代理类
	    IBusiness2 proxyBusiness = (IBusiness2) Proxy.newProxyInstance(classLoader, proxyInterface, handler);
	    //使用代理类的实例来调用方法。
	    proxyBusiness.doSomeThing2();
	    ((IBusiness) proxyBusiness).doSomeThing();
	}
}
