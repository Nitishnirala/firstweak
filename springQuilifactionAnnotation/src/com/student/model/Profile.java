package com.student.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Profile {
	@Autowired
	   @Qualifier(value="stdObj")
	   private Student student;
     
	   public Profile(){
	      System.out.println("Inside Profile constructor." );
	   }

	   public void printId() {
	      System.out.println("Age : " + student. getStud_Id());
	   }

	   public void printName() {
	      System.out.println("Name : " + student.getStud_name());
	   }
  public void printAddress(){
	  System.out.println("Address:"+student.getStud_address());
  }
}
