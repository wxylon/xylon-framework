package com.xylon.utils.algorithm;

/**
 * 堆排序
 * 
 * @author wangx
 * @date 2012-5-23
 */
public class HeapSort {
	public void sort(Integer[] array, int from, int len) {
		build_heap(array, from, len);
		for (int i = 0; i < len; i++) {
			// 把最大的跟置换到最后面
			swap(array, from, from + len - 1 - i);
			shift_down(array, from, len - 1 - i, 0);// 始终从根节点开始置换，最大的上升到根节点，小的下沉到叶子节点
		}
	}

	// 转换数组的两项
	private void swap(Integer[] array, int x, int y) {
		Integer temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	private final void build_heap(Integer[] array, int from, int len) {
		int pos = (len - 1) / 2;// 从第一个非叶子节点开始
		for (int i = pos; i >= 0; i--) {
			shift_down(array, from, len, i);
		}
	}

	// 把所有的非叶子节点同子节点比较 第pos个元素 选择与子节点中最大的元素与该节点置换
	private final void shift_down(Integer[] array, int from, int len, int pos) {
		// 暂时保存该节点的值， 因为这里可能不只进行一次比较
		Integer tmp = array[from + pos];

		int index = pos * 2 + 1;// 得到当前pos节点的左节点
		// 还存在左节点
		while (index < len){
			// 如果存在右节点
			// 则比较
			if (index + 1 < len && array[from + index].compareTo(array[from + index + 1]) < 0){
				// 如果右边节点比左边节点大
				index += 1;
			}
			// 因为下面是单向赋值，所以这里要用tmp进行比较
			if (tmp.compareTo(array[from + index]) < 0) {
				// 注意这里是单向赋值，少了一次置换，当前值已经赋给tmp
				array[from + pos] = array[from + index];
				// 进行了置换操作后，以置换的子节点为起点 （如果存在子节点） 还要继续进行该操作
				pos = index;
				index = pos * 2 + 1;

			} else {
				break;
			}
		}
		// 最终全部置换完毕后 才把临时变量赋给最后的节点
		array[from + pos] = tmp;
	}

	// 打印数组
	public void printArray(Integer[] a) {
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "  ");
			if ((i + 1) % 5 == 0) {
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Integer[] a = { 45, 26, 13, 27, 35, 10, -455, -90, 2008, 1024, 101, 555, 216, 82 };
		HeapSort aa = new HeapSort();
		aa.sort(a, 0, a.length);
		aa.printArray(a);
	}

}
