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
</head>
<body>
<form action="RegisterStep2" method="post">
<% if(request.getAttribute("error")!=null){ %>
<div>
<p>
<% out.print(request.getAttribute("error")); %>
</p>
</div>
<% } %>
<input type="text" name="username" placeholder="Name" <% if(request.getAttribute("username")!=null){ %> value="<% out.print(request.getAttribute("username")); %>" <% } %>>
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
<input type="text" name="phone" placeholder="Phone" <% if(request.getAttribute("phone")!=null){ %> value="<% out.print(request.getAttribute("phone")); %>" <% } %>>
<input type="hidden" name="_token" value="<% out.print(request.getAttribute("token")); %>">
<button type="submit">Submit</button>
</form>
</body>
</html>