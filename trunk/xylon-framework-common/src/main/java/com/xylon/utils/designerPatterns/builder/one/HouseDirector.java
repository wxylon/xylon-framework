/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.builder.one;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-8
 */
public class HouseDirector {
	public void makeHouse(HouseBuilder builder) { 
        builder.makeFloor(); 
        builder.makeWall(); 
        builder.makeHousetop(); 
    } 

}

