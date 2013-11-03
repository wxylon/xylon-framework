package com.xylon.framework.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xylon.framework.service.IService;

@Service(value="iserver")
public class IServiceImpl implements IService {

	public void hello() {
		Object object = null;
		((Map)object).size();
	}
}
