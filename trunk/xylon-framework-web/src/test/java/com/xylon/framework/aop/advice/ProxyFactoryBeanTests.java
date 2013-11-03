/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.aop.advice;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.xylon.framework.service.IService;

/**
 * @author wxylon@gmail.com
 * @date 2013-4-3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"ProxyFactoryBeanTests.xml"})
@Transactional
public class ProxyFactoryBeanTests extends AbstractJUnit4SpringContextTests {
	
	@Resource
	IService iService;
	
	@Test
	public void testAdvice(){
		try {
			iService.hello();
		} catch (Exception e) {
		}
	}
}

