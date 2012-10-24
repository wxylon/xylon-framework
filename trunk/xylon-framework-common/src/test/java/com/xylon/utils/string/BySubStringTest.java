/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * http://www.ibm.com/developerworks/cn/java/j-lo-optmizestring/#_ 表 _1._Java
 * http://blog.csdn.net/bzwm/article/details/5844045
 * http://blog.csdn.net/zmywhhit/article/details/6881609
 * @author wangx
 * @date 2012-10-24
 */
public class BySubStringTest {
	public static void main(String...args) {
        List<String> handler = new ArrayList<String>();
        for(int i = 0; i < 100000; i++) {
            Huge h = new Huge();
            handler.add(h.getSubString(1, 5));
        }
    }
	static class Huge {
		private String str = new String(new char[100000]);
	    public String getSubString(int begin, int end) {
//	    	return str.substring(begin, end);
	        return new String(str.substring(begin, end));
	    }
	}
	
//	@Test
	public void testSubString(){
		String hello="hello world";
		String world=hello.substring(0);
		System.err.println(hello==world);
		System.err.println(hello.equals(world));
	}
	
//	@Test
	public void testString(){
		String hello="abcde";
		String a0 = "a";
		String a1 = hello.substring(0, 1);
		System.out.println(a0 == a1);
		String a2 = new String(a1);
		System.out.println(a0 == a2);
		System.out.println(a0.intern() == a1.intern());
		System.out.println(a0.intern() == a2.intern());
	}
	
	/**
	 * http://hi.baidu.com/chen_767/item/86e4dd619b568b2569105bf5
	 * @author wxylon@gmail.com
	 * @date 2012-10-24
	 */
	@Test
	public void testString02(){
		/*1. String s = "abc"， 虚拟机首先会检查String池里有没有"abc"对象(通过equals方法)
		// 如果有，直接返回引用，如果没有，会在池里创建一个“abc”对象，并返回引用
		*/
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1==s2);    // result: true
		
	}
	
	
}

