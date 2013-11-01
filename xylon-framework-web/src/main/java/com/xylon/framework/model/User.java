/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.model;

/**
 * @author wangx
 * @date 2013-10-20
 */
public class User {
	 
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
   
    public String toString() {
       return username + ", " + password;
    }
   
}

