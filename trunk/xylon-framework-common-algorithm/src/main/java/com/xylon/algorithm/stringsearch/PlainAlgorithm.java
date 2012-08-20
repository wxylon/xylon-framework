/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.algorithm.stringsearch;

import org.apache.commons.lang.StringUtils;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-4
 */
public class PlainAlgorithm {
	public static int plainFor(String sources, String searchTarget){
		int count = 0;
		if(StringUtils.isEmpty(sources) || StringUtils.isEmpty(searchTarget)){
			return 0;
		}
		for(int i = 0; i < sources.length(); i++){
			for(int j = 0; j < searchTarget.length(); j++){
				if(sources.charAt(i) != searchTarget.charAt(j)){
					break;
				}else if(j+1 == searchTarget.length()){
					count++;
				}else{
					i++;
				}
			}
		}
		return count;
	}
	
	public static int plainWhile(String sources, String searchTarget){
		int count = 0;
		if(StringUtils.isEmpty(sources) || StringUtils.isEmpty(searchTarget)){
			return 0;
		}
		
		
		
		return count;
	}
}

