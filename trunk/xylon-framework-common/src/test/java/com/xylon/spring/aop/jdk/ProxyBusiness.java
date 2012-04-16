package com.xylon.spring.aop.jdk;

import java.lang.reflect.Method;

import com.xylon.spring.aop.model.Business;
import com.xylon.spring.aop.model.IBusiness;
import com.xylon.spring.aop.model.IBusiness2;

public class ProxyBusiness implements IBusiness, IBusiness2 {
	 private JDKInvocationHandler h;

	    @Override
	    public void doSomeThing2() {
	        try {
	            Method m = (h.target).getClass().getMethod("doSomeThing", null);
	            h.invoke(this, m, null);
	        } catch (Throwable e) {
	            // 异常处理 1（略）
	        }
	    }

	    @Override
	    public boolean doSomeThing() {
	        try {
	            Method m = (h.target).getClass().getMethod("doSomeThing2", null);
	            return (Boolean) h.invoke(this, m, null);
	        } catch (Throwable e) {
	            // 异常处理 1（略）
	        }
	        return false;
	    }

	    public ProxyBusiness(JDKInvocationHandler h) {
	        this.h = h;
	    }

	    //测试用
	    public static void main(String[] args) {
	        //构建AOP的Advice
	        staticDynamic();
	    }

	    public static void staticDynamic() {
	    	JDKInvocationHandler handler = new JDKInvocationHandler(new Business());
	        new ProxyBusiness(handler).doSomeThing();
	        new ProxyBusiness(handler).doSomeThing2();
	    }

}
