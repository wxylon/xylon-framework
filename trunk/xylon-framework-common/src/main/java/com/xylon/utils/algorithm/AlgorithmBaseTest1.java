package com.xylon.utils.algorithm;

public class AlgorithmBaseTest1 {
	
	public static void main(String[] args) {
		test();
	}
	
	
	public static void test(){
		int n = 100, count1 = 0, count2 = 0, count3 = 0, count = 0;
		for(int i = 0; i < n; i++){
			// 0 + 1 + 2 + ... (n - 1)
			count1++;
			for(int j = 0; j < i; j++){
				// 0 + 1 + 2 + 3 + ... + n-2
				count2++;
				for(int k = 0; k <= j; k++){
					// (0) + ( 0 + 1) + ( 0 + 1 + 2) + ( 0 + 1 + 2 + 3) + ( 0 + 1 + 2 + 3 + ... + (n - 3))
					count3++;
				}
			}
		}
		for(int i = 0; i < n; i++){
			count += (i * (i + 1) / 2);
		}
		//int j1 = n * (n + 1) / 2;
		
		System.out.println(count1);
		System.out.println(count2);
		System.out.println(count3);
		System.out.println(count);
	}
}
