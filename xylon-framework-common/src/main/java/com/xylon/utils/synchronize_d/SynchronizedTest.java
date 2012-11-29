/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.synchronize_d;

import java.util.LinkedList;

/**
 * http://www.iteye.com/topic/81152
 * @author wxylon@gmail.com
 * @date 2012-11-28
 */

public class SynchronizedTest{
	public static void main(String[] args) throws InterruptedException {
		IteyeStack iteyeStack = new IteyeStack();
		for(;;){
			iteyeStack.push(new Object());
			iteyeStack.pop();
		}
	}
}


class IteyeStack {
	LinkedList list = new LinkedList();

	public synchronized void push(Object x) {
		synchronized (list) {
			list.addLast(x);
			//notify();
			System.out.println("++++++++++++++");
		}
	}

	public synchronized Object pop() throws InterruptedException {
		synchronized (list) {
			if (list.size() <= 0)
				wait();
			System.out.println("--------------");
			return list.removeLast();
		}
	}

}
