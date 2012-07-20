package com.xylon.utils.rpc.hadoop.one;

import com.xylon.utils.rpc.hadoop.one.op.Echo;
import com.xylon.utils.rpc.hadoop.one.op.RemoteEcho;
import com.xylon.utils.rpc.hadoop.one.support.Server;


public class Main {
	public static void main(String[] args) {
		Server server = new RPC.RPCServer();
		server.register(Echo.class, RemoteEcho.class);
		server.start();
	}

}
