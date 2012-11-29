/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.concurrent.locks.demo1;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */
public class Task {
	private final Lock lock = new ReentrantLock();
	private final Condition addCondition = lock.newCondition();
	private final Condition subCondition = lock.newCondition();
	private static int num = 0;
	private List<String> lists = new LinkedList<String>();

	public void add() throws InterruptedException {
		this.lock.lock();
		try {
			while (lists.size() == 10) {
				this.addCondition.await();
			}
			num++;
			lists.add("add string" + num);
			System.out.println("==========================");
			System.out.println("the add string size is " + lists.size());
			System.out.println(Thread.currentThread().getName());
			System.out.println("==========================");
			this.subCondition.signal();

		} finally {
			this.lock.unlock();
		}
	}

	public void sub() throws InterruptedException {
		this.lock.lock();
		try {
			if (this.lists.size() > 0) {

				this.lists.remove(0);
				System.out.println("--------------------------------------------");
				System.out.println("the sub string is " + num);
				System.out.println(Thread.currentThread().getName());
				System.out.println("--------------------------------------------");
				num--;
				this.addCondition.signal();
			} else {

				this.subCondition.await();
			}

		} finally {
			this.lock.unlock();
		}
	}
}
