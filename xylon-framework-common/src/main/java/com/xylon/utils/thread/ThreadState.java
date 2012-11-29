/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.thread;

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
public class ThreadState {
	public static void main(String[] args)throws Exception{
		final Object lock_1 = new Object(), lock_2 = new Object();
		Thread thread1 = new MyThread1(lock_1, lock_2);
		Thread thread2 = new MyThread2(lock_1, lock_2);
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

class MyThread1 extends Thread{
	final Object lock_1, lock_2;
	
	public MyThread1(Object lock_1, Object lock_2){
		super.setName("t1");
		this.lock_1 = lock_1;
		this.lock_2 = lock_2;
	}
	
	public void run() {
		synchronized (lock_1) {
			try {
				Thread.sleep(4000);
			
//			synchronized (lock_2) {
				System.out.println("thread t1 done.");
			} catch (InterruptedException e) {}
//			}
		}
	}
}

class MyThread2 extends Thread{
	
	final Object lock_1, lock_2;
	
	public MyThread2(Object lock_1, Object lock_2){
		super.setName("t2");
		this.lock_1 = lock_1;
		this.lock_2 = lock_2;
	}
	
	public void run() {
		synchronized (lock_2) {
			try {
				Thread.sleep(3000);
			
				synchronized (lock_1) {
					System.out.println("thread t2 done.");
				}
			} catch (InterruptedException e) {}
		}
	}
}




