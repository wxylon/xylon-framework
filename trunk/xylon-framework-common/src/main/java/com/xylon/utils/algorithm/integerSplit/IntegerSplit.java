/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.algorithm.integerSplit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxylon@gmail.com
 * @date 2012-7-25
 */
public class IntegerSplit {
	
	public static void enumerateInt(int result){
		StringBuilder builder = new StringBuilder();
		List<Integer> ints = new ArrayList<Integer>(result);
		int sum = 0;
		for(int i = result; i > 0; i--){
			for(int j = i; j > 0; j--){
				sum += j;
				if(sum == result){
					ints.add(j);
					break;
				}else if(sum > result){
					sum -= j;
					continue;
				}else{
					ints.add(j);
				}
			}
		}
	}
}

