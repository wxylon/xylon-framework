/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/
package com.xylon.framework.web.action;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.validation.ObjectError;
/**
 * @author wangx
 * @date 2013-7-4
 */
public class BaseControllor extends GlobalController{
	private static final Logger logger = Logger.getLogger(BaseControllor.class);
	
	public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null && !"unknown".equalsIgnoreCase(request.getHeader("x-forwarded-for"))) {
            ip = ip + "," + request.getHeader("x-forwarded-for");
        }
        return ip;
    }
	
	protected String validator(List<ObjectError> errMsg) {
		if(errMsg.isEmpty()){
			return null;
		}else{
			StringBuilder stringBuilder = new StringBuilder();
			Iterator<ObjectError> iterator = errMsg.iterator();
			int index = 0;
			while(iterator.hasNext()){
				if(index != 0){
					stringBuilder.append("; ");
				}
				stringBuilder.append(iterator.next().getDefaultMessage());
				index++;
			}
			return stringBuilder.toString();
		}
	}
	
	protected String validator(Set<?> cvs) {
		if(cvs.isEmpty()){
			return null;
		}else{
			StringBuilder stringBuilder = new StringBuilder();
			Iterator<?> iterator = cvs.iterator();
			int index = 0;
			while(iterator.hasNext()){
				if(index != 0){
					stringBuilder.append("; ");
				}
				stringBuilder.append(((ConstraintViolation)iterator.next()).getMessage());
				index++;
			}
			return stringBuilder.toString();
		}
	}
}

