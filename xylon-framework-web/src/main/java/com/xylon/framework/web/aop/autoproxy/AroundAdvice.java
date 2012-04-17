package com.xylon.framework.web.aop.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AroundAdvice implements MethodInterceptor{
	protected final Log logger = LogFactory.getLog(getClass());
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
//		ActionMapping mapping = (ActionMapping) invocation.getArguments()[0];
//		ActionForm form = (ActionForm) invocation.getArguments()[1];
//		HttpServletRequest request = (HttpServletRequest) invocation.getArguments()[2];
//		HttpServletResponse response = (HttpServletResponse) invocation.getArguments()[3];
		Object result = invocation.proceed(); // 
		logger.info("time"+(System.currentTimeMillis()-start)+"invocation.getArguments(): " + invocation.getArguments().toString());
		return result;
	}

	
}
