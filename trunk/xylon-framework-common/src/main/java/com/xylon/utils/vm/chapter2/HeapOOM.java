/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Arguments: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * VM Args : -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 * @author wxylon@gmail.com
 * @date 2012-12-8
 */
public class HeapOOM {
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>();
		
		while(true){
			list.add(new OOMObject());
		}
		
	}
}

