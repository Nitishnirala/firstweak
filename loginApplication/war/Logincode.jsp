<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.google.appengine.api.datastore.*;"%>



<%
String email = request.getParameter("uemail");
String	pass = request.getParameter("pwd");
	DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	Key key = KeyFactory.createKey("Register", email);
	System.out.println(key);
	Query query = new Query("Register");
	PreparedQuery pq = ds.prepare(query);
	for (Entity ul : pq.asIterable()) {
		String email1 = ul.getProperty("emai").toString();
		String pass1 = ul.getProperty("password").toString();
		request.setAttribute("userEmail", email1);
		if (email.equals(email) && pass.equals(pass)) {	
	
			 response.sendRedirect("secuss.jsp");
			}
			else{
				
				response.sendRedirect("error.jsp");
			}
	
	}
	
%>