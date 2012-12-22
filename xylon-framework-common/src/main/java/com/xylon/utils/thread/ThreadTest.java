package com.xylon.utils.thread;


/**
 * 多线程测试<br/>
 * 该Thread除了包含run方法外，还包含一个disp方法<br/>
 * @author wangxiong
 */
public class ThreadTest {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		thread.start();
		thread.disp();
	}
}

class MyThread extends Thread{

	public void run() {
		System.out.println("MyThread--->run--->start" + Thread.currentThread().getName());
	}
	
	public void disp(){
		System.out.println("MyThread--->disp--->start" + Thread.currentThread().getName());
	}
}
