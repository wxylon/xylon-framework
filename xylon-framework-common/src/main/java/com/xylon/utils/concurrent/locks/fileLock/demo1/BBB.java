package com.xylon.utils.concurrent.locks.fileLock.demo1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class BBB {
	public boolean obtain() throws IOException {
		String lockFileName = "C:/001/lockfile001.lock";
		File tf = new File(lockFileName);
		tf.createNewFile();
		FileChannel channel = new RandomAccessFile(tf, "rw").getChannel();
		try {
			System.out.println("get lock 000 >>>>>>>>>>>>>>>");
			FileLock lock = channel.lock();
			System.out.println("get lock >>>>>>>>>>>>>>>");
			return true;
		} catch (OverlappingFileLockException e) {
			System.out.println(e);
			return false;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new BBB().obtain();
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

	}

}
