/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public abstract class ShapeFactory {
	
	protected abstract Shape factoryMethod(String aName);
	  // 在anOperation中定义Shape的一系列行为
	public void anOperation(String aName){
	    Shape s = factoryMethod(aName);
	    System.out.println("The current shape is: " + s.name);
	    s.draw();
	    s.erase();
	}

}

