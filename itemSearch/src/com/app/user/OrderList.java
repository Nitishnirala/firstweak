package com.app.user;

public class OrderList {
	private String userName;
	private String itemName;
	private double itemPrice;
	private int itemNumber;
	private String address;
	private String mobileNumber;
	public OrderList() {
		super();
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUserName() {
		return userName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public String getAddress() {
		return address;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}

	@Override
	public String toString() {
		return "OrderList [userName=" + userName + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + ", itemNumber=" + itemNumber
				+ ", address=" + address + ", mobileNumber=" + mobileNumber
				+ "]";
	}

	

}
