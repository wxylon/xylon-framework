/**
 * Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.xylon.utils.algorithm.sifter;

import java.util.Map;
import java.util.TreeMap;

/**
 * http://lovephoenix.iteye.com/blog/599291
 * 计算 骰子的组合 概率 问题
 * @author 俞立全
 * @version 2009-10-20
 */
public class A {

	/**
	 * 初始化定义骰子的个数
	 */
	private static int[] n = { 1, 1, 1, 1, 1, 1 };

	// 排列，组合
	/*
	 * 骰子的组合就是求和的概率，如2颗骰子 出现和为2的概率是1，3的概率是2，4的概率是3，5的概率是4，6的概率是5，7的概率是6
	 * ，概率的总和也就是骰子的组合。
	 */
	// 1,1,1,1,1,1
	// 6,5,4,3,2,1
	// 21,15,10,6,3,1
	// 56,35,20,10,4,1
	// 126,70,35,15,5,1
	// 上面每一个结果都是

	/**
	 * 计算骰子组合的总数（排列）
	 * @param n 骰子面数
	 * @param num 骰子数量
	 * @return
	 */
	public static int[] exec(int[] n, int num) {
		if (num > 1) {
			num--;
			System.out.println("递归：" + num);
			for (int i = 0; i < n.length; i++) {
				System.out.print("i:" + i);
				for (int j = 0; j < n.length - i - 1; j++) {
					// 每次减少一位
					n[i] += n[j + i + 1];
					System.out.print("; j:" + j);
					System.out.print("; n[" + i + "]:" + n[i]);
				}
				System.out.println();
				print(n);
			}
			exec(n, num);
		}
		return n;
	}

	private static void print(int[] n) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int k = 0; k < n.length; k++) {
			if (k != 0) {
				sb.append(", ");
			}
			sb.append(n[k]);
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

	// ///////////////////////////////////////////////////////

	/**
	 * 初始化骰子
	 * @param num1 骰子面数 一般为6面
	 * @param num2 骰子个数
	 */
	public static int[] get(int num) {
		int[] n = new int[num];
		for (int i = 0; i < n.length; i++) {
			n[i] = 1;
		}
		return n;
	}

	/**
	 * 显示输出的行号
	 */
	private static int numbers = 1;
	/**
	 * 开始枚举数组时开始的位置，从n[0]递增，由于使用递归，只能定义为成员变量。
	 */
	private static int i = 0;
	/**
	 * n[0]位置是否满足条件 n[i]>=num 由于使用递归，只能定义为成员变量。
	 */
	private static boolean x = true;

	/**
	 * 枚举出所有数组
	 * @param n 初始的数组，如{1,1,1,1}
	 * @param num 骰子的面数，一般为6
	 * @param x
	 */
	public static void verify(int[] n, int num) {
		if (x && (include(n) || isCalculateCount)) {
			printArray(n);
			numbers++;
			// 是否统计骰子掷出的概论
			if (isCalculateCount) {
				getCount(n);
			}
		}
		if (!isEnd(n, num)) {
			if (n[i] >= num) {
				x = false;
				n[i] = 1;
				i = i + 1;
			} else {
				x = true;
				n[i]++;
				i = 0;
			}
			verify(n, num);
		}
	}

	/**
	 * 判断是否结束 ，即 6，6，6，6 形式
	 * @param n
	 * @param num
	 * @return
	 */
	public static boolean isEnd(int[] n, int num) {
		boolean rt = true;
		for (int i = 0; i < n.length; i++) {
			if (n[i] == 6) {
				rt &= true; // 只有都为true才返回true
			} else {
				rt &= false;
			}
		}
		return rt;
	}

	/**
	 * 判断数组中相邻两位左边的值是否大于等于右边的值，大于等于有效，返回true，只要有一个组小于就返回false;
	 * @param n
	 * @return
	 */
	public static boolean include(int[] n) {
		boolean rt = true;
		for (int i = 0; i < n.length - 1; i++) {
			if (n[i] >= n[i + 1]) {
				rt &= true;
			} else {
				rt &= false;
			}
		}
		return rt;
	}

	/**
	 * 打印数组中的元素到控制台
	 * @param source
	 */
	public static void printArray(int[] data) {
		System.out.print(numbers + " : ");
		for (int i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	// ///////////////////////////////// 概论判断,使用概论判断就必须使用排列，包括重复的组合。
	private static boolean isCalculateCount = true;

	private static Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

	public static void getCount(int[] n) {
		Integer count = 0;
		Integer number = 0;
		for (int i = 0; i < n.length; i++) {
			count += n[i];
		}
		// 判断比较
		if (map.get(count) != null) {
			number = map.get(count) + 1;
			map.remove(count);
			map.put(count, number);
		} else {
			map.put(count, 1);
		}
	}

	/**
	 * 执行计算
	 * @param args
	 */
	public static void main(String[] args) {
		// 定义骰子的数量
		int count = 3;
		// 计算骰子组合的总数（无序）
		int[] x = A.exec(n, count);
		// int sum = 0;
		// for(int i=0;i<x.length;i++){
		// System.out.println(x[i]);
		// sum+=x[i];
		// }
		// System.out.println("最后的总和："+sum);
		//
		// //遍历骰子（排列或组合由 isCalculateCount 属性控制）
		// A.isCalculateCount = false;
		// A.verify(A.get(count),6);
		//
		// //概论统计
		// for(java.util.Map.Entry<Integer,Integer> entry : map.entrySet()){
		// Integer key = entry.getKey();
		// Integer value = entry.getValue();
		// System.out.println("骰子掷出的和: " + key + "  出现次数:" + value);
		// }
	}
}
