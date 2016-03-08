package com.app.employee;

import org.springframework.beans.factory.annotation.Autowired;

public class Employee {

	private int empId;
	private String empName;
	private String empAddress;
	private double salary;
	private String company;
	public Employee() {
		super();
	}
	public int getEmpId() {
		return empId;
	}
	@Autowired
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	@Autowired
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	@Autowired
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public double getSalary() {
		return salary;
	}
	@Autowired
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getCompany() {
		return company;
	}
	@Autowired
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName
				+ ", empAddress=" + empAddress + ", salary=" + salary
				+ ", company=" + company + "]";
	}
	
}
