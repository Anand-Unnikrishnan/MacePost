package com.macepost.auth;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class CheckAuthentication {
	public String check(String username, String password) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/macepost","root","root");
			Statement statement = con.createStatement();
			ResultSet rs;
			String query = "select password from users WHERE user_name=\""+username+"\";";
			System.out.println(query);
			rs = statement.executeQuery(query);
			rs.next();
			String s = "password : "+rs.getString("password");
			return s;
		}catch(Exception e) {System.out.println(e);}
		return ("Error");
	}
}