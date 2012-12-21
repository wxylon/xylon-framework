/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.algorithm.sifter;

/**
 * http://kmplayer.iteye.com/blog/746939
 * @author wxylon@gmail.com
 * @date 2012-8-9
 */
public class Sifter {

	static int N = 10;
	// 临时表
	static int[][] dp = new int[N + 1][6 * N + 1];

	public static int sifter(){

	    for (int i = 1; i < N; i++){
	        for (int j = i; j <= 6*i; j++){
	            if (i == 1)
	                dp[i][j] = 1;
	            else{
	                for (int k = 1; k <=6 ; k++)
	                    dp[i][j] += dp[i-1][j-k];
	            }
	        }
	    }

	    for (int i = 1; i < N; i++){
	        for (int j = i; j <= 6*i; j++)
	        	System.out.println("<<" + j + "<< -> << " + dp[i][j] + "<<  ");
//	            cout << j << "->" << dp[i][j] << " ";
//	        cout << endl;
	    }
	    return 0;
	}
	
	public static void  main(String[] args){
		sifter();
	}
}
