package com.app.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;


public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String mobileno=null;
		String email=null;
		String userEmail=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		Key key=KeyFactory.createKey("Employee", userEmail);
		System.out.println(key);
		Query query=new Query("Employee");
		PreparedQuery pq=ds.prepare(query);
		for(Entity ul:pq.asIterable()){
		email=ul.getProperty("empEmail").toString();
		mobileno=ul.getProperty("empnumber").toString();
		}
		
		if(userEmail.equals(email)&&mobile.equals(mobileno)){
			
			resp.sendRedirect("sucess.html");
			System.out.println(email);
			System.out.println(mobileno);
		}
		else
		{
			
			resp.sendRedirect("failed.html");
		}
	}
}
