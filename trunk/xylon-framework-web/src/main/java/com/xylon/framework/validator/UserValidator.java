/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.validator;

/**
 * @author wangx
 * @date 2013-10-20
 */
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.xylon.framework.model.User;
 
public class UserValidator implements Validator {
 
    public boolean supports(Class<?> clazz) {
       return User.class.equals(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
       ValidationUtils.rejectIfEmpty(errors, "username", null, "Username is empty.");
       User user = (User) obj;
       if (null == user.getPassword() || "".equals(user.getPassword()))
           errors.rejectValue("password", null, "Password is empty.");
    }
 
}

