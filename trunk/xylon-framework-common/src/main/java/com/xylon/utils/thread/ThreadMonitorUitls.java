/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.thread;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-31
 */
public class ThreadMonitorUitls {
	
	public static void beepForAnHour(final List<? extends Thread> ts) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		final Runnable beeper = new Runnable() {
			public void run() {
				for (Thread t : ts) {
//					if(t.getState() == Thread.State.BLOCKED || t.getState() == Thread.State.RUNNABLE ){
						System.out.println(t.getName() + "--->Thread.state:" + t.getState());
//					}
				}
			}
		};
		// 间隔10秒种
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
	}
	
}

