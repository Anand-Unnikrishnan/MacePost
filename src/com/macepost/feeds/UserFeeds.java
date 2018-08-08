package com.macepost.feeds;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Servlet implementation class UserFeeds
 */
@WebServlet("/UserFeeds")
public class UserFeeds extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Connection con;
    public UserFeeds() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/macepost","root","root");
		}catch(Exception e) {e.printStackTrace();}
    }
    
    protected void processrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
			Statement statement = con.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select* from tweets;");
			PrintWriter out = response.getWriter();
			ArrayList<TweetData> tweetdata = new ArrayList<TweetData>();
			TweetData temp_tweetdata = new TweetData();
			//String s = "";
			int count=0;
			while(rs.next()) {
				//out.print(rs.getString("tweet_id")+" "+rs.getString("user_id")+" "+rs.getString("likes")+" "+rs.getString("tweet")+" "+rs.getString("datetime"));
				temp_tweetdata.setdata(rs.getInt("tweet_id"), rs.getInt("user_id"), rs.getInt("likes"), rs.getString("tweet"), rs.getString("datetime"));
				tweetdata.add(temp_tweetdata);
				count++;
			}
			//out.print(s);
			request.setAttribute("tweetdata", tweetdata);
			RequestDispatcher rd = request.getRequestDispatcher("feeds.jsp");
			rd.forward(request, response);
		}catch(Exception e) {e.printStackTrace();}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(Integer.parseInt(session.getAttribute("Logedin").toString())==1) {
			processrequest(request,response);
		}
		else {
			PrintWriter out = response.getWriter();
			out.print("Pling!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
