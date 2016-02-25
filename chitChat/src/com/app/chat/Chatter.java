package com.app.chat;

/**
 * This class represents a chatter in the chat room. For each Chatter object a
 * name, age and login time is required.
 * 
 * @author Nitish Nirala
 */
public class Chatter {
	private String name = null;
	private String sex = null;
	private String comment = null;
	private String email = null;
	private long loginTime = -1;
	private long enteredInRoomAt = -1;
	private int age = -1;

	/**
	 * Used to create a new Chatter object. It acccepts a name age and time of
	 * his login.
	 * 
	 * @param name
	 *            name of the chatter
	 * @param age
	 *            age of the chatter
	 * @param loginTime
	 *            time when user logged in. Got using Date.getTime(). Works as a
	 *            timestamp.
	 */

	public Chatter(String name, String sex, long loginTime) {
		this.name = name;
		this.sex = sex;
		this.loginTime = loginTime;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setEnteredInRoomAt(long enteredAt) {
		this.enteredInRoomAt = enteredAt;
	}

	public long getEnteredInRoomAt() {
		return enteredInRoomAt;
	}
}