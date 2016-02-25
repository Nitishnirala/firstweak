<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

 table{
  border:2px solid;
  border-color:green;

  }
  th, td {
    padding: 7px;
  }
 th {
    text-align: center;
  }
  h1{
  
  margin: 10px;
  padding: 20px;
  background-color: black;
  color: green;
  font: italic;
  font-family: cursive;
  font-size: 30px;
  text-align: center;
  }
</style>
<title>redirect</title>
</head>
<body>
<h1>Login Here</h1>
<form action="/chitchat">
<table align="center">
<tr>
<th>User_Name</th><td>
<input type="text" name="nickname"></td></tr>
<tr>
<th> Gender</th>
<td>
<input type="radio" name="imgsel"  value="male" checked="checked" />Male
<input type="radio" name="imgsel"  value="female" checked="checked" />Female
</td>
</tr>
<tr>
<th>Age
</th><td><input type="number" name="age"></td></tr>
<tr>
<th></th><td><input type="submit" value="start"></td></tr>
</table>
</form>
</body>
</html>