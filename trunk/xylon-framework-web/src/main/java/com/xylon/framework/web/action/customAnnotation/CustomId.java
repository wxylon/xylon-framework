/**
* Copyright(c) 2002-2014, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.customAnnotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangx
 * @date 2014-2-19
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomId {
	static String ID = "CUSTOM_ID";
}

