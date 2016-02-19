package com.app.DAO;

import java.util.List;

import com.app.model.Student;

public interface StudentDAO {
	public void addStudent(Student student);  
	  
	 public List<Student> listStudent();  
	   
	 public Student getStudent(int std_id);  
	   
	 public void deleteStudent(Student  student); 

}
