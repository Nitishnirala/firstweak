package com.student.model;

public class Student {

	private int stud_Id;
	private String stud_name;
	private String stud_address;
	public Student() {
		super();
	}
	public int getStud_Id() {
		return stud_Id;
	}
	public void setStud_Id(int stud_Id) {
		this.stud_Id = stud_Id;
	}
	public String getStud_name() {
		return stud_name;
	}
	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}
	public String getStud_address() {
		return stud_address;
	}
	public void setStud_address(String stud_address) {
		this.stud_address = stud_address;
	}
	@Override
	public String toString() {
		return "Student [stud_Id=" + stud_Id + ", stud_name=" + stud_name
				+ ", stud_address=" + stud_address + "]";
	}
	
}
