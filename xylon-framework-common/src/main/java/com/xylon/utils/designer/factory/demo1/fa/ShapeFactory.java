/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designer.factory.demo1.fa;

import com.xylon.utils.designer.factory.demo1.NoThisShapeException;
import com.xylon.utils.designer.factory.demo1.Shape;
import com.xylon.utils.designer.factory.demo1.Square;
import com.xylon.utils.designer.factory.demo1.Circle;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class ShapeFactory {
	private static Shape s;

	private ShapeFactory() {
	}

	static Shape factoryMethod(String aName, String aType) throws NoThisShapeException {
		if (aType.compareTo("square") == 0)
			return new Square(aName);
		else if (aType.compareTo("circle") == 0)
			return new Circle(aName);
		else
			throw new NoThisShapeException(aType);
	}

	// 在anOperation中定义Shape的一系列行为
	static void anOperation(String aName, String aType) throws NoThisShapeException {
		s = factoryMethod(aName, aType);
		System.out.println("The current shape is: " + s.name);
		s.draw();
		s.erase();
	}

}
