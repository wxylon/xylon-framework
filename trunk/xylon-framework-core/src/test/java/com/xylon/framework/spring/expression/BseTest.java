/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.spring.expression;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-20
 */
public class BseTest {

//	@Test
	public void testConcat() {
		//ExpressionParser接口用于解析一个Expression字符串
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!').concat('哇哈啊哈')");
		String message = (String) exp.getValue();
		System.out.println(message);
	}
	
	@Test
	public void testToUpperCase() {
		User user = new User("jizhou", "jz");
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");
		//EvaluationContext接口用于在评估一个表达式时，解析方法，字段，类型转换
		EvaluationContext context = new StandardEvaluationContext(user);
		String name = (String) exp.getValue(context);
		System.out.println(name);
	}
}
