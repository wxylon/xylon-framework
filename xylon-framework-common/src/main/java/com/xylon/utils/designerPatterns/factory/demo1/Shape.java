/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public abstract class Shape {
	
	public String name;
	
	public Shape(String aName){
		name = aName;
	}
	// 勾画shape
	public abstract void draw();
	// 擦去 shape
	public abstract void erase();
}

