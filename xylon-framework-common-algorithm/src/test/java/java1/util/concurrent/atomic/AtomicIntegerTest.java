/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package java1.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * @author wxylon@gmail.com
 * @date 2012-8-13
 */
public class AtomicIntegerTest {
	public static AtomicInteger atomicInteger = new AtomicInteger(10);
	
	public static int getID(){
		synchronized (atomicInteger) {
			return atomicInteger.getAndIncrement();
		}
	}
	
	@Test
	public void test(){
		for(int i = 0; i < 100; i++){
			System.out.println(getID());
		}
	}
}

