package com.app;

import java.util.Map;
import java.util.TreeMap;

public class DemoMap {
	
	public static void main(String[] args) {
		Map<Integer, String> map=new TreeMap<Integer, String>();{
			map.put(101, "Rohan");
			map.put(101,"sohan");
			map.put(103, "Nitish");
			for(Map.Entry m:map.entrySet()){  
				   System.out.println(m.getKey()+" "+m.getValue());  
				  }  
			
		}
	}

}
