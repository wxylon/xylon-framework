/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.spring.expression;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * http://orange5458.iteye.com/blog/1549551
 * @author wxylon@gmail.com
 * @date 2012-8-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class ExpressionBySpringTests{
	
	@Resource
	private Map autoCgSort;
	
	/**
	 * 测试初始化bean
	 * @throws Exception
	 * @author wxylon@gmail.com
	 * @date 2012-8-27
	 */
//	@Test
	public void test() throws Exception{
		Iterator<String> iterator = autoCgSort.keySet().iterator();
		for(String key = iterator.next(); iterator.hasNext(); key = iterator.next()){
			System.out.println(key);
		}
	}
	
	
	@Resource
	private User baseUser;
	@Resource
	private User user;
	
	/**
	 * 测试bean引用另一个bean的属性
	 * <b>null 对象是不能被赋值的</b>
	 * @author wxylon@gmail.com
	 * @date 2012-8-27
	 */
	@Test
	public void testSpELBeanRef() throws Exception{
		System.out.println(BeanUtils.describe(baseUser));
		System.out.println(BeanUtils.describe(user));
	}
}

