

<%@ page import="test.*" %>
<%@page import="javax.mail.Multipart" %>
<%
String toemail=request.getParameter("toemail");
String subject=request.getParameter("subjects");
String content=request.getParameter("content");
Gmail gmail=new Gmail();
gmail.sendMessage(toemail, subject, content);
%>-