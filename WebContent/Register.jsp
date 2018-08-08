<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
<form action="Register" method="post">
<input type="text" name="username">
<input type="password" name="password">
<input type="hidden" name="_token" value="<% out.print(request.getAttribute("token")); %>">
<button type="submit">Submit</button>
</form>
</body>
</html>