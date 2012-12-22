/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.designerPatterns.builder.one;

/**
 * http://www.blogjava.net/fancydeepin/archive/2012/08/05/384783.html
 * http://www.java63.com/design_pattern/bulider_pattern.html
 * @author wxylon@gmail.com
 * @date 2012-11-8
 */
public class MainClass {
	public static void main(String[] args) { 
	    //  客户直接造房子 
	    //  House house = new House(); 
	    //  house.setFloor("地板"); 
	    //  house.setWall("墙"); 
	    //  house.setHousetop("屋顶"); 
	 
        //由工程队来修 
        HouseBuilder builder = new GongyuBuilder(); 
        //设计者来做 
        HouseDirector director = new HouseDirector(); 
        director.makeHouse(builder); 
 
        House house = builder.getHouse(); 
        System.out.println(house.getFloor()); 
        System.out.println(house.getWall()); 
        System.out.println(house.getHousetop()); 
    } 
}

