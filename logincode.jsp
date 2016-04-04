<%@page import="com.beauty.service.LoginUser"%>
<%
	String userEmail = request.getParameter("useremail");
	String Password = request.getParameter("uswepassword");

	LoginUser login = new LoginUser();
	boolean check =login.loginUser(userEmail, Password);
	if(check==true){
		
		 String site = new String("http://localhost:8080/projectbeauty/");
		   response.setStatus(response.SC_MOVED_TEMPORARILY);
		   response.setHeader("Location", site); 
	}
	else{
		
	// out.println("invalid password and user name");
	}
%>