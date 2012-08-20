/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.spring.expression;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-20
 */
public class User {
	private String name;
	private String abridge;
	
	public User(){}
	
	public User(String name, String abridge){
		this.name = name;
		this.abridge = abridge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbridge() {
		return abridge;
	}

	public void setAbridge(String abridge) {
		this.abridge = abridge;
	}
}

