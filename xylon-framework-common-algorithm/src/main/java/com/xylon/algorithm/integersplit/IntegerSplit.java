/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.algorithm.integersplit;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-14
 */
public class IntegerSplit {
	
	/**
	 * 群举法
	 * @param n
	 * @return
	 * @author wxylon@gmail.com
	 * @date 2012-8-14
	 */
	public static int integerSplitByEunm(int n){
		//StringBuilder sb = new StringBuilder();
		if(n > 63){
			throw new IllegalArgumentException(n+"");
		}
		int t = 0x1 << n;
		int count = 0;
		for(int i = 1; i < t; i++){
			int sum = 0;
			//sb.delete(0, sb.length());
			for(int j = 1; j <= n; j++){
				if(((i >> (j - 1)) & 0x1) == 0x1){
					sum += j;
					//sb.append(j + ", ");
				}
			}
			if(sum == n){
				count++;
				//System.out.println(sb.toString());
			}
		}
		return count;
	}
	
	/**
	 * 递归法
	 * @param max
	 * @param n
	 * @return
	 * @author wxylon@gmail.com
	 * @date 2012-8-15
	 */
	public static int recursive(int max, int n){
		if( n < 0 || max < 0){
			return 0;
		}
		if( n == 0){
			return 1;
		}
		return recursive(max - 1, n - max) + recursive(max-1, n);
	}
}

