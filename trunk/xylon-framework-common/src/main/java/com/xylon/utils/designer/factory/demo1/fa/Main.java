/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.factory.demo1.fa;

import com.xylon.utils.designer.factory.demo1.NoThisShapeException;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class Main {
	public static void main(String[] args) throws NoThisShapeException {
		ShapeFactory.anOperation("Shape one", "circle");
		ShapeFactory.anOperation("Shape two", "square");
		ShapeFactory.anOperation("Shape three", "delta");
	}

}
