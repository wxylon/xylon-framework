package com.xylon.utils.algorithm;

import com.xylon.BaseLog;

/**
 * http://baike.baidu.com/view/115472.htm#2
 * 快速排序
 * @author wxylon@gmail.com
 * @date 2012-5-24
 */
public class FastSort extends BaseLog{
	
	
	/**
	 * 对数组objects从 p 到 r 范围的值(使用快速排序算法)进行排序.<br/>
	 * 其中必须 p >= r,才能进行有效的排序.<br/>
	 * @param objects		待排序的数组
	 * @param p				数组排序范围起始下标
	 * @param r				数组排序范围截止下标
	 * @author wxylon@gmail.com
	 * @date 2012-5-24
	 */
	private static int partition(Object[] objects, int p, int r){
		debug("快排前范围：<" + p + ", " +r+">", objects);
		/**就以第最后一个元素为界，对 [p, r] 内的元素进行划分*/
		Object x = objects[r];
		/**标识小于x的最小索引位置, 初次i的值应该为-1*/
		int i = p - 1;
		for(int j = p; j < r; j++){
			if(x.hashCode() >= objects[j].hashCode()){
				i = i + 1;
				swap(objects, j, i);
				debug("源下标:"+j+"; 目标下标："+i+"  交换", objects);
			}else{
				debug("源下标:"+j+"; 目标下标："+i+"未交换", objects);
			}
		}
		swap(objects, i+1, r);
		debug("快排后范围：<" + p + ", " +r+">", objects);
		return i + 1;
	}
	
	private static void sort(Object[] objects, int p, int r){
		if(p >= r){
			return;
		}
		int q = partition(objects, p, r);
		if(r > p){
			/**排左边的元素*/
			sort(objects, p, q - 1);
			/**排右边的元素*/
			sort(objects, q + 1, r);
		}
	}
	
	public static void sort(Object[] objects){
		sort(objects, 0, objects.length-1);
	}
	
	/**
	 * 对数组的做交换数据操作
	 * @param objects
	 * @param source
	 * @param target
	 * @author wxylon@gmail.com
	 * @date 2012-5-24
	 */
	public static void swap(Object[] objects, int source, int target){
		Object temp = objects[source];
		objects[source] = objects[target];
		objects[target] = temp;
	}
}

