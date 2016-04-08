<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
      <div align="center" style="background: green; border: solid 2px yellow;">
		<form method="post" action="logincode.jsp">
			<table>
				<tr>
					<th>Name</th>
					<td><input type="text" name="user" placeholder="userName">
					</td>
				</tr>
				<tr>
					<th>password</th>
					<td><input type="password" name="pass"
						placeholder="enter password"></td>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" value="Login"></th>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>