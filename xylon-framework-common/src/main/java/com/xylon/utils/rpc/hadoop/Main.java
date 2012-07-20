package com.xylon.utils.rpc.hadoop;

import com.xylon.utils.rpc.hadoop.op.Echo;
import com.xylon.utils.rpc.hadoop.op.RemoteEcho;
import com.xylon.utils.rpc.hadoop.support.Server;


public class Main {
	public static void main(String[] args) {
		Server server = new RPC.RPCServer();
		server.register(Echo.class, RemoteEcho.class);
		server.start();
	}

}
