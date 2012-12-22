/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.builder.one;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-8
 */
public class PingFangBuilder implements HouseBuilder { 
    House house = new House(); 
 
    public void makeFloor() { 
        house.setFloor("平房-->地板"); 
    } 
    public void makeHousetop() { 
        house.setHousetop("平房-->房顶"); 
    } 
    public void makeWall() { 
        house.setWall("平房-->墙"); 
    } 
    public House getHouse() { 
        return house; 
    } 
}

