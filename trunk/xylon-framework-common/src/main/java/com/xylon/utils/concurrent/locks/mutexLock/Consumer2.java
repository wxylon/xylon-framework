/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.concurrent.locks.mutexLock;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-29
 */
import java.util.concurrent.locks.Lock;
/**
 * @说明 父亲类，只挣钱
 */
public class Consumer2 implements Runnable {
	BankCard bc = null;
	Lock lock = null;
	Consumer2(BankCard bc, Lock lock) {
		this.bc = bc;
		this.lock = lock;
	}
	public void run() {
		try {
			while(true){
				lock.lock();
				System.out.print("父亲要存钱，现在余额：" + bc.getBalance() + "\t");
				bc.setBalance(bc.getBalance() + 500);
				System.out.println("父亲存入500元，现在余额：" + bc.getBalance());
				lock.unlock();
				Thread.sleep(3 * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}


