/**
* Copyright(c) 2002-2014, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.customAnnotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author wangx
 * @date 2014-2-19
 */
public class CustomAnnotationInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long id = 10000l;
		
		request.setAttribute(CustomId.ID, id);
		return true;
	}
	
}

