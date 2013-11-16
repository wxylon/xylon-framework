/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xylon.framework.model.User;
import com.xylon.framework.validator.UserValidator;
import com.xylon.framework.web.action.BaseControllor;

/**
 * @author wangx
 * @date 2013-10-20
 */
@Controller
public class ValidatorController extends BaseControllor{
	@InitBinder  
    public void initBinder(DataBinder binder) {  
       binder.setValidator(new UserValidator());  
    }  
   
    @RequestMapping("validator")  
	public @ResponseBody Map<String, ? extends Object> validator(@Valid User user,BindingResult valid) {
		Map<String, Object> error = new HashMap<String, Object>();
		if (valid.hasErrors()) {
			error.put("code", 201);
			error.put("msg", super.validator(valid.getAllErrors()));
		} else {
			error.put("code", 200);
		}
		return error;
	}
}

