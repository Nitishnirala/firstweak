<%
String username=request.getParameter("user");
String password=request.getParameter("pass");
String user = (String)session.getAttribute("user");
String pass=(String)session.getAttribute("password");
if((user.equals(username))&&(pass.equals(password)) ){

	 response.sendRedirect("logout.jsp");
}
else{
	
	response.sendRedirect("error.jsp");
}
%>