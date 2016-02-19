package com.map;

import java.util.*;

public class ArrayListDemoMain {
	
	public static void main(String[] args) {
		ArrayList<ArrayListDemo>array=new ArrayList<ArrayListDemo>();
		array.add(new ArrayListDemo(101, "Sweta", "Gondia"));
		array.add(new ArrayListDemo(102, "Rahul", "Bihar"));
		array.add(new ArrayListDemo(103, "Upendra", "Ranchi"));
		array.add(new ArrayListDemo(104, "Rajesh", "Bodhgaya"));
		System.out.println(array);

	}

} 
