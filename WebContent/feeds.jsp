<%@ page import="com.macepost.feeds.TweetData" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%ArrayList<TweetData> tweetdata = (ArrayList<TweetData>) request.getAttribute("tweetdata");
	  //tweetdata = (TweetData[]) request.getAttribute("tweetdata");
	  //TweetData temp_tweetdata = new TweetData();
	  for(TweetData tdata : tweetdata){
		  //temp_tweetdata = tweetdata[i];
		  //out.print(temp_tweetdata.tweet_id);
		  out.println(tdata.user_id);
		  out.println(tdata.tweet_id);
		  out.println(tdata.likes);
		  out.println(tdata.tweet);
		  out.println(tdata.datetime);
	  }
	%>
</body>
</html>