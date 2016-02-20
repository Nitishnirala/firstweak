package com.app.st;

public class DemoStatic {
	
	static int a=100;
	int b;
	public DemoStatic(int b) {
		this.b=b;
	}
	public static void prize(){
	
		System.out.println("static method");
	}
	
	public void read(){
		System.out.println("insance method");
	}
	public static void main(String[] args) {
		
		DemoStatic obj=new DemoStatic(500);
		/**
		 * call static method directly and static variable 
		 * because same class can't we use 
		 * class name if i use no problem
		 */
		prize();
		System.out.println("static variable value="+a);
		/**
		 * call instance variable and method
		 * 
		 */
		obj.read();
		System.out.println("instance variable value="+obj.b);
	}

}
