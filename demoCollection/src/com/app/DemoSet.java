package com.app;

import java.util.HashSet;
import java.util.Set;

public class DemoSet {
	
	public static void main(String[] args) {
		Set<Integer>set=new HashSet<Integer>() ;{
			set.add(101);
			set.add(102);
			set.add(101);
			System.out.println(set);
			
		}
		
	}

}
