/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Arguments: -XX:PermSize=10M -XX:MaxPermSize=10M
 * @author wxylon@gmail.com
 * @date 2012-12-22
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}

