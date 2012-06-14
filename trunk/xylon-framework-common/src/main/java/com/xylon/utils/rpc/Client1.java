package com.xylon.utils.rpc;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import org.apache.xmlrpc.XmlRpcException;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-14
 */
public class Client1 {
	 public static void main(String[] args) {
	        try {
	            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	            config.setServerURL(new URL("http://localhost:8005/xmlrpc"));
	            XmlRpcClient client = new XmlRpcClient();
	            client.setConfig(config);
	            Vector<String> params = new Vector<String>();
	            params.addElement("牛粪");
	            String result = (String) client.execute("com.xylon.utils.rpc.HelloHandler.sayHello",params);
	            String result2 = (String) client.execute("com.xylon.utils.rpc.HelloHandler.sayHi",params);
	            System.out.println(result);
	            System.out.println(result2);
	        } catch (MalformedURLException e) {
	            System.out.println(e.toString());
	        } catch (XmlRpcException e) {
	            System.out.println(e.toString());
	        }
	    }
}

