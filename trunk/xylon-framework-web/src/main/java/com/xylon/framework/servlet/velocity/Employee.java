package com.xylon.framework.servlet.velocity;

public class Employee {

	private int id;
	private String ename;
	private String eaddress;
	private int age;
	private Department department;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEaddress() {
		return eaddress;
	}

	public void setEaddress(String eaddress) {
		this.eaddress = eaddress;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee() {
	}

	public Employee(int id, String ename, String eaddress,
			int age, Department department) {
		super();
		this.id = id;
		this.ename = ename;
		this.eaddress = eaddress;
		this.age = age;
		this.department = department;
	}

}
