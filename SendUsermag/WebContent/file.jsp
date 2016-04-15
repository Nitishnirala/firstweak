

<%@page import="test.SendAttachmentInEmail"%>
<%
String toemail=request.getParameter("toemail");
String  name=request.getParameter("foo");
String  directory=request.getParameter("myFile");

SendAttachmentInEmail send=new SendAttachmentInEmail();
send.attachFile(toemail, name, directory);

%>