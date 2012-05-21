package com.xylon.utils.algorithm;

import com.xylon.utils.Me;

/**
 * 插入排序算法
 * @author wangxiong
 */
public class InsertionSort {
	public static void sort(Me[] mes){
		int n = mes.length;
		int j;
		Me temp;
		for(int i = 1; i < n; i++){
			temp = mes[i];
			j = i - 1;
			while(j >= 0 && mes[j].hashCode() > temp.hashCode()){
				mes[j+1] = mes[j];
				j--;
			}
			mes[j+1] = temp;
		}
	}
}