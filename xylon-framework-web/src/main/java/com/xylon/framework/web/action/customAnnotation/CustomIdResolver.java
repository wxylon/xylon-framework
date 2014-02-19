///**
//* Copyright(c) 2002-2014, 360buy.com  All Rights Reserved
//*/
//
//package com.xylon.framework.web.action.customAnnotation;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.context.request.WebRequest;
//
///**
// * @author wangx
// * @date 2014-2-19
// */
//public class CustomIdResolver implements HandlerMethodArgumentResolver {
//	public boolean supportsParameter(MethodParameter parameter) {
//		return parameter.getParameterAnnotation(CustomId.class) != null;
//	}
//
//	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
//			WebDataBinderFactory binderFactory) throws Exception { 
//		return webRequest.getAttribute(CustomId.ID, WebRequest.SCOPE_REQUEST);
//	}
//}
//
