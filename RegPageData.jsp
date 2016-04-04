<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="html/css" href="background.css" rel="stylesheet" />
</head>
<body>
	<table align="center">
		<caption>Employee_registration form</caption>

		<tr>
			<td>First_name</td>
			<td><input type="text"
				value="<%=request.getAttribute("showname")%>" /></td>
		</tr>

		<tr>
			<td>Last_name</td>
			<td><input type="text"
				value="<%=request.getAttribute("showlastname")%>" /></td>
		</tr>

		<tr>
			<td>Mobile Number</td>
			<td><input type="text"
				value="<%=request.getAttribute("showmobile")%>" /></td>
		</tr>

		<tr>
			<td>E-mail</td>
			<td><input type="text"
				value="<%=request.getAttribute("showemail")%>" /></td>
		</tr>

		<tr>
			<td>Age</td>
			<td><input type="text"
				value="<%=request.getAttribute("showage")%>" /></td>
		</tr>

		<tr>
			<th></th>
			<td><a href="loginpage.html"> <input type="submit"
					value="GoBack" onclick="check()" /></a></td>
		</tr>

	</table>

</body>
</html>