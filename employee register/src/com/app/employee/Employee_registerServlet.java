package com.app.employee;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class Employee_registerServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String name=req.getParameter("emp_name");
		String empno=req.getParameter("num");
		String adress=req.getParameter("S2");
		String  cNumber=req.getParameter("txtFName1");
		String location=req.getParameter("mydropdown");
		String design=req.getParameter("mydropdown");
		String email=req.getParameter("email_id");
		
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		Entity ent=new Entity("Employee");
		ent.setProperty("empName",name);
		ent.setProperty("empNo",empno);
		ent.setProperty("empAddress",adress);
		ent.setProperty("empnumber", cNumber);
		ent.setProperty("empJobLocation", location);
		ent.setProperty("empDegination", design);
		ent.setProperty("empEmail", email);
		ds.put(ent);
	
	}
}
