package com.xylon.framework.web.action.customAnnotation;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xylon.framework.web.action.GlobalController;

//@Controller
public class CustomAnnotationController extends GlobalController{
	
	private static Log log = LogFactory.getLog(CustomAnnotationController.class);
	
//	@RequestMapping(value = "/customAnnotation", method = RequestMethod.GET)
	public String customAnnotation(HttpServletRequest request, ModelMap model, @CustomId Long id) {
		log.debug("CustomAnnotationController--->customAnnotation--->start");
		return "";
	}
}
