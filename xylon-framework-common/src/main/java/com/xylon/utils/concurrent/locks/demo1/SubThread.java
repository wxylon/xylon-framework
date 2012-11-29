/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.concurrent.locks.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */
public class SubThread implements Runnable {
	private Task task;

	public SubThread(Task task) {
		this.task = task;
	}

	public void run() {
		try {
			this.task.sub();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
