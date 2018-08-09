<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Registration</title>
<% if(session.getAttribute("course")==null){
	response.sendRedirect("Register");
} %>
<style>
	input[type=text],input[type=password],select {
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

	input[type=text]:focus,input[type=password]:focus,select:focus {
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
	html,body{
	padding:0;
	margin:0;}
</style>
</head>
<body>
	<div style="height:100%; width:100%; display: flex;">
		<div class=bg style="width:60%;"></div>
		<div style="width:40%; height: 100%; background-color: white">
		<div style="height: 50px;"></div>
		<div style="border:solid; border-width:0.5px; border-radius: 5px; box-shadow:10px 10px 10px gray; width:80%; height:500; margin: auto; background-color: white"> 
			<div style="height: 50px;"></div>
			<div style="text-align: center; height:90px;"><img src="logo.png" alt="logo" style="width:200px;height:83px;"></div>
			
<form action="RegisterStep2" method="post">
<% if(request.getAttribute("error")!=null){ %>
<div style="text-align: center; color: red;">
<p>
<% out.print(request.getAttribute("error")); %>
</p>
</div>
<% } %>
				<div style="text-align: center; height:80px;">Register<br><br><input type="text" placeholder="Name" name="username" <% if(request.getAttribute("username")!=null){ %> value="<% out.print(request.getAttribute("username")); %>" <% } %> style="text-align: center;"></div>
				<div style="text-align: center; height:80px;">
					<select name="current_year">
					<% if("B".equals(session.getAttribute("course"))){ %>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<% }%>
					<% if("M".equals(session.getAttribute("course"))){ %>
					<option value="1">1</option>
					<option value="2">2</option>
					<% } 
					session.removeAttribute("course");
					%>
					</select>

				</div>
				<div style="text-align: center; height:50px;"><input type="text" name="phone" placeholder="Phone" <% if(request.getAttribute("phone")!=null){ %> value="<% out.print(request.getAttribute("phone")); %>" <% } %> style="text-align: center;"></div>
				<input type="hidden" name="_token" value="<% out.print(request.getAttribute("token")); %>">
				<div style="text-align: center; height: 60px;"><button type="submit" class= "button"; style="border-radius:100%; height:60px; width:60px;">Register</button></div>
</form>
		</div>
		<div></div>
		</div>
	</div>
</body>
</html>