package com.xylon.utils.algorithm;

/**
 * 插入排序算法
 * @author wangxiong
 */
public class InsertionSort {
	public static void sort(Object[] objects){
		int n = objects.length;
		int j;
		Object temp;
		for(int i = 1; i < n; i++){
			temp = objects[i];
			j = i - 1;
			while(j >= 0 && objects[j].hashCode() > temp.hashCode()){
				objects[j+1] = objects[j];
				j--;
			}
			objects[j+1] = temp;
		}
	}
}