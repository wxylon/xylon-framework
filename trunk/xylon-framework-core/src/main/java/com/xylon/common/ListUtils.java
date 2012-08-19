/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-17
 */
public class ListUtils {
	
	/**
	 * 
	 * @param <T>
	 * @param sources
	 * @param groupByAttribute
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @author wxylon@gmail.com
	 * @date 2012-8-17
	 */
	public static <T> List<List<T>> group(List<T> sources, final String groupByAttribute) throws Exception{
		List<List<T>> results = new ArrayList<List<T>>();
		if(null == groupByAttribute || groupByAttribute.trim().length() == 0){
			throw new NullPointerException("groupByAttribute must not be " + groupByAttribute);
		}
		
		if(null == sources || sources.isEmpty()){
			return results;
		}
		
		Map<Object, List<T>> values = new HashMap<Object, List<T>>();
		/**保存属性的次序*/
		final Map<String, Integer> sort_by = new HashMap<String, Integer>();
		
		int index = 0;
		for(T t : sources){
			/**为空的对象，将对忽略掉*/
			if(null == t){
				continue;
			}
			/**根据指定属性进行分组*/
			String value = BeanUtils.getProperty(t, groupByAttribute);
			List<T> temp = values.get(value);
			if(temp == null){
				temp = new ArrayList<T>();
				values.put(value, temp);
			}
			temp.add(t);
			
			/**根据原集合的顺序，对 groupByAttribute属性进行次序保存*/
			Integer current_index = sort_by.get(value);
			if(current_index == null){
				sort_by.put(value, index++);
			}
		}
		results.addAll(values.values());
		
		for(Entry<String, Integer> entry : sort_by.entrySet()){
			results.add(entry.getValue(), values.get(entry.getKey()));
		}
		return results;
	}
	
	public static <T> void summary(List<T> sources, T summaryObject){
		if(null == summaryObject){
			throw new NullPointerException("summaryObject must not be " + summaryObject);
		}
		
		Field[] fields = summaryObject.getClass().getDeclaredFields();
		
		Annotation[] annotations = summaryObject.getClass().getAnnotations();
		for(Annotation annotation : annotations){
			if(annotation instanceof OperatorControl){
				OperatorControl control = (OperatorControl)annotation;
				Computing[] computings = control.computing();
				for(Computing computing : computings){
					System.out.println(computing);
				}
			}else if(annotation instanceof Operator){
				Operator control = (Operator)annotation;
				CulomnComputing culomnComputing = control.culomnComputing();
				System.out.println(culomnComputing);
			}
		}
		
		
		for(int i = 0; i < sources.size()-1; i++){
		}
	}
}

