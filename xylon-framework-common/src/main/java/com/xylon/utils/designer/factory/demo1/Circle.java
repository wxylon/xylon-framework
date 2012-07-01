/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designer.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
//圆形子类
class Circle extends Shape {
	public void draw() {
		System.out.println("It will draw a circle.");
	}
	public void erase() {
		System.out.println("It will erase a circle."); 
	}
	// 构造函数
	public Circle(String aName){
		super(aName);
	}
}


