package com.map;

public class ArrayListDemo {
	private int empid;
	private String empName;
	private String addess;

	public ArrayListDemo(int empid, String empName, String addess) {
		super();
		this.empid = empid;
		this.empName = empName;
		this.addess = addess;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddess() {
		return addess;
	}

	public void setAddess(String addess) {
		this.addess = addess;
	}

	@Override
	public String toString() {
		return "ArrayListDemo [empid=" + empid + ", empName=" + empName
				+ ", addess=" + addess + "]";
	}

}
