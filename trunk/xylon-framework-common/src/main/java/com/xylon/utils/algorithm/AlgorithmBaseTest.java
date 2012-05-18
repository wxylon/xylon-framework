package com.xylon.utils.algorithm;

public class AlgorithmBaseTest {
	
	
	
	public static void main(String[] args) {
		for(int k = 10000; k < 10000000; k += 10000){
			test1(k);
			test2(k);
		}
	}
	
	public static void test1(int n){
		long start = System.nanoTime();
		int i, sum = 0;
		for(i = 1; i <= n; i++){
			sum = sum + i;
		}
		System.out.println("sum = " + sum + "; time = " + (System.nanoTime()-start));
	}
	
	public static void test2(int n){
		long start = System.nanoTime();
		int sum = 0;
		sum = (1 + n) * n / 2;
		System.out.println("sum = " + sum + "; time = " + (System.nanoTime()-start));
	}
}
