/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.thread.wait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-29
 */
public class ThreadWait {
	public static int count = 0;
	public static void main(String[] args)throws Exception{
		final Object lock = new Object();
		Thread thread1 = new MyThread3(count, "t1", count);
		Thread thread2 = new MyThread3(count, "t2", count);
		beepForAnHour(Arrays.asList(thread1, thread2));
		Thread.currentThread().sleep(2000);
		thread1.start();
		thread2.start();
	}
	
	
	public static void beepForAnHour(final List<Thread> ts) {
	   ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
       final Runnable beeper = new Runnable() {
           public void run() { 
        	   for(Thread t : ts){
        		   System.out.println(t.getName() + "--->Thread.state:"+t.getState()); 
        	   }
           }
       };
       //间隔10秒种
       final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
   }
}

class MyThread3 extends Thread{
	final Object lock;
	String name;
	int count;
	public MyThread3(Object lock, String threadName, int count){
		super.setName(threadName);
		this.name = threadName;
		this.lock = lock;
		this.count = count;
	}
	
	public void run() {
		count++;
		synchronized (lock) {
			try {
				if(count==2){
					lock.wait(3000);
//					lock.notifyAll();
				}
				System.out.println("thread "+name+" done.");
			} catch (InterruptedException e) {}
		}
	}
}