/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.zookeeper.cnblogs;

import org.junit.Test;

/**
 * http://www.cnblogs.com/haippy/archive/2012/07/20/2600077.html
 * @author wxylon@gmail.com
 * @date 2013-2-1
 */
public class ExecutorTest {
	
	public static final String SERVER_IP = "10.12.207.164";
	public static final String SERVER_PORT = "2181";
	public static final int SERVER_TIMEOUT = 20000;

	@Test
	public void testExecutor(){
		String hostPort = SERVER_IP + ":" + SERVER_PORT;
		String znode = "/china";
		String filename = "wangxiong";
		String exec[] = new String[1];
		exec[0] = "ifconfig";
		try {
			new Executor(hostPort, znode, filename, exec).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

