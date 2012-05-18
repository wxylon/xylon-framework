package com.xylon.utils.algorithm;

/**
 * 插入排序算法
 * @author wangxiong
 */
public class InsertionSort {
	public static void sort(Me[] mes){
		int n = mes.length;
		int j;
		Me temp;
		for(int i = 1; i < n; i++){
			temp = mes[i];
			j = i - 1;
			while(j >= 0 && mes[j].hashCode() > temp.hashCode()){
				mes[j+1] = mes[j];
				j--;
			}
			mes[j+1] = temp;
		}
	}
}
class Me{
	private int id;
	private String name;
	
	Me(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if(null == obj) return false;
		if(obj.getClass() == this.getClass()){
			Me temp = (Me)obj;
			return temp.getId() == this.getId() && temp.getName().equals(this.getName());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id:").append(this.id).append("; name:").append(this.name).append(";]");
		return builder.toString();
	}
}