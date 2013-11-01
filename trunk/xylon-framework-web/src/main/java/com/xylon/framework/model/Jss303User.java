/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author wangx
 * @date 2013-10-20
 */
public class Jss303User {
	
	@Size(min=3, max=20, message="用户名长度只能在3-20之间")
    private String username;
   
    private String password;
    
    @Digits(integer=5, fraction=0, message="年龄的最小值为10")
    private int age;
 
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
   
    public String toString() {
       return username + ", " + password;
    }

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

