/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.algorithm.sifter;

/**
 * http://bylijinnan.iteye.com/blog/1428099
 * @author wxylon@gmail.com
 * @date 2012-8-10
 */
public class ProbabilityOfDice {

	/**
	 * Q67 n个骰子的点数
	 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为S。输入n，打印出S的所有可能的值出现的概率。
	 * 在以下求解过程中，我们把骰子看作是有序的。
	 * 例如当n=2时，我们认为（1，2）和（2，1）是两种不同的情况
	 */
	private static int MAX=6;
	public static void main(String[] args) {
		int n=2;
		printProbabilityOfDice(n);//solution 1,use recursion
		System.out.println("============");
		printProbabilityOfDice2(n);//solution 2,use DP
	}

	public static void printProbabilityOfDice(int n){
		if(n<1){
			return;
		}
		double total=Math.pow(MAX, n); 
		int len=n*MAX-n*1+1;//the sum of n dices is from n*1 to n*MAX
		int[] times=new int[len];
		for(int i=1;i<=MAX;i++){//initial the first dice.
			probabilityOfDice(n,i,n,0,times);//count the times of each possible sum
		}
		for(int i=0;i<len;i++){
			System.out.println((i+n)+","+times[i]+"/"+total);
		}
		
	}
	public static void probabilityOfDice(int n,int curDiceValue,int numOfDices,int curSum,int[] times){
		if(numOfDices==1){
			int sum=curSum+curDiceValue;
			times[sum-n]++;//n*1 to n*MAX <---> 0 to len
		}else{
			int sum=curSum+curDiceValue;
			for(int i=1;i<=MAX;i++){
				probabilityOfDice(n,i,numOfDices-1,sum,times);
			}
		}
	}
	
	/*
有k-1个骰子时，再增加一个骰子，这个骰子的点数只可能为1、2、3、4、5或6。那k个骰子得到点数和为n的情况有：
(k-1,n-1)：第k个骰子投了点数1
(k-1,n-2)：第k个骰子投了点数2
(k-1,n-3)：第k个骰子投了点数3
....
(k-1,n-6)：第k个骰子投了点数6
在k-1个骰子的基础上，再增加一个骰子出现点数和为n的结果只有这6种情况！
所以：f(k,n)=f(k-1,n-1)+f(k-1,n-2)+f(k-1,n-3)+f(k-1,n-4)+f(k-1,n-5)+f(k-1,n-6)
初始化：有1个骰子，f(1,1)=f(1,2)=f(1,3)=f(1,4)=f(1,5)=f(1,6)=1。
	 */
	public static void printProbabilityOfDice2(int n){
		if(n<1){
			return;
		}
		double total=Math.pow(MAX, n); 
		int maxSum=n*MAX;
		double[][] f=new double[n+1][n*MAX+1];
		for(int i=1;i<=MAX;i++){
			f[1][i]=1;
		}
		for(int k=2;k<=n;k++){
			for(int sum=n;sum<=maxSum;sum++){
				for(int i=1;sum-i>=1&&i<=MAX;i++){
					f[k][sum]+=f[k-1][sum-i];
				}
			}
		}
		
		for(int sum=n;sum<=maxSum;sum++){
			System.out.println(sum+","+f[n][sum]+"/"+total);
		}
	}
}



