package com.xylon.utils.rpc;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-14
 */
public class HelloHandlerImpl implements HelloHandler {

	public String sayHello(String param) {
		return "sayHello：" + param;
	}

	public String sayHi(String param) {
		return "sayHi：" + param;
	}

}

