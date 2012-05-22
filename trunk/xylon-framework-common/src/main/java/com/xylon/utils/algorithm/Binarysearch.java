package com.xylon.utils.algorithm;

import com.xylon.BaseLog;

/**
 * 二分查找
 */
public class Binarysearch extends BaseLog{
	
	/**
	 * 首先要把握下面几个要点：  
	 * index 为当前位置
	 * start=0 	=> if(ids[index] > id) 	=> end = index; 
	 * end=n 	=> if(ids[index] < id) 	=> start = index; 
	 * middle的计算不能写在while循环外，否则无法得到更新。 
	 * @param ids
	 * @param id
	 * @return
	 */
	public static int binarysearch(int[] ids, int id){
		int end = ids.length;
		int start = 0;
		int index = 0;
		
		while(true){
			index = (start + end) >> 1;
			if(ids[index] > id){
				end = index;
			}else if(ids[index] < id){
				start = index;
			}else{
				return ids[index];
			}
			debug(index+"", new Object[0]);
		}
	}
}
