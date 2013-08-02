package com.xylon.framework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor 接口是在该实例bean初始化的时候做的一些预处理<br/>
 * 调试日志信息：log4j.logger.org.springframework=debug<br/>
 * @author wxylon@gmail.com
 * @date 2012-12-18
 */
public class BeanPostPrcessorImpl implements BeanPostProcessor {

	// Bean 实例化之前进行的处理
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		String beanClassName = null;
		if (null != bean) {
			beanClassName = bean.getClass().getName();
		}
		System.out.println("对象" + beanName + "开始实例化,class:" + beanClassName);
		return bean;
	}

	// Bean 实例化之后进行的处理
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		String beanClassName = null;
		if (null != bean) {
			beanClassName = bean.getClass().getName();
		}
		System.out.println("对象" + beanName + "实例化完成, class:" + beanClassName);
		return bean;
	}
}
