
<%@ page import="java.io.*,java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<% 
                    String un=request.getParameter("un");                    String ut=request.getParameter("ut");
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename","databaseusername","databasepassword");
                    Statement st=con.createStatement();
                    ResultSet rs = st.executeQuery("select * from table_name where username='"+un+"' AND usertype='"+ut+"' ");  // this is for name
                    if(rs.next())
                    {    
                        out.println("<font color=red>");
                        out.println("Name already taken");
                        out.println("</font>");

                    }else {
                        out.println("<font color=green>");
                        out.println("Available");
                        out.println("</font>");

                    }
rs.close();
st.close();
con.close();
%>