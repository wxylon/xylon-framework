/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.utils.vm.chapter2;

/**
 * VM Arguments : -Xss2M
 * @author wxylon@gmail.com
 * @date 2012-12-8
 */
public class JavaVMStackOOM {
	private void dontStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable() {
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) throws Throwable{
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}

