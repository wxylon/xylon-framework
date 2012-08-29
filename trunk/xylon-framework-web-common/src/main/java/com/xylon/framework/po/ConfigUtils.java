/**
 * Copyright(c) 2004-2012, 360buy.com  All Rights Reserved
 */

package com.jd.fms.ykauto.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用java读取配置文件 http://jackchen.ddjava.com/blog/blog.html?blogId=373
 * @author wangx
 * @date 2012-8-29
 */
public class ConfigUtils {
	private static Map<String, Map<String, String>> propMap = null;
	private static String path = "D:\\csv2sqlserver\\config.ini";
	private static ConfigUtils _instance = null;
	
	public static ConfigUtils getInstance(String path){
		if(null != path && path.length() != 0){
			ConfigUtils.path = path;
		}
		if(null == _instance){
			_instance = new ConfigUtils();
			 init(path);
		}
		return _instance;
	}
	
	private ConfigUtils(){}

	/** 初始化 */
	private static void init(String path) {
		propMap = new HashMap<String, Map<String, String>>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = "";
			String block = "";
			Map<String, String> current = null;
			while ((line = br.readLine()) != null) {
				if(!line.trim().startsWith("#") && line.trim().startsWith("[") && line.trim().endsWith("]")){
					block = line.trim().substring(1, line.trim().length()-1);
					current = propMap.get(block);
					if(null == current){
						current = new HashMap<String, String>();
						propMap.put(block, current);
					}
					continue;
				} 
				if (line.indexOf("=") != -1 && !line.trim().startsWith("#") && !line.trim().startsWith("//")) {
					System.out.println(line);
					String[] lineArr = line.split("=");
					if(lineArr.length == 1){
						current.put(lineArr[0].trim(), "");
					}else{
						current.put(lineArr[0].trim(), lineArr[1].trim());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取所有键值对组成的Map
	 * @param path  配置文件所在路径
	 * @return
	 */
	public Map<String, Map<String, String>> getPropMap() {
		return propMap;
	}

	/**
	 * 获取属性值
	 * @param path	 配置文件所在路径
	 * @param key   属性名
	 * @return
	 */
	public String getPropValue(String block, String key) {
		if(null == propMap.get(block)){
			return null;
		}
		return propMap.get(block).get(key);
	}

	/**
	 * 设置某个属性的值
	 * @param path
	 * @param key
	 * @param value
	 */
	public void setPropValue(String block, String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		setPropValues(block, map);
	}

	/**
	 * 根据Map设置属性
	 * @param path
	 * @param map
	 */
	public void setPropValues(String block, Map<String, String> map) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(path));

			StringBuffer sb = new StringBuffer();
			String line = "";
			// 读取文件并对替换文件内容
			boolean ifBlock = false;
			while ((line = br.readLine()) != null) {
				if(!line.trim().startsWith("#") && line.trim().startsWith("[") && line.trim().endsWith("]")){
					String block_temp = line.trim().substring(1, line.trim().length()-1);
					if(block.equals(block_temp)){
						ifBlock = true;
						continue;
					}else{
						ifBlock = false;
					}
				} 
				
				if (ifBlock && line.indexOf("=") != -1 && !line.trim().startsWith("#") && !line.trim().startsWith("//")) {
					String[] lineArr = line.split("=");
					String key = lineArr[0].trim();
					String newValue = map.get(key);
					if (null != newValue && !"".equals(newValue)) {
						sb.append(key).append(" = ").append(newValue).append("\r\n");
					} else {
						sb.append(line).append("\r\n");
					}
				} else {
					sb.append(line).append("\r\n");
				}
			}
			br.close();
			bw = new BufferedWriter(new FileWriter(path));
			// 写入文件
			bw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
