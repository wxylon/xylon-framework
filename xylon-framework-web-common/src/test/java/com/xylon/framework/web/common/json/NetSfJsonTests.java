/**
* Copyright(c) 2004-2012, 360buy.com  All Rights Reserved
*/

package com.xylon.framework.web.common.json;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.xylon.framework.po.Content;
import com.xylon.framework.po.Picture;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

/**
 * @author wangx
 * @date 2012-7-30
 */
public class NetSfJsonTests{
	
	public List<Content> contents = new ArrayList<Content>();
	
	@Before
	public void run() throws Exception{
		for(int i = 0; i < 5; i++){
			Content content = new Content();
			content.setAuthor("作者-" + i);
			content.setId("ID-" + i);
			content.setPath("路径-" + i);
			content.setTitle("标题-" + i);
			content.setPrice(BigDecimal.valueOf(2333434343334.1231321));
			List<Picture> pictures = new ArrayList<Picture>();
			for(int k = 0; k <= i; k++){
				Picture picture = new Picture();
				picture.setImgPath("path" + k);
				picture.setDescription("注释 " + k);
				pictures.add(picture);
				picture.setPrice(BigDecimal.valueOf(2333434343334.1231321));
			}
			content.setPictures(pictures);
			contents.add(content);
		}
	}
	
	@Test
	public void testBean2Json() throws Exception{
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(contents);
		System.out.println(jsonArray.toString());
		
		JSONArray ja = JSONArray.fromObject(jsonArray.toString());
		Map<String, Class<Picture>> classMap = new HashMap<String, Class<Picture>>();
		classMap.put("pictures", Picture.class);
		
		List<Content> contents = JSONArray.toList(ja, Content.class, classMap);
		for(int i = 0 ; i < contents.size();  i++){
			Content content = (Content)contents.get(i);
			System.out.print(content.getAuthor()+"\t\t");
			System.out.print(content.getPrice()+"\t\t");
			for(Picture picture : content.getPictures()){
				System.out.print(picture.getImgPath()+"\t\t");
				System.out.print(picture.getPrice()+"\t\t");
			}
			System.out.println();
		}
	}
	
//	@Test
	public void testJson2Bean() throws Exception{
		String s = "[{\"author\":\"作者-0\",\"id\":\"ID-0\",\"path\":\"路径-0\",\"pictures\":[{\"description\":\"注释 0\",\"imgPath\":\"path0\",\"price\":234.1231321}],\"price\":234.1231321,\"title\":\"标题-0\"},{\"author\":\"作者-1\",\"id\":\"ID-1\",\"path\":\"路径-1\",\"pictures\":[{\"description\":\"注释 0\",\"imgPath\":\"path0\",\"price\":234.1231321},{\"description\":\"注释 1\",\"imgPath\":\"path1\",\"price\":234.1231321}],\"price\":234.1231321,\"title\":\"标题-1\"},{\"author\":\"作者-2\",\"id\":\"ID-2\",\"path\":\"路径-2\",\"pictures\":[{\"description\":\"注释 0\",\"imgPath\":\"path0\",\"price\":234.1231321},{\"description\":\"注释 1\",\"imgPath\":\"path1\",\"price\":234.1231321},{\"description\":\"注释 2\",\"imgPath\":\"path2\",\"price\":234.1231321}],\"price\":234.1231321,\"title\":\"标题-2\"},{\"author\":\"作者-3\",\"id\":\"ID-3\",\"path\":\"路径-3\",\"pictures\":[{\"description\":\"注释 0\",\"imgPath\":\"path0\",\"price\":234.1231321},{\"description\":\"注释 1\",\"imgPath\":\"path1\",\"price\":234.1231321},{\"description\":\"注释 2\",\"imgPath\":\"path2\",\"price\":234.1231321},{\"description\":\"注释 3\",\"imgPath\":\"path3\",\"price\":234.1231321}],\"price\":234.1231321,\"title\":\"标题-3\"},{\"author\":\"作者-4\",\"id\":\"ID-4\",\"path\":\"路径-4\",\"pictures\":[{\"description\":\"注释 0\",\"imgPath\":\"path0\",\"price\":234.1231321},{\"description\":\"注释 1\",\"imgPath\":\"path1\",\"price\":234.1231321},{\"description\":\"注释 2\",\"imgPath\":\"path2\",\"price\":234.1231321},{\"description\":\"注释 3\",\"imgPath\":\"path3\",\"price\":234.1231321},{\"description\":\"注释 4\",\"imgPath\":\"path4\",\"price\":234.1231321}],\"price\":234.1231321,\"title\":\"标题-4\"}]";
		JSONArray ja = JSONArray.fromObject(s);
		
		Map<String, Class<Picture>> classMap = new HashMap<String, Class<Picture>>();
		classMap.put("pictures", Picture.class);
		List<Content> results = JSONArray.toList(ja, Content.class, classMap);
		for(int i = 0 ; i < results.size();  i++){
			Content content = (Content)results.get(i);
			System.out.print(content.getAuthor()+"\t\t");
			System.out.print(content.getPrice()+"\t\t");
			for(Picture picture : content.getPictures()){
				System.out.print(picture.getImgPath()+"\t\t");
				System.out.print(picture.getPrice()+"\t\t");
			}
			System.out.println();
		}
	}
	
	
	
	
}

