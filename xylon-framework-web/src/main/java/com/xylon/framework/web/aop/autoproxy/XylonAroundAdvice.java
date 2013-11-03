package com.xylon.framework.web.aop.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XylonAroundAdvice implements MethodInterceptor{
	protected final Log logger = LogFactory.getLog(getClass());
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = invocation.proceed();
		logger.info("time"+(System.currentTimeMillis()-start)+"invocation.getArguments(): " + invocation.getArguments().toString());
		return result;
	}
}
