/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author wangx
 * @date 2013-10-20
 */
@Controller
public class GlobalController {
	/**
     * 前端传入的参数，转换为后端的Controller绑定参数类型时，发生类型转换错误时，触发。<br/>
     * eg. 前端传入id=10000000000000000000000,后端绑定参数为Integer类型时，入参超过了Integer值范围，将会触发该异常。
     * @return 
	 * @author wangx
	 * @date 2013-10-20
	 */
    @ExceptionHandler({TypeMismatchException.class})
    public String exception(TypeMismatchException e) {
		return "exception";
    }  
}

