/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.classloader;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * http://blog.163.com/liu_zhenxu@yeah/blog/static/652663532012424104012788/
 * @author wxylon@gmail.com
 * @date 2012-11-7
 */
public class TestClassLoader {
	public static void main(String[] args) throws Exception{  
        Properties p=System.getProperties();  
        Set<Object> s=p.keySet();  
        Iterator<Object> i=s.iterator();  
        while((i).hasNext()){  
            String key=(String)i.next();  
            if(key.contains("java.class.path")||key.contains("java.ext.dirs")||key.contains("sun.boot.class.path")) {
                  System.out.println(key+" === "+p.get(key));  
             }
        }  
        System.out.println("TestClassLoader === " + new TestClassLoader().getClass().getClassLoader());
    }  
}

