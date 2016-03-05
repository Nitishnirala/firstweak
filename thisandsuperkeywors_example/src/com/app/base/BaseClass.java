package com.app.base;

public class BaseClass {
	
	int a,b;
	    public BaseClass() {
		System.out.println("this is base class defoult constructor");
	   }
	    public BaseClass(int a ,int b){
		this();
		this.a=a;
		this.b=b;
		System.out.println("value of a+b="+(a+b));
	}

}
