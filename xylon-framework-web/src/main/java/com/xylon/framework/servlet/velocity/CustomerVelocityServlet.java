/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.framework.servlet.velocity;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;

/**
 * @author wxylon@gmail.com
 * @date 2013-1-13
 */
@SuppressWarnings("serial")
public class CustomerVelocityServlet extends VelocityViewServlet{

	
	//设置返回视图为text/html编码为gbk
	@Override
	protected void setContentType(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=gbk");
	}
	
	//处理请求
	@Override
	protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
		ctx.put("username", "张三");
		ctx.put("password", "123456789");
		ctx.put("age", "20");
		ctx.put("address", "陕西西安"); 
		ctx.put("blog", "http://blogjava.net/sxyx2008");
		
		List<Employee> list=new ArrayList<Employee>();
		list.add(new Employee(1,"张三","陕西西安",18,new Department(1,"软件研发部1")));
		list.add(new Employee(2,"张三","陕西西安",19,new Department(2,"软件研发部2")));
		list.add(new Employee(3,"张三","陕西西安",20,new Department(3,"软件研发部3")));
		list.add(new Employee(4,"张三","陕西西安",21,new Department(4,"软件研发部4")));
		list.add(new Employee(5,"张三","陕西西安",22,new Department(5,"软件研发部5")));
		list.add(new Employee(6,"张三","陕西西安",23,new Department(6,"软件研发部6")));
		list.add(new Employee(7,"张三","陕西西安",24,new Department(7,"软件研发部7")));
		list.add(new Employee(8,"张三","陕西西安",25,new Department(8,"软件研发部8")));
		list.add(new Employee(9,"张三","陕西西安",26,new Department(9,"软件研发部9")));
		list.add(new Employee(10,"张三","陕西西安",27,new Department(10,"软件研发部10")));
		
		ctx.put("list", list);
		//调用父类的方法getTemplate()
		return getTemplate("demo.vm");
	}
	
}

