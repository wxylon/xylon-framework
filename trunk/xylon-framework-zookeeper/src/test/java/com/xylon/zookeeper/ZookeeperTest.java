/**
* Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.zookeeper;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;

/**
 * http://www.ibm.com/developerworks/cn/opensource/os-cn-zookeeper/
 * @see CreateMode.PERSISTENT				创建后只要不删就永久存在
 * @see CreateMode.EPHEMERAL				会话结束年结点自动被删除
 * @see CreateMode.SEQUENTIAL				节点名末尾会自动追加一个10位数的单调递增的序号
 * @see CreateMode.PERSISTENT_SEQUENTIAL	结合PERSISTENT和SEQUENTIAL
 * @see CreateMode.EPHEMERAL_SEQUENTIAL		结合EPHEMERAL和SEQUENTIAL
 * @author wxylon@gmail.com
 * @date 2013-2-1
 */
public class ZookeeperTest {
	public static final String SERVER_IP_PORT = "127.0.0.1:2181";
	public static final int SERVER_TIMEOUT = 20000;
	public ZooKeeper zk = null;
	public AtomicInteger seq = new AtomicInteger();
	
	@Before
	public void init() throws Exception{
		// 创建一个与服务器的连接
		zk = new ZooKeeper(SERVER_IP_PORT, 
			 SERVER_TIMEOUT, new Watcher() { 
	            // 监控所有被触发的事件
	            public void process(WatchedEvent event) { 
	                System.out.println("已经触发了" + event.toString() + "事件！"); 
	            } 
	        }); 
	}
	
	@Test
	public void testCreate() throws Exception{
		
		zk.create("/wangx" + seq.getAndIncrement(), "ceshi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		zk.create("/wangx" + seq.getAndIncrement(), "ceshi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		zk.create("/wangx" + seq.getAndIncrement(), "ceshi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
		zk.create("/wangx" + seq.getAndIncrement(), "ceshi".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		Thread.currentThread().sleep(10000);
		
//		[zk: 127.0.0.1(CONNECTED) 7] ls /
//		[wangx0, wangx30000000012, wangx20000000011, wangx1, zookeeper]
//		[zk: 127.0.0.1(CONNECTED) 8] ls /
//		[wangx0, wangx20000000011, zookeeper]
	}
	
	
//	@Test
	public void test() throws Exception{
		// 创建一个与服务器的连接
		ZooKeeper zk = new ZooKeeper(SERVER_IP_PORT, 
				 SERVER_TIMEOUT, new Watcher() { 
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		                System.out.println("已经触发了" + event.getType() + "事件！"); 
		            } 
		        }); 
		 // 创建一个目录节点
		 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); 
		 // 创建一个子目录节点
		 zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath", false, null))); 
		 // 取出子目录节点列表
		 System.out.println(zk.getChildren("/testRootPath", true)); 
		 // 修改子目录节点数据
		 zk.setData("/testRootPath/testChildPathOne","modifyChildDataOne".getBytes(), -1); 
		 System.out.println("目录节点状态：["+zk.exists("/testRootPath", true)+"]"); 
		 // 创建另外一个子目录节点
		 zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); 
		 System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null))); 
		 // 删除子目录节点
		 zk.delete("/testRootPath/testChildPathTwo", -1); 
		 zk.delete("/testRootPath/testChildPathOne", -1); 
		 // 删除父目录节点
		 zk.delete("/testRootPath", -1); 
		 // 关闭连接
		 zk.close(); 

	}
}

