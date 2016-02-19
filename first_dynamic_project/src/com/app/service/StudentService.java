package com.app.service;

import java.util.List;

import com.app.DAO.StudentDAO;
import com.app.model.Student;

public interface StudentService {
	public void addStudent(Student student);  
	  
	 public List<Student> listStudent();  
	   
	 public Student getStudent(int std_id);  
	   
	 public void deleteStudent(Student student); 

}
