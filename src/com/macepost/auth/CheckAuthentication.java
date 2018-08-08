package com.macepost.auth;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class CheckAuthentication {
	Connection con;
	CheckAuthentication(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/macepost","root","root");
		}catch(Exception e) {System.out.println(e);}
	}
	public String check(String username, String password) {
		try{
			Statement statement = con.createStatement();
			ResultSet rs;
			rs = statement.executeQuery("select password from users WHERE user_name=\""+username+"\";");
			rs.next();
			return rs.getString("password");
		}catch(Exception e) {System.out.println(e);}
		return ("Error");
	}
}