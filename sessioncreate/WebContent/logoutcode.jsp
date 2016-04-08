<%
String user = (String)session.getAttribute("user");
String pass=(String)session.getAttribute("password");
if (request.getParameter("logout") != null) { 
	session.removeAttribute("user");
	session.removeAttribute("password");
    session.invalidate();
    response.sendRedirect("login.jsp");
  
}
else{
	
	 response.sendRedirect("error.jsp");	
}

%>