package com.xylon.utils.algorithm;

import com.xylon.BaseLog;

/**
 * 冒泡排序（Bubble Sort）一种交换排序，它的基本思想是：两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止。
 * 冒泡的实现在细节上可以很多种变化，我们将分别就3种不同的冒泡实现代码，来讲解冒泡排序的思想。
 * @author wangxiong
 */
public class BubbleSort extends BaseLog{
	public static void sort(Object[] args){
		Object temp;
		int j = 1;
		for(int i = 0; i < args.length; i++){
			j = 1;
			while(j < args.length){
				if(args[j-1].hashCode() > args[j].hashCode()){
					temp = args[j-1];
					args[j-1] = args[j];
					args[j] = temp;
				}
				debug("第  "+i+"  趟排序中", args);
				j++;
			}
		}
	}
}
