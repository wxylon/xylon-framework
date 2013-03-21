/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.web.common.xml;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.xylon.framework.po.Content;
import com.xylon.framework.po.Picture;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-2
 */
public class XstreamXMLTest {
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
		StringWriter w = new StringWriter();
		PrintWriter pw = new PrintWriter(w);
		XStream xs = new XStream();
		//如果不调用alias方法，进行别名，XStream默认是将包名+类名作为节点名称，也就是<com.xcy.Subscribe>.....</com.xcy.Subscribe>  
		xs.alias("list", List.class);
		xs.alias("Content", Content.class);
		xs.alias("Picture", Picture.class);
		xs.toXML(contents, pw);
		pw.flush();
		System.out.println(w.toString());
	}
	
	@Test
	public void testJson2Bean() throws Exception{
		
		StringWriter w = new StringWriter();
		PrintWriter pw = new PrintWriter(w);
		XStream xs = new XStream();
		xs.autodetectAnnotations(true);
		xs.toXML(contents, pw);
		pw.flush();
		System.out.println(w.toString());
		
		
		xs = new XStream();
		xs.autodetectAnnotations(true);
		xs.alias("list", List.class);
		xs.alias("Content", Content.class);
		xs.alias("Picture", Picture.class);
		List<Content> sources = (List<Content>) xs.fromXML(w.toString());
		
		for(int i = 0 ; i < sources.size();  i++){
			Content content = (Content)sources.get(i);
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

