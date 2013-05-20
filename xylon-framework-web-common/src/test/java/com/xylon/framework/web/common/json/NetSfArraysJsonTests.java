/**
* Copyright(c) 2004-2012, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.common.json;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * @author wangx
 * @date 2012-7-30
 */
public class NetSfArraysJsonTests{
	
	List<T> ts = new ArrayList<T>();
	
	{
		for(int i = 0; i < 10; i++){
			T t = new T();
			t.setName("ceshi"+i);
			t.setArgs(new String[]{"123","345"});
			ts.add(t);
		}
	}
	
	@Test
	public void testBean2Json() throws Exception{
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(ts);
		System.out.println(jsonArray.toString());
		
		JsonConfig jsonConfig = new JsonConfig();
		
		JSONArray ja = JSONArray.fromObject(jsonArray.toString(), jsonConfig);
		List<T> contents = JSONArray.toList(ja, jsonConfig);
		
		for(int i = 0 ; i < contents.size();  i++){
			T content = (T)contents.get(i);
			System.out.print(content);
			for(String picture : content.getArgs()){
				System.out.print(picture);
			}
			System.out.println();
		}
	}
	
	public static class T{
		private String name;
		private String[] args;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String[] getArgs() {
			return args;
		}
		public void setArgs(String[] args) {
			this.args = args;
		}
		@Override
		public String toString() {
			return "T [name=" + name + ", args=" + Arrays.toString(args) + "]";
		}
	}	
}


