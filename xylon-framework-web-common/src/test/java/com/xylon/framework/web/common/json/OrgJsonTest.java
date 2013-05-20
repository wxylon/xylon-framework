/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.web.common.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2013-5-20
 */
public class OrgJsonTest {
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
	public void testArray2Json() throws Exception{
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(ts);
		String s = jsonArray.toString();
		System.out.println(s);
	}
	
	@Test
	public void testBean2Json() throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("aa", new String[]{"123","345"});
		String s1 = jsonObject.toString();
		System.out.println(s1);
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

