/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.concurrent.locks.demo1;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */
public class AddThread implements Runnable {
	private Task task = null;

	public AddThread(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		try {
			this.task.add();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
