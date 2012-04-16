package com.xylon.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
	
	public Object target; //目标对象

	JDKInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行原有逻辑
        Object rev = method.invoke(target, args);
        //执行织入的日志
        if (method.getName().equals("doSomeThing")) {
            System.out.println("动态代理记录日志");
        }
        return rev;
    }
}
