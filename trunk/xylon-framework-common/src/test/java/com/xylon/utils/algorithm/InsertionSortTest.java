package com.xylon.utils.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class InsertionSortTest {
	public static final Random RANDOM = new Random();
	
	@Test
	public void test(){
		List<Me> mes = new ArrayList<Me>();
		Me me = null;
		int id;
		for(int i = 0; i < 200000; i++){
			id = RANDOM.nextInt(10000);
			me = new Me(id, "name-"+id);
			mes.add(me);
//			System.out.println("  init: " + me.toString());
		}
		System.out.println("  inited");
		Me[] m = new Me[mes.size()];
		mes.toArray(m);
		long start = System.nanoTime();
		InsertionSort.sort(m);
		System.out.println(System.nanoTime() - start);
		show(m);
	}
	
	private void show(Me[] mes){
		for(Me me : mes){
//			System.out.println("sorted: " + me.toString());
		}
	}
}
