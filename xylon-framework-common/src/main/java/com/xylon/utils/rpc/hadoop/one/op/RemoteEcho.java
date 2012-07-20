package com.xylon.utils.rpc.hadoop.one.op;

public class RemoteEcho implements Echo{
	public String echo(String echo) {
		return "from remote:"+echo;
	}
}
