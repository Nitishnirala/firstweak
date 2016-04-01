
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%!static Connection con;%>
<%!  static String url="jdbc:mysql://localhost:3306/dbname?";%>
<%! static String userName = "dbUserName"; %>
<%! static String password = "dbPassword";%>
 <%
         Class.forName("com.mysql.jdbc.Driver");
         con=(Connection)DriverManager.getConnection(url, userName, password);
         Statement s = (Statement)con.createStatement();
          ResultSet rs=s.executeQuery("SELECT * FROM usertype");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
var usertype = document.getElementById("utype");
var userType= usertype .options[usertype .selectedIndex].value;
var uname=document.getElementById("username").value;
var urls="checkusername.jsp?un="+uname+"&ut="+userType;

if (window.XMLHttpRequest)
  {
  xmlhttp=new XMLHttpRequest();
  }
else
  {
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4)
    {
        document.getElementById("err").innerHTML=xmlhttp.responseText;
     }
  }
xmlhttp.open("GET",urls,true);
xmlhttp.send();
}
</script>
    </head>
    <body>
<%

%> 
        User Type<select name="utype" id="utype">
<%while(rs.next()){
 String userType=rs.getString("userTypeColumnName");
%> 
                              <option  value="<%=userType%>"><%=userType%></option>
<%}%>
                        </select>
        User Name: <input type="text" name="username" id="username" onkeyup="loadXMLDoc()">
        <span id="err"> </span>
    </body>
</html>
