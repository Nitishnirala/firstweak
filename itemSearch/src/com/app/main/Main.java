package com.app.main;

import java.util.Scanner;

import com.app.search.ItemSearch;
import com.app.user.OrderList;

public class Main {
	public static String username;
	public static String itemName;
	public static int itemNo;
	public static double setprice;
	static String mobile ;
	public static String address;
	private static Scanner sc;
	public static void main(String[] args) {
		
		sc = new Scanner(System.in);
		System.out.println("enter user Name ");
        username=sc.nextLine();
        System.out.println("enter item name");
        itemName=sc.next();
        System.out.println("enter how much you want item ");
		 itemNo=sc.nextInt();
		 System.out.println("enter item price");
		setprice=sc.nextDouble();
		System.out.println("enter mobile number of user ");
        mobile=sc.next();
        System.out.println("enter full address where you want to deliver the item");
	     address=sc.next();
		OrderList listObj=new OrderList();
		listObj.setUserName(username);	
		listObj.setItemName(itemName);
		listObj.setItemNumber(itemNo);
		listObj.setItemPrice(setprice);
		listObj.setMobileNumber(mobile);
		listObj.setAddress(address);
		System.out.println("*********search the record of the current order******");
		if(listObj!=null)
		{
		String mob;
		System.out.println("search the record enter your mobile number");
		mob=sc.next();
        if(mob.equals(mobile))
		ItemSearch.searchItem(mobile,username,address);
		}
	}

}
