package com.app.implDAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.DAO.StudentDAO;
import com.app.model.Student;
@Repository("studentDAO")
public class StudentImplDAO implements StudentDAO {

	 @Autowired  
	 private SessionFactory sessionFactory;
	@Override
	public void addStudent(Student student) {
		sessionFactory.getCurrentSession().saveOrUpdate(student); 
	}

	@Override
	 @SuppressWarnings("unchecked") 
	public List<Student> listStudent() {
	
		  return (List<Student>) sessionFactory.getCurrentSession().createCriteria(Student.class).list();
	}

	@Override
	public Student getStudent(int std_id) {
		
		return (Student) sessionFactory.getCurrentSession().get(Student.class, std_id);  
	}

	@Override
	public void deleteStudent(Student student) {
	
		sessionFactory.getCurrentSession().createQuery("DELETE FROM StdTab WHERE stdid = "+student. getStdid()).executeUpdate();  
 
	}  
}
