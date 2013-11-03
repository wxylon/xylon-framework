package com.xylon.framework.servlet.velocity;

public class Department {

	private int id;
	private String deptname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public Department() {
	}

	public Department(int id, String deptname) {
		super();
		this.id = id;
		this.deptname = deptname;
	}

}
