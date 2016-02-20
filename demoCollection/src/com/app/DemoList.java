package com.app;

import java.util.ArrayList;
import java.util.List;

public class DemoList {
	/**
	 * list is java collection framework interface that is maintain duplicate
	 * value if i pass any types of data type contain only same types values.
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		List<Comparable> al=new ArrayList<Comparable>();{
			al.add("india");
			al.add("japan");
			al.add(10);
			al.add(10.0);
			al.add("india");
			System.out.println(al);
		}
		
	}

}
