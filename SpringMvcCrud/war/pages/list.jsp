<%@ page import="java.util.List" %>
<%@ page import="com.spring.model.Customer" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<html>
<head>
<style type="text/css">
h1{
 background-color:teal;
 font-family: cursive;
 font-size: 30px;
 font: bold;
}
 a:HOVER {
	text-decoration: none;
	color: green;
	font: italic;
	font-family: fantasy;
	font-size: 30px;
}
a{
text-decoration: none;
text-indent: cm;
color: blue;

}
table{
  border:2px solid;
  border-color:teal;
  border-left-style: dashed;
  border-right-style: dashed;
  border-left-color: red;
  border-right-color: red;
  }
 td {
    padding: 5px;
  }
 th {
    text-align: left;
  }
</style>
<title>List Of Record</title>
</head>
<body>
	<h1 align="center">CRUD Example with JDO</h1>

	<b>Function :</b> <a href="add">Add Customer</a>
	<hr />
<div align="center">
	<h2 align="center">All Customers</h2>
	<table >
		<thead>
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Address</td>
				<td>ZipCode</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		
		<%
			
			if(request.getAttribute("customerList")!=null){
				
				List<Customer> customers = (List<Customer>)request.getAttribute("customerList");
				
				if(!customers.isEmpty()){
					 for(Customer c : customers){
						 
		%>
					<tr>
					  <td><%=c.getName() %></td>
					  <td><%=c.getEmail() %></td>
					  <td><%=c.getAddress() %></td>
					  <td><%=c.getZipCode() %></td>
					  <td><%=c.getDate() %></td>
					  <td><a href="update/<%=c.getName()%>">Update</a> 
		                   | <a href="delete/<%=KeyFactory.keyToString(c.getKey()) %>">Delete</a></td>
					</tr>
		<%	
			
					}
		    
				}
			
		   	}
		%>
         
        </tr>
     
	</table>
</div>
</body>
</html>