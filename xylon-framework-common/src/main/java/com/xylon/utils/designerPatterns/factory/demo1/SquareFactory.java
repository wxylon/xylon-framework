/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class SquareFactory extends ShapeFactory {

	@Override
	protected Shape factoryMethod(String aName) {
		 return new Square(aName + " (created by SquareFactory)");
	}

}

