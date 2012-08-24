/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.web.common.poi.jxls;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xylon.framework.poi.jxls.JxlsUtils;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-24
 */
public class PoiJxlsTests {
	private String source = "D:\\maven-xylon\\xylon-framework-2\\xylon-framework-web-common\\src\\test\\java\\com\\xylon\\framework\\web\\common\\poi\\jxls\\PoiJxlsTests.xlsx";
	private String target = "D:\\maven-xylon\\xylon-framework-2\\xylon-framework-web-common\\src\\test\\java\\com\\xylon\\framework\\web\\common\\poi\\jxls\\PoiJxlsTestsResult.xlsx";
	
	@Test
	public void testJxls(){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("testif", "²âÊÔÒ»¸öÀý×Ó");
		data.put("testTrinocular", "");
		JxlsUtils.generateExcel(source, data, target);
	}
}

