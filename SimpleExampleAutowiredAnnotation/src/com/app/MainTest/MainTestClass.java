package com.app.MainTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.employee.Employee;

public class MainTestClass {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"employee.xml");
		Employee emp=(Employee)context.getBean("empObj1");
		System.out.println(emp);
		
	}

}
