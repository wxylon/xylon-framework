package com.xylon.framework.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xylon.framework.service.IService;

@Controller
public class DynamicController{
	
	private static Log log = LogFactory.getLog(DynamicController.class);
	
	@Resource
	private IService iService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		iService.hello();
		return "/index";
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String indexForWeblogic(HttpServletRequest request, ModelMap model) {
		return index(request, model);
	}
	
	@RequestMapping(value = "/demoes/debugDispatcherServlet.html", method = RequestMethod.GET)
	public String debugDispatcherServlet(HttpServletRequest request, ModelMap model){
		String id = request.getParameter("id");
		model.put("newId", "id:"+id);
		return "/demoes/debugDispatcherServlet";
	}
	
	@RequestMapping(value = "/demoes/spring-local-theme.html", method = RequestMethod.GET)
	public String d1(HttpServletRequest request, ModelMap model){
		log.debug("DynamicController--->d1");
		return "/demoes/spring-local-theme";
	}
}
