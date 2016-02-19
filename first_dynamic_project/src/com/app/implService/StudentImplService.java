package com.app.implService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.StudentDAO;
import com.app.model.Student;
import com.app.service.StudentService;

@Service("studentService")
// @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class StudentImplService implements StudentService {

	@Autowired
	private StudentDAO studentDAO;

	@Override
	public void addStudent(Student student) {
		studentDAO.addStudent(student);
	}

	@Override
	public List<Student> listStudent() {

		return studentDAO.listStudent();
	}

	@Override
	public Student getStudent(int std_id) {

		 return studentDAO.getStudent(std_id); 
	}

	@Override
	public void deleteStudent(Student student) {

		studentDAO.deleteStudent(student);
	}

}
