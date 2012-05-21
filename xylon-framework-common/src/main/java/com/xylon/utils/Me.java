package com.xylon.utils;

public class Me {
	private int id;
	private String name;
	
	public Me(int id, String name){
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
