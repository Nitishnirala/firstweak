<%@page import="com.beauty.service.BlichingService"%>
<%
BlichingService blich=new BlichingService();
blich.full_Body();
blich.Full_Hands();
blich.Full_legs();
blich.underArmas();
blich.Back();
blich.upper_Lip();
blich.addAll();
%>