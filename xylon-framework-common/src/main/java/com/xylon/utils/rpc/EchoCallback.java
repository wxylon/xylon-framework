package com.xylon.utils.rpc;

import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;

/**
 * @author wxylon@gmail.com
 * @date 2012-6-14
 */
class EchoCallback implements AsyncCallback {
    public void handleResult(XmlRpcRequest pRequest, Object pResult) {
        System.out.println("服务器说: " + (String) pResult);
    }

    public void handleError(XmlRpcRequest pRequest, Throwable pError) {
        System.out.println("发生错误: " + pError.getMessage());
    }
}

