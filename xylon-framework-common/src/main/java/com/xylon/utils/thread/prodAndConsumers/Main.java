/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.thread.prodAndConsumers;

import java.util.Arrays;
import java.util.Random;

import com.xylon.utils.thread.ThreadMonitorUitls;

/**
 * @author wxylon@gmail.com
 * @date 2012-12-31
 */
public class Main {
	public static void main(String[] args) {
		Consumer[] consumers = new Consumer[10];
		Producer[] producers = new Producer[10];
		
		Random random = new Random();
		GoDown godown = new GoDown(100);
		
		for(int i = 0; i < consumers.length; i++){
			consumers[i] = new Consumer(random.nextInt(30), godown);
			consumers[i].start();
		}
		
		for(int i = 0; i < producers.length; i++){
			producers[i] = new Producer(random.nextInt(30), godown);
			producers[i].start();
		}
		ThreadMonitorUitls.beepForAnHour(Arrays.asList(producers));
		ThreadMonitorUitls.beepForAnHour(Arrays.asList(consumers));
	}
}
