/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.action.bind;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xylon.framework.web.action.BaseControllor;

/**
 * @author wangx
 * @date 2013-11-1
 */
@Controller
public class RequestBodyAction extends BaseControllor{
	
	private static Log log = LogFactory.getLog(RequestBodyAction.class);
	
	@RequestMapping(value = "/requestBody", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, ? extends Object> requestBody(@RequestBody @Valid Person person, BindingResult valid, HttpServletRequest request, ModelMap model) {
		log.debug("RequestBodyAction--->requestBody--->start");
		Map<String, Object> error = new HashMap<String, Object>();
		if (valid.hasErrors()) {
			error.put("code", 201);
			error.put("msg", super.validator(valid.getAllErrors()));
		} else {
			error.put("code", 200);
		}
		return error;
	}
	
	@RequestMapping(value = "/demoes/spring-request-body.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String show(HttpServletRequest request, ModelMap model) {
		return "/demoes/spring-request-body";
	}
	
	public static class Person{
		@NotNull(message="不能为空！")
		private String username;
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
}

