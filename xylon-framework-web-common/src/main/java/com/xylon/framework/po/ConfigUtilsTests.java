/**
* Copyright(c) 2004-2012, 360buy.com  All Rights Reserved
*/

package com.jd.fms.ykauto.common;

import java.util.Map;

import org.junit.Test;

/**
 * @author wangx
 * @date 2012-8-29
 */
public class ConfigUtilsTests {
	
	@Test
	public void test(){
		ConfigUtils configUtils = ConfigUtils.getInstance("D:\\csv2sqlserver\\config.ini");
		configUtils.setPropValue("require_file", "file_name", "1111.zip");
		
		Map<String, Map<String, String>> propMap = configUtils.getPropMap();
		for(Map.Entry<String, Map<String, String>> map : propMap.entrySet()){
			for(Map.Entry<String, String> entry : map.getValue().entrySet()){
				System.out.println(map.getKey() + "--->" + entry.getKey() + "--->" + entry.getValue());
			}
		}
	}
}

