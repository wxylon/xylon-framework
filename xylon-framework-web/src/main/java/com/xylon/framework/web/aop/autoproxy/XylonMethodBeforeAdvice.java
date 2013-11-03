package com.xylon.framework.web.aop.autoproxy;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class XylonMethodBeforeAdvice implements MethodBeforeAdvice {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public void before(Method method, Object[] args, Object object) {
		logger.info("method.tostring:"+method.toString() + "; target.getClass().getName():"+object.getClass().getName() + "; args:" + args.toString());
	}
}
