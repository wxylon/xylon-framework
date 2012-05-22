package com.xylon.utils.algorithm;

import com.xylon.BaseLog;

/**
 * 二分查找
 */
public class Binarysearch extends BaseLog{
	
	/**
	 * <b><a href="http://blog.csdn.net/v_july_v/article/details/7093204">http://blog.csdn.net/v_july_v/article/details/7093204</a></b><br/>
	 * middle= (left+right)>>1; 这样的话left与right的值比较大的时候，其和可能溢出<br/>
	 * 首先要把握下面几个要点：  <br/>
	 * right=n-1 => while(left <= right) => right=middle-1;  <br/>
	 * right=n   => while(left <  right) => right=middle;  <br/>
	 * middle的计算不能写在while循环外，否则无法得到更新。  
	 * @param ids
	 * @param id
	 * @return
	 */
	public static int binarysearch(int[] ids, int id){
	 	int left = 0;  
	    int right = ids.length-1;
	    int middle = 0;
	    //如果这里是int right = n 的话，那么下面有两处地方需要修改，以保证一一对应：  
	    //1、下面循环的条件则是while(left < right)  
	    //2、循环内当array[middle]>value 的时候，right = mid  
	  
	    while (left <= right)  {            //循环条件，适时而变
//	        middle = left + ((right - left) >> 1);  //防止溢出，移位也更高效。同时，每次循环都需要更新。  
	    	//比上面少了已给运算，更高效
	    	//  >>> 无符号移位
	    	middle = (left + right) >>> 1;  
	        if(log.isDebugEnabled()){
	        	log.debug("left:" + left +"; right:" + right + "; middle:" + middle + "; ");
	        }
	        if(ids[middle] > id){  
	            right = middle - 1;   //right赋值，适时而变  
	        } else if(ids[middle] < id){  
	            left= middle + 1;  
	        } else  
	            return middle;    
	        //可能会有读者认为刚开始时就要判断相等，但毕竟数组中不相等的情况更多  
	        //如果每次循环都判断一下是否相等，将耗费时间  
	    }  
	    return -1;
	}
}
