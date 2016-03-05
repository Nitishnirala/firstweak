<html>
<head>
<title>Add record</title>
<style type="text/css">

table{
  border:2px solid;
  border-color:teal;
  
  }
 td {
    padding: 5px;
  }
 th {
    text-align: left;
  }
h1{
border-bottom-color: lime;
color: #080A0D;
font: italic;
font-family: cursive;
font-size: 45px;


  -webkit-animation: bgcolorchange 4s infinite;  
  animation: 4s infinite bgcolorchange;
}
@keyframes bgcolorchange {
  0% {
    background-color: red;
  }
  25% {
    background-color: green;
   }
  50% {
    background-color: #2E3B0B;
  }
  75% {
    background-color: #585858;
  }
  100% {
    background-color: red;
  }
}

 @-webkit-keyframes bgcolorchange {
      0%   {background: red;}
      25%  {background: #2E3B0B;}
      75%  {background: green;}
      100% {background: blue;}
 }
 }
</style>
</head>
<body>
	<h1 align="center"><b>Add Customer</b></h1>
    <div align="center">
	<form method="post" action="add">
		<table>
			<tr>
				<td>UserName :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="name" id="name" /></span></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="email" id="email" /></span></td>
			</tr>
			<tr>
				<td>Address :</td>
				<td><input type="text" style="width: 185px;" maxlength="30"
					name="address" id="address" /></span></td>
			</tr>
			<tr>
				<td>ZipCode :</td>
				<td><input type="text" style="width: 185px;" maxlength="7"
					name="zipCode" id="zipCode" /></span></td>
			</tr>
		</table>
		<input type="submit" class="save" title="Save" value="Save" />
	</form>
</div>
</body>
</html>