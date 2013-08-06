package com.sync.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sync.MysqlSync;

/**
 * 显示  sync 当前状态
 * 
 * @author ben
 *
 */

public class SyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append( "---------------------------------------------------- \n" );
		sBuffer.append( " MysqlSync info  \n" );
		sBuffer.append( "---------------------------------------------------- \n" );
		sBuffer.append(  "io thread status : ").append( MysqlSync._IO_THREAD_STATUS ).append(" \n");
		sBuffer.append(  "sql thread status  : ").append(MysqlSync._SQL_THREAD_STATUS  ).append( " \n");
		sBuffer.append(  "binlog postion : ").append(MysqlSync._POS_INFO ).append( " \n");
		sBuffer.append( "----------------------------------------------------"  + " \n");
		
		response.getWriter().write(sBuffer.toString());
		response.getWriter().flush();
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

}
