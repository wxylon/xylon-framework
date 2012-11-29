/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.thread.notify;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-29
 */
public class ThreadB extends Thread{
	int total; 
	public void run() { 
		synchronized (this) { 
			for (int i = 0; i < 101; i++) { 
				total += i; 
			} 
			//（完成计算了）唤醒在此对象监视器上等待的单个线程，在本例中线程A被唤醒 
			notify(); 
		} 
	}
}

