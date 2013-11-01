/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.validator;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xylon.framework.model.Jss303User;

/**
 * @author wangx
 * @date 2013-10-20
 */
@Controller
public class Jss303ValidatorController {
    
	/**http://localhost:8080/xylon-framework-web/jss303Validator?username=12121&password=1212&age=1111111111111111111111111*/
    @RequestMapping("jss303Validator")  
    public String jss303Validator(@Valid Jss303User jss303User, BindingResult result) {  
    	 if (result.hasErrors()){
    		 List<ObjectError> errMsg = result.getAllErrors();
      	   System.out.println(errMsg.get(0).getDefaultMessage());
      	   
      	   return "redirect:http://www.baidu.com/s?wd=错误";  
         }
         return "redirect:http://www.google.com.hk/#newwindow=1&q=正确&safe=strict";  
    } 
    
    /**http://localhost:8080/xylon-framework-web/jss303Validator?username=12121&password=1212&age=1111111111111111111111111*/
    @RequestMapping("jss303Validator0")  
    public String jss303Validator0(@Digits(integer=5, fraction=0, message="年龄的最小值为10") String pid, BindingResult result) {  
    	 if (result.hasErrors()){
    		 List<ObjectError> errMsg = result.getAllErrors();
      	   System.out.println(errMsg.get(0).getDefaultMessage());
      	   
      	   return "redirect:http://www.baidu.com/s?wd=错误";  
         }
         return "redirect:http://www.google.com.hk/#newwindow=1&q=正确&safe=strict";  
    } 
}

