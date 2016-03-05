package com.app.login;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class LoginApplicationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name=req.getParameter("userName");
		String email=req.getParameter("email");
		String pass=req.getParameter("pwd");
		String dob=req.getParameter("bday");
		String address=req.getParameter("address");
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		Entity ent=new Entity("Register");
		ent.setProperty("userName", name);
		ent.setProperty("emai", email);
		ent.setProperty("password",pass);
		ent.setProperty("DoB", dob);
		ent.setProperty("address", address);
		ds.put(ent);
	}
}
