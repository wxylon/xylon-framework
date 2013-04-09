/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @author wxylon@gmail.com
 * @date 2013-4-9
 */
public class BeanFactoryTests{
	ApplicationContext applicationContext = null;
	
	@Before
	public void init(){
		applicationContext = new ClassPathXmlApplicationContext("classpath:/com/xylon/framework/BeanFactoryTests.xml");
	}
	
	@Test
	public void testGetBean(){
		Map map = (Map)applicationContext.getBean("map");
		System.out.println(map.size());
	}
}

