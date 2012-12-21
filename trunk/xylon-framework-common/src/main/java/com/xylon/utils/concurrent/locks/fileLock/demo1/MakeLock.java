package com.xylon.utils.concurrent.locks.fileLock.demo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MakeLock implements Runnable{
	private String threadID = "";
	public void run() {
		try {
			while(true) {
				test2(threadID);
				Thread.sleep(200);
			}
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	/**
	 * @deprecated
	 * */
	public void test(String threadID) throws FileNotFoundException, IOException, InterruptedException{
		Lock lock = new MakeLock(threadID).getLock("\\\\192.168.0.87\\share\\lockfile001.lock","");
		if (!lock.isLocked()) {
			System.out.println(threadID+":obtain...");
			boolean b = lock.obtain();
			System.out.println(threadID+":obtained   "+b);
			if(b){//执行业务逻辑
				Thread.sleep(300);
				for(int i = 0 ; i < Integer.MAX_VALUE ; i ++){
					;
				}
				lock.unlock();
				System.out.println(threadID+":released");
			}
		}else{
			System.out.println(threadID+":wait unlock :"+lock);
		}
	}
	
	public void test2(String threadID) throws FileNotFoundException, IOException, InterruptedException{
		Lock lock = new MakeLock(threadID).getLock("c:/001/lockfile001.lock",threadID);
		if (!lock.isLocked()) {
			System.out.println(threadID+":obtain...");
			boolean b = lock.obtain();
			System.out.println(threadID+":obtained   "+b);
			if(b){//执行业务逻辑
				Thread.sleep(390);
				for(int i = 0 ; i < Integer.MAX_VALUE ; i ++){
					;
				}
				lock.unlock();
			}
		}else{
			System.out.println(threadID+":can't get a lock :"+lock);
		}
		lock = null;
			
	}
	
	public MakeLock(String threadID){
		this.threadID = threadID;
	}
	
	public Lock getLock(String name,String threadID) {
		final StringBuffer buf = new StringBuffer();
		return FileProgrameLock.get(name,threadID);
	}
	
	public static void main(String[] args){
		File lockFile = new File("c:/001/dt1.dt");
		try {
			
			Lock lock = new MakeLock("").getLock("C:/001/lockfile001","");
			if(!lock.isLocked()){
				lock.obtain();
				System.out.println("obtain");
				lock.unlock();
				System.out.println("release");
			}
			
			lockFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
