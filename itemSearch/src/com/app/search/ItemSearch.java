package com.app.search;


import java.util.HashMap;
import java.util.List;
import java.util.Set;
import com.app.user.OrderList;

public class ItemSearch {
	static List<Object>  li;
	public static String searchItem(String mobile,String name,String Address) {
		OrderList or = new OrderList();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(mobile, or.getMobileNumber());
		Set<String> keys = map.keySet();
		if (keys != null) {
			for (String key : keys) {
				map.put(name, or.getUserName())	;
				map.put(Address, or.getAddress());
			}
			System.out.println(map);
		}
		return mobile;
	}
}
