/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.thread.prodAndConsumers;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-31
 */
public class Producer extends Thread {
	// 生产产品的数量
	private int neednum;
	// 仓库
	private GoDown godown;
	
	public Producer(int neednum, GoDown godown) {
		this.neednum = neednum;
		this.godown = godown;
	}

	public void run() {
		// 生产指定数量的产品
		while(true){
			godown.produce(neednum);
		}
	}
}
