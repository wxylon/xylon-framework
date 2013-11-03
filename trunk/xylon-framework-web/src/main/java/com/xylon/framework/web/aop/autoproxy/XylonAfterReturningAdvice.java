/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.web.aop.autoproxy;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author wxylon@gmail.com
 * @date 2013-4-3
 */
public class XylonAfterReturningAdvice implements AfterReturningAdvice{
	protected final Log logger = LogFactory.getLog(getClass());
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		logger.info("method.tostring:"+method.toString() + "; target.getClass().getName():"+target.getClass().getName() + "; args:" + args.toString());
	}
}

