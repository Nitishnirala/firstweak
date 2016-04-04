<%@page import="com.beauty.service.RegisterNewUser"%>
<%

String username=request.getParameter("user_name");
String useremail=request.getParameter("user_email");
String password=request.getParameter("user_password");
String userage=request.getParameter("user_age");
String location=request.getParameter("user_Loction");
RegisterNewUser user=new RegisterNewUser();
user.insertNewRegisterUser(username, useremail, password, userage, location);

%>