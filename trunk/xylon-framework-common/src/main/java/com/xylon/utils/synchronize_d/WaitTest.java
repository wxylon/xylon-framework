/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.synchronize_d;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */
public class WaitTest {
	
	public static void main(String[] args) {
		Object lock = new Object();
		Thread myThread1 = new MyThread(lock); 
		Thread myThread2 = new MyThread(lock); 
		Thread myThread3 = new MyThread(lock); 
		myThread1.start();
		myThread2.start();
		myThread3.start(); 
	}
}

class MyThread extends Thread{
	Object lock;
	
	public MyThread(Object lock){
		this.lock = lock;
	}
	
	public void run(){
		int count = 0;
		synchronized (lock) {
			while((count++) < 10){
				System.out.println(Thread.currentThread().getName());
			}
			notifyAll();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

