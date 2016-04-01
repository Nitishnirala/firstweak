<!DOCTYPE html>
<html>  
<head>  
<script>  
var request;  
function sendInfo()  
{  
var username=document.vinform.user.value;  
var password=document.vinform.pass.value;  
var url="admincode.jsp?username="+username+"&password="+password;  
if(window.XMLHttpRequest){  
request=new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try  
{  
request.onreadystatechange=getInfo;  
request.open("GET",url,true);  
request.send();  
}  
catch(e)  
{  
alert("Unable to connect to server");  
}  
}    
function getInfo(){  
if(request.readyState==4){  
var username=request.responseText; 
var password=request.responseText;
document.getElementById('nitish').innerHTML=username;  
document.getElementById('password').innerHTML=password; 
}  
}  

</script>  
</head>  
<body>  
    <marquee><h1>This is an example of ajax</h1></marquee>  
<form name="vinform">  
UserEmail:<input type="text" name="user">  
Password:<input type="password" name="pass">  
<input type="button" value="Send" onClick="sendInfo()">  
</form>  
<div><a href="admin.jsp">Admin</a></div>
<div align="center" style="background: yellow; border: 10px solid red;">
<span id="nitish" style="font-family: cursive; font-size: 30px;"> </span>  
<span id="password"style="font-family: cursive; font-size: 30px;"> </span>
  </div> 
</body>  
</html>  