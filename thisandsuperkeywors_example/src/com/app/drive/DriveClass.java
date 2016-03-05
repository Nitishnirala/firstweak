package com.app.drive;

import com.app.base.BaseClass;

public class DriveClass extends BaseClass {

	public DriveClass() {
    super(23,67);
		System.out.println("this is drive class default constructor");
	}

	public DriveClass(int a, int b) {
		this();

	System.out.println("this is value of drive class constructor=" + (a + b));
	}

	public static void main(String[] args) {
     
		new DriveClass(10,20);
	}
}
