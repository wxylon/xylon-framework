package com.xylon.utils.concurrent.locks.fileLock.demo1;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Lock {

	/**
	 * 检测是否被锁定
	 * @return true被锁定 ,false空闲
	 * */
	public abstract boolean isLocked() throws FileNotFoundException;

	/**
	 * 获取锁资源
	 * @return true成功锁定目标资源 ,false锁定操作失败
	 * */
	public abstract boolean obtain() throws IOException;

	/**
	 * 释放锁
	 * */
	public abstract void unlock();

}