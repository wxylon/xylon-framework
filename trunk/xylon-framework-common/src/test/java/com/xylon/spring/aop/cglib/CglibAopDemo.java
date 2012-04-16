package com.xylon.spring.aop.cglib;

import com.xylon.spring.aop.model.BusinessNoneInterface;

import net.sf.cglib.proxy.Enhancer;
/**
 * 字节码生成机制演示AOP
 * @author tengfei.fangtf
 */
public class CglibAopDemo {

    public static void main(String[] args) {
        byteCodeGe();
    }

    public static void byteCodeGe() {
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(BusinessNoneInterface.class);
        //设置需要织入的逻辑
        enhancer.setCallback(new CglibLogIntercept());
        //创建子类
        BusinessNoneInterface newBusiness = (BusinessNoneInterface) enhancer.create();
        newBusiness.doSomeThing2();
        newBusiness.doSomeThing();
    }
}
