/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designerPatterns.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class CircleFactory extends ShapeFactory {
	// 重载factoryMethod方法,返回Circle对象
	protected Shape factoryMethod(String aName) {
		return new Circle(aName + " (created by CircleFactory)");
	}
}
