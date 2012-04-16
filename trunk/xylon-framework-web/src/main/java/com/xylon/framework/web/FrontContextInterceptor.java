package com.xylon.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontContextInterceptor extends HandlerInterceptorAdapter{
	protected final Log logger = LogFactory.getLog(getClass());
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("FrontContextInterceptor--->>preHandle--->start");
		return true;
	}
}