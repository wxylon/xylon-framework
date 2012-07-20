package com.xylon.utils.rpc.hadoop.one.support;

import com.xylon.utils.rpc.hadoop.one.protocal.Invocation;


public interface Server {
	public void stop();
	public void start();
	public void register(Class interfaceDefiner,Class impl);
	public void call(Invocation invo);
	public boolean isRunning();
	public int getPort();
}
