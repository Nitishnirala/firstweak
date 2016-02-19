package com.app.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView; 

import com.app.model.Student;
import com.app.model.StudentBean;
import com.app.service.StudentService;

@Controller 
public class HomeController {
@Autowired
private StudentService  studentService;


@RequestMapping(value = "/save", method = RequestMethod.POST)  
public ModelAndView saveEmployee(@ModelAttribute("command")StudentBean studentBean,   
BindingResult result) {  
Student student = prepareModel(studentBean);  
studentService.addStudent(student);  
return new ModelAndView("redirect:/add.html");  
}

@RequestMapping(value="/students", method = RequestMethod.GET)  
public ModelAndView listStudent() {  
	 Map<String, Object> model = new HashMap<String, Object>();  
 model.put("students",  prepareListofBean(studentService.listStudent()));  
 return new ModelAndView("studentList", model);  
} 
@RequestMapping(value = "/add", method = RequestMethod.GET)  
public ModelAndView addEmployee(@ModelAttribute("command")StudentBean studentBean,  
  BindingResult result) {  
 Map<String, Object> model = new HashMap<String, Object>();  
 model.put("student",  prepareListofBean(studentService.listStudent()));  
 return new ModelAndView("addEmployee", model);  
}  
 
@RequestMapping(value = "/index", method = RequestMethod.GET)  
public ModelAndView welcome() {  
  return new ModelAndView("index");  
 }  
@RequestMapping(value = "/delete", method = RequestMethod.GET)  
public ModelAndView editEmployee(@ModelAttribute("command")StudentBean studenBean,  
   BindingResult result) {  
	studentService.deleteStudent(prepareModel(studenBean));  
  Map<String ,Object> model = new HashMap<String, Object>();  
  model.put("student", null);  
  model.put("students",  prepareListofBean(studentService.listStudent()));  
  return new ModelAndView("addStudent", model);  
 }  
   
@RequestMapping(value = "/edit", method = RequestMethod.GET)  
public ModelAndView deleteStudent(@ModelAttribute("command")StudentBean studentBean,  
   BindingResult result) {  
  Map<String, Object> model = new HashMap<String, Object>();  
  model.put("student", prepareStudentBean(studentService.getStudent(studentBean.getStdid())));  
  model.put("employees",  prepareListofBean(studentService.listStudent()));  
  return new ModelAndView("addStudent", model);  
 }  
private Object prepareStudentBean(Student student) {

	StudentBean bean = new StudentBean();  
	  bean.setStdid(student.getStdid());  
	  bean.setFname(student.getFname());  
	  bean.setLname(student.getLname());  
	  bean.setMname(student.getMname());  
	  bean.setpNumber(student.getpNumber());  
	  bean.setAddress(student.getAddress());
	  bean.setDob(student.getDob());
	  return bean;  
}

private Object prepareListofBean(List<Student> student) {
	List<StudentBean> beans = null;  
	  if(student!= null && !student.isEmpty()){  
	   beans = new ArrayList<StudentBean>();  
	   StudentBean bean = null;  
	   for(Student student1 : student){  
	    bean = new StudentBean();  
	   bean.setStdid(student1.getStdid());
	   bean.setFname(student1.getFname());
	   bean.setMname(student1.getMname());
	   bean.setLname(student1.getLname());
	   bean.setpNumber(student1.getpNumber());
	   bean.setDob(student1.getDob());
	   bean.setAddress(student1.getAddress());
	   }  
	  }  
	  return beans;  
}

private Student prepareModel(StudentBean studentBean) {

	Student student = new Student();  
	student.setFname(studentBean.getFname());  
	student.setMname(studentBean.getMname());  
	student.setLname(studentBean.getLname());  
	student.setpNumber(studentBean.getpNumber());  
	student.setStdid(studentBean.getStdid());  
	studentBean.setStdid(null);  
	  return student;  
}  

  
}
