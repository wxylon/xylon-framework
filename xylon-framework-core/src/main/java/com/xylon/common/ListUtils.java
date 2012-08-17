/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-17
 */
public class ListUtils {
	public static <T> List<List<T>> group(List<T> sources, final String groupByAttribute){
		List<List<T>> results = new ArrayList<List<T>>();
		if(null == groupByAttribute || groupByAttribute.trim().length() == 0){
			throw new NullPointerException("groupByAttribute must not be " + groupByAttribute);
		}
		
		if(null == sources || sources.isEmpty()){
			return results;
		}
		
		Map<Object, List<T>> values = new HashMap<Object, List<T>>();
		Map<String, Integer> sort_by = new HashMap<String, Integer>();
		try {
			int index = 0;
			for(T t : sources){
				/***/
				String value = BeanUtils.getProperty(t, groupByAttribute);
				List<T> temp = values.get(value);
				if(temp == null){
					temp = new ArrayList<T>();
					values.put(value, temp);
				}
				temp.add(t);
				
				Integer current_index = sort_by.get(value);
				if(current_index == null){
					sort_by.put(value, index);
					index++;
				}
			}
			results.addAll(values.values());
			Collections.sort(values, new Comparator<List<T>>(){
				public int compare(List<T> o1, List<T> o2) {
					if(null == o1 || o1.isEmpty() || null == o2 || o2.isEmpty()){
						return 0;
					}else{
						String value1 = BeanUtils.getProperty(o1.get(0), groupByAttribute);
						String value2 = BeanUtils.getProperty(o2.get(0), groupByAttribute);
						
						
						return sort_by.get(value1) - sort_by.get(value1);
					}
				}
			});
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return new ArrayList<List<T>>(values.values());
	}
}

