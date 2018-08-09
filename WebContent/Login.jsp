<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
	input[type=text],input[type=password] {
    width: 130px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
    background-color: white;
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding: 5px 5px 5px 5px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
	}

	input[type=text]:focus,input[type=password]:focus {
		width: 70%;
	}
	.button{
	background-color:white;
	color:black;
	border: 1px solid #ccc;
	-webkit-transition-duration: 0.4s;
	transition-duration: 0.4s;
    cursor: pointer;
	}
	.button:hover{
	background-color:black;
	color:white;
	}
	.bg{
	background-image: url("nature.jpg");
	background-size: auto;
	}
</style>
</head>
<body>
	<div style="height:100%; width:100%; display: flex;">
		<div class=bg style="width:60%;"></div>
		<div style="width:40%; height: 100%; background-color: white">
		<div style="height: 50px;"></div>
		<div style="border:solid; border-width:0.5px; border-radius: 5px; box-shadow:10px 10px 10px gray; width:80%; height:500; margin: auto; background-color: white"> 
			<div style="height: 50px;"></div>
			<div style="text-align: center; height:120px;"><img src="logo.png" alt="logo" style="width:200px;height:83px;"></div>
			<form method="post" action="Auth">
			<% if(session.getAttribute("succ")!=null){ %>
				<div style="text-align: center; color: green;">
				<p>
				<% out.print(session.getAttribute("succ")); session.removeAttribute("succ"); %>
				</p>
				</div>
			<% } %>
			 	<div style="text-align: center; height:80px;">Login<br><br><input type="text" name="username" placeholder="Admission No" style="text-align: center;"></div>
			 	<div style="text-align: center; height:50px"><input type="password" name="password" placeholder="Password" style="text-align: center;"></div>
				<div style="text-align: center; height: 70px;"><button type="submit" class= "button"; style="border-radius:100%; height:50px; width:50px;"> login </button></div>
			 
			</form>
		<div style=" margin: auto; text-align: center; color: blue;">Haven't registered yet? <p><b><a href="Register" style="color: red; font-size:12px">Register here</a></b></p></div>
		</div>
		<div></div>
		</div>
	</div>
</body>
</html>