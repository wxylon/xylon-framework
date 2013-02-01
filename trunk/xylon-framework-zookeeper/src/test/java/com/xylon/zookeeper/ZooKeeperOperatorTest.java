/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.zookeeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2013-2-1
 */
public class ZooKeeperOperatorTest {
	private static Log log = LogFactory.getLog(ZooKeeperOperatorTest.class.getName());

	@Test
	public void test() throws Exception{
		ZooKeeperOperator zkoperator = new ZooKeeperOperator();
		zkoperator.connect("10.12.207.164");
		byte[] data = new byte[] { 'a', 'b', 'c', 'd' };
		// zkoperator.create("/root",null);
		// System.out.println(Arrays.toString(zkoperator.getData("/root")));
		// zkoperator.create("/root/child1",data);
		// System.out.println(Arrays.toString(zkoperator.getData("/root/child1")));
		// zkoperator.create("/root/child2",data);
		// System.out.println(Arrays.toString(zkoperator.getData("/root/child2")));
		String zktest = "ZooKeeper-Java API-";
		String path = "/wangx4";
		zkoperator.create(path, zktest.getBytes());
		log.debug("获取设置的信息：" + new String(zkoperator.getData(path)));
		System.out.println("节点孩子信息:");
		zkoperator.getChild(path);
		zkoperator.close();
	}
}

