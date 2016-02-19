package com.map;

import java.util.*;


public class MainMap {
	

	public static void main(String[] args) {

		HashMap<DemoMapUse, String> map = new HashMap<DemoMapUse, String>();
		{
			map.put(new DemoMapUse("Nitish", 101, "Chennai"), "Nitish");
			map.put(new DemoMapUse("Vikash", 102, "Bihar"), "Vikash");
			map.put(new DemoMapUse("Shilpa", 103, "Nagpur"), "Shilpa");
			map.put(new DemoMapUse("Shruti", 104, "Nagpur"), "Shruti");
			printMap(map);

		}

	}
	 public static void printMap(HashMap<DemoMapUse, String> map){
         
	        Set<DemoMapUse> keys = map.keySet();
	        for(DemoMapUse p:keys){
	            System.out.println(p+"==>"+map.get(p));
	        }
	 }
}
