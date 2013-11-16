/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xylon.framework.model.Jss303User;
import com.xylon.framework.web.action.BaseControllor;

/**
 * 单独绑定一个参数的不可行：Errors/BindingResult argument declared without preceding model attribute. Check your handler method signature!
 * @Digits(integer=5, fraction=0, message="年龄的最小值为10") String pid,
 * 只能绑定对象
 * @author wangx
 * @date 2013-10-20
 */
@Controller
public class Jss303ValidatorController extends BaseControllor{
    
	/**http://localhost:8080/xylon-framework-web/jss303Validator?username=12121&password=1212&age=1111111111111111111111111*/
    @RequestMapping("jss303Validator")  
    public @ResponseBody Map<String, ? extends Object> jss303Validator(@Valid Jss303User jss303User, BindingResult valid) {  
    	Map<String, Object> error = new HashMap<String, Object>();
    	if (valid.hasErrors()){
    		error.put("code", 201);
			error.put("msg", super.validator(valid.getAllErrors()));
    	}else{
        	error.put("code", 200);
        }
    	return error;
    } 
    
    @Deprecated
    /**http://localhost:8080/xylon-framework-web/jss303Validator0?pid=1*/
    @RequestMapping("jss303Validator0")  
    public @ResponseBody Map<String, ? extends Object> jss303Validator0(@Digits(integer=5, fraction=0, message="年龄的最小值为10") String pid, BindingResult valid) {  
    	Map<String, Object> error = new HashMap<String, Object>();
    	if (valid.hasErrors()){
    		error.put("code", 201);
			error.put("msg", super.validator(valid.getAllErrors()));
        }else{
        	error.put("code", 200);
        }
    	
    	return error;
    } 
}

