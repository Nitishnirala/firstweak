package com.app.model;



public class StudentBean {

	private Integer stdid;
	private String fname;
	private String mname;
	private String lname;
	private String pNumber;
	private String address;
	private String dob;
	public StudentBean() {
		super();
	}
	public Integer getStdid() {
		return stdid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getpNumber() {
		return pNumber;
	}
	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setStdid(Integer stdid) {
		this.stdid = stdid;
	}
	
}
