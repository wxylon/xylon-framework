package com.xylon.spring.aop.bytecode.performance;

import com.xylon.spring.aop.cglib.CglibAopDemo;
import com.xylon.spring.aop.jdk.JDKProxyDemo;
import com.xylon.spring.aop.jdk.ProxyBusiness;


/**
 * TODO please describe PerformanceTest.
 * 
 * @author tengfei.fangtf
 */
public class PerformanceTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JDKProxyDemo.aop();
        long end = System.currentTimeMillis();
        long jdkProxyNeedTime = end - start;

        start = System.currentTimeMillis();
        ProxyBusiness.staticDynamic();
        end = System.currentTimeMillis();
        long staticProxyNeedTime = end - start;

        start = System.currentTimeMillis();
        CglibAopDemo.byteCodeGe();
        end = System.currentTimeMillis();
        long byteCodeGeNeedTime = end - start;

        System.out.println("jdkProxyNeedTime need time:" + jdkProxyNeedTime + "ms");
        System.out.println("staticDynamic need time:" + staticProxyNeedTime + "ms");
        System.out.println("byteCodeGe need time:" + byteCodeGeNeedTime + "ms");
    }

}
