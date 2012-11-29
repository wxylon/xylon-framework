/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.concurrent.locks.demo1;

/**
 * http://leowzy.iteye.com/blog/810969
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */
public class LockMainTest {
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		Task task = new Task();
		int i = 0;
//		while (i < 10) {
//			Thread t1 = new Thread(new AddThread(task));
//			Thread t3 = new Thread(new AddThread(task));
//			Thread t7 = new Thread(new AddThread(task));
//			Thread t8 = new Thread(new AddThread(task));
//			Thread t2 = new Thread(new SubThread(task));
//			Thread t4 = new Thread(new SubThread(task));
//			Thread t5 = new Thread(new SubThread(task));
//			Thread t6 = new Thread(new SubThread(task));
//			t2.start();
//
//			t8.start();
//			t1.start();
//			t1.sleep(2000);
//			t3.start();
//			i++;
//			Thread.sleep(1000);
//			t6.start();
//			t7.start();
//			t5.start();
//			t4.start();
//
//		}
		 Thread t1=new Thread(new AddThread(task));
		 Thread t2=new Thread(new AddThread(task));
		 Thread t3=new Thread(new AddThread(task));
		 Thread t4=new Thread(new AddThread(task));
		 Thread t5 = new Thread(new SubThread(task));
		 Thread t6 = new Thread(new SubThread(task));
		 t1.start();
		 t2.start();
		 t5.start();
		 t3.start();
		 t4.start();
		
		 t6.start();
	}
}
