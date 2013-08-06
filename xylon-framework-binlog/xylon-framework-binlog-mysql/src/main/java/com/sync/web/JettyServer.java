package com.sync.web;

import org.apache.log4j.Logger;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHandler;

/**
 *  嵌入式 jetty 容器
 *  
 * @author ben
 *
 */

public class JettyServer {
	
	private static Logger logger = Logger.getLogger(JettyServer.class);
	

	Server server = null;
	
	public void start() {
		try {	        
			
	        Server server = new Server();	        
	        Connector connector = new SelectChannelConnector();
	        connector.setPort(Integer.getInteger("jetty.port", 9090).intValue());
	        server.setConnectors(new Connector[]{connector});
	        
	        Context root = new Context(server,"/", Context.SESSIONS);

	        ServletHandler servlet_handler = new ServletHandler();
	        servlet_handler.addServletWithMapping(SyncServlet.class, "/sync");

	        ResourceHandler resource_handler = new ResourceHandler();
	        resource_handler.setWelcomeFiles(new String[]{"index.html"});
	        resource_handler.setResourceBase(System.getProperty("sync.home") + "/webapps");
	        
	        logger.debug("resource: " + resource_handler.getBaseResource());
	        
	        HandlerList handlers = new HandlerList();
	        handlers.addHandler(resource_handler);
	        handlers.addHandler(servlet_handler);

	        root.setHandler(handlers);
	        
	        server.start();
	        server.join();	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
