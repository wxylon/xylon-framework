/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.concurrent.locks.mutexLock;

/**
 * http://www.iteye.com/topic/1121839
 * @author wxylon@gmail.com
 * @date 2012-11-29
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class MainThread {
	public static void main(String[] args) {
		BankCard bc = new BankCard();
		Lock lock = new ReentrantLock();
		ExecutorService pool = Executors.newCachedThreadPool();
		Consumer cm1 = new Consumer(bc, lock);
		Consumer2 cm2 = new Consumer2(bc, lock);
		pool.execute(cm1);
		pool.execute(cm2);
	}
}

