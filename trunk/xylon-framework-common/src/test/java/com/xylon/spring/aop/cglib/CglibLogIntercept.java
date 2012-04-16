package com.xylon.spring.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibLogIntercept implements MethodInterceptor {
	public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //执行原有逻辑
    	before();
        Object rev = proxy.invokeSuper(target, args);
        //执行织入的日志
        if (method.getName().equals("doSomeThing2")) {
            System.out.println("指定方法---记录日志");
        }
        end();
        return rev;
    }
    
	public void before() {
		System.out.println("--------在调用要代理类的方法前做的处理----------");
	}

	public void end() {
		System.out.println("--------在调用要代理类的方法后做的处理----------");
	}

}
