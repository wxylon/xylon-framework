package com.xylon.utils.arithmetic;

public class BaseTest {
	public static void main(String[] args) {
		test01();
	}
	
	public static void test01(){
		int i = 1, j = 10;
		do{
			if(i > j){
				continue;
			}
			j--;
			System.out.println("i=" + i + "; j=" + j);
		}while(++i < 6);
		System.out.println("i=" + i + "; j=" + j);

	}
}
