package com.xylon.utils.concurrent.locks.fileLock.demo1;

public class AAA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//new AAA().test();
		System.out.println("=========================================");
		Thread th1 = new Thread(new MakeLock("==================== thread1 ===================="));
		Thread th2 = new Thread(new MakeLock("#################### thread2 ####################"));
		Thread th3 = new Thread(new MakeLock("@@@@@@@@@@@@@@@@@@@@ thread3 @@@@@@@@@@@@@@@@@@@@"));
		Thread th4 = new Thread(new MakeLock("$$$$$$$$$$$$$$$$$$$$ thread4 $$$$$$$$$$$$$$$$$$$$"));
		Thread th5 = new Thread(new MakeLock("&&&&&&&&&&&&&&&&&&&& thread5 &&&&&&&&&&&&&&&&&&&&"));
		th1.start();
		th2.start();
		th3.start();
//		th4.start();
//		th5.start();
	}

}
