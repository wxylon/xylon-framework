/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.validator;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xylon.framework.model.User;
import com.xylon.framework.validator.UserValidator;

/**
 * @author wangx
 * @date 2013-10-20
 */
@Controller
public class ValidatorController {
	@InitBinder  
    public void initBinder(DataBinder binder) {  
       binder.setValidator(new UserValidator());  
    }  
   
    @RequestMapping("validator")  
    public String validator(@Valid User user, BindingResult result) {  
       if (result.hasErrors()){
    	   List<ObjectError> errMsg = result.getAllErrors();
    	   System.out.println(errMsg.get(0).getDefaultMessage());
    	   
    	   return "redirect:http://www.baidu.com/s?wd=错误";  
       }
       return "redirect:http://www.google.com.hk/#newwindow=1&q=正确&safe=strict";  
    }
}

