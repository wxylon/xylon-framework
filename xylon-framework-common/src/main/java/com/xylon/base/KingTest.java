/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.base;

/**
 * @author wxylon@gmail.com
 * @date 2012-9-28
 */
public class KingTest {
	public static void main(String[] args) {
		int size = 30;
		for(int i = 1; i < size; i+=2){
			for(int j = 1; j <= (size-i)/2; j++){
				System.out.print(" ");
			}
			for(int j = 1; j <= i; j++){
				System.out.print("&");
			}
			System.out.println();
		}
	}
}

