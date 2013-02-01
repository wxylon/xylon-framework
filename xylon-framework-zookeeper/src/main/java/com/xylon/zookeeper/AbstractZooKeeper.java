/**
 * Copyright(c) 2002-2013, wxylon@gmail.com  All Rights Reserved
 */
package com.xylon.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author wxylon@gmail.com
 * @date 2013-2-1
 */
public class AbstractZooKeeper implements Watcher {

	private static Log log = LogFactory.getLog(AbstractZooKeeper.class.getName());

	// 超时时间
	private static final int SESSION_TIME = 20000;
	protected ZooKeeper zooKeeper;
	protected CountDownLatch countDownLatch = new CountDownLatch(1);
	
	public void connect(String hosts) throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
		countDownLatch.await();
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			countDownLatch.countDown();
		}
	}

	public void close() throws InterruptedException {
		zooKeeper.close();
	}
}
