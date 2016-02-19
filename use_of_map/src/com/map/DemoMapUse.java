package com.map;

public class DemoMapUse {
	private String name;
	private int empid;
	private String address;

	public DemoMapUse() {
		super();
	}

	public DemoMapUse(String name, int empid, String address) {
		super();
		this.name = name;
		this.empid = empid;
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "DemoMapUse [name=" + name + ", empid=" + empid + ", address="
				+ address + "]";
	}

}
