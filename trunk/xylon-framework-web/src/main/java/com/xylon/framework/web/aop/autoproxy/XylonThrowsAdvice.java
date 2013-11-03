/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.web.aop.autoproxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author wxylon@gmail.com
 * @date 2013-4-3
 */
public class XylonThrowsAdvice implements ThrowsAdvice{
	protected final Log logger = LogFactory.getLog(getClass());
	public void afterThrowing(NullPointerException npe) throws Exception{
		logger.error(npe.getMessage(), npe.getCause());
	}
}

