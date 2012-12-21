/**
* Copyright(c) 2002-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.xylon.algorithm.connectivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxylon@gmail.com
 * @date 2012-11-27
 */
public class ContectivityAreaTest {
	private String[][] graph= {{"A", "A", "C", "D"}, {"A", "A", "A", "D"}, {"A", "A", "C", "D"}, {"A", "B", "C", "D"}};
	private Map<String, List<Point>> values = new HashMap<String, List<Point>>();
	
	public void init(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				System.out.print(graph[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public void cipher(){
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph[i].length; j++){
				String temp = graph[i][j];
				add(i, j, temp);
			}
		}
	}
	
	public void add(int i, int j, String key){
		List<Point> points = values.get(key);
		if(null == points){
			points = new ArrayList<Point>();
			points.add(new Point(i, j, key));
			values.put(key, points);
		}else{
			for(Point point : points){
				if(point.getX() == i && point.getY() + 1 == j){
					points.add(new Point(i, j, key));
					break;
				}else if(point.getY() == j && point.getX() + 1 == i){
					points.add(new Point(i, j, key));
					break;
				}
			}
		}
	}
	
	public void finish(){
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
		for(List<Point> points : values.values()){
			for(Point point : points){
				System.out.print(point.key + "   ");
			}
			System.out.println();
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public static void main(String[] args) {
		ContectivityAreaTest r = new ContectivityAreaTest();
		r.init();
		r.cipher();
		r.finish();
	}
	
	class Point{
		private int x;
		private int y;
		private String key;
		
		public Point(int x, int y, String key){
			this.x = x;
			this.y = y;
			this.key = key;
		}
		
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	}
	
}

