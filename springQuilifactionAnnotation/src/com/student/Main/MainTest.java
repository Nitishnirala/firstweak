package com.student.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.student.model.Profile;

public class MainTest {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("student.xml");
		Profile profile = (Profile) context.getBean("profile");
		profile.printId();
		profile.printName();
		profile.printAddress();
	}

}
