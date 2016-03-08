package com.student.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.student.model.Profile;

public class MainTest {

	private static ApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("student.xml");
		Profile profile = (Profile) context.getBean("profile");
		profile.printId();
		profile.printName();
		profile.printAddress();
	}

}
