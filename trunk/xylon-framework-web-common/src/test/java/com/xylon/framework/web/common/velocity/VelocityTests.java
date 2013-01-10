/**
 * Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.framework.web.common.velocity;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2013-1-10
 */
public class VelocityTests {
	
	public static final String UTF8 = "UTF-8";
	
	@Test
	public void testVelocity() throws Exception {
		String vmPath = VelocityTests.class.getResource("wangx.vm").getPath();
		vmPath = vmPath.substring(0, vmPath.lastIndexOf("/"));

		Properties p = new Properties();
		p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, vmPath);
		p.setProperty(Velocity.ENCODING_DEFAULT, UTF8);
		p.setProperty(Velocity.INPUT_ENCODING, UTF8);
		p.setProperty(Velocity.OUTPUT_ENCODING, UTF8);
		
		VelocityEngine engine = new VelocityEngine(p);
		engine.setProperty(Velocity.INPUT_ENCODING, UTF8);
		engine.setProperty(Velocity.OUTPUT_ENCODING, UTF8);
		
		String tempName = "wangx.vm";
		Template template = engine.getTemplate(tempName, UTF8);

		VelocityContext context = new VelocityContext();
		context.put("name", "sunyh");
		context.put("age", 80);
		context.put("job", "yyyyyy");

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writer.flush();
		System.out.println(writer.toString());
	}
	
}
