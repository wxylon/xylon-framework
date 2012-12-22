/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.designerPatterns.factory.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-1
 */
public class Main {
	public static void main(String[] args) {
		ShapeFactory sf1 = new SquareFactory();
		ShapeFactory sf2 = new CircleFactory();
		sf1.anOperation("Shape one");
		sf2.anOperation("Shape two");
	}

}
