package com.xylon.utils.rpc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-14
 */
public class Client3 {
	 public static void main(String[] args) throws Exception {
	        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
	        config.setServerURL(new URL("http://localhost:8005/xmlrpc"));
	        config.setEnabledForExtensions(true);
	        config.setConnectionTimeout(60 * 1000);
	        config.setReplyTimeout(60 * 1000);
	        XmlRpcClient client = new XmlRpcClient();
	        client.setConfig(config);
	        ClientFactory factory = new ClientFactory(client);
	        HelloHandler handler = (HelloHandler) factory.newInstance(HelloHandler.class);
	        String str = handler.sayHello("美女");
	        System.out.println(str);
	        List<String> params = new ArrayList<String>();
	        params.add("帅哥");
	        String result=(String)client.execute("com.xylon.utils.rpc.HelloHandler.sayHello", params);
	        System.out.println(result);
	        client.executeAsync("com.xylon.utils.rpc.HelloHandler.sayHello", params,new EchoCallback());
	        client.executeAsync("com.xylon.utils.rpc.HelloHandler.sayHi", params,new EchoCallback());
	    }
}

