/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.builder.one;

/**
 * 工程队
 * @author wxylon@gmail.com
 * @date 2012-11-8
 */
public interface HouseBuilder {
	//修地板 
    public void makeFloor(); 
    //修墙 
    public void makeWall(); 
    //修屋顶 
    public void makeHousetop(); 
    public House getHouse(); 
}

