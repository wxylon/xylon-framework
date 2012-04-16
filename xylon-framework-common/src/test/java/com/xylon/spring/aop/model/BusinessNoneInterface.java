package com.xylon.spring.aop.model;

/**
 * 业务逻辑类
 */
public class BusinessNoneInterface{

    public boolean doSomeThing() {
        System.out.println("执行业务逻辑");
        return true;
    }

    public void doSomeThing2() {
        String s = "执行业务逻辑2";
        System.out.println(s);
    }
}
