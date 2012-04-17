package com.xylon.framework.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xylon.framework.service.IService;

@Controller
public class DynamicController{
	
	@Resource
	private IService iService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		iService.hello();
		return "/hc_dk";
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request, ModelMap model) {
		return index(request, model);
	}
}
