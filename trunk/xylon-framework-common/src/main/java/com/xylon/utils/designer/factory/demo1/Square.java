/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class Square extends Shape{
	public void draw() {
		System.out.println("It will draw a square.");
	}

	public void erase() {
		System.out.println("It will erase a square.");
	}

	// 构造函数
	public Square(String aName) {
		super(aName);
	}

}
