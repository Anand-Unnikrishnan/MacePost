package com.macepost.auth;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;


public class CheckAuthentication {
	Connection con;
	public CheckAuthentication() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/macepost","root","");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public boolean check(String admission, String password) {
		try{
			Statement statement = con.createStatement();
			ResultSet rs;
			admission = admission.toUpperCase();
			rs = statement.executeQuery("select password from users WHERE admission_no='"+admission+"';");
			if(rs.next()) {
				if(BCrypt.checkpw(password,rs.getString("password"))) {
					return true;
				}
					
			}	
			return false;
			
		}catch(Exception e) {System.out.println(e);}
		return false;
	}
	
	public void addUser(String admission,String username,String year,String phone) throws SQLException {
		Statement statement = con.createStatement();
		int rs;
		String query = "select password from tempdata WHERE admission=\""+admission+"\";";
		ResultSet res;
		System.out.println(year);
		res = statement.executeQuery(query);
		if(res.next()) {
			String password = res.getString("password");
			System.out.println("password");
			query = "insert into users(admission_no,user_name,current_year,password,phone) values('"+admission+"','"+username+"','"+year+"','"+password+"','"+phone+"')";
			rs = statement.executeUpdate(query);
		}
	}
	public void addTemp(String admission,String password) throws SQLException, NoSuchAlgorithmException {
		Statement statement = con.createStatement();
		int rs;
		String query = "select 1 from tempdata WHERE admission=\""+admission+"\";";
		ResultSet res;
		System.out.println(password);
		password = BCrypt.hashpw(password, BCrypt.gensalt());
		res = statement.executeQuery(query);
		if(res.next()) {
			query = "update tempdata set password='"+password+"'";
			rs = statement.executeUpdate(query);
		}else {
			query = "insert into tempdata(admission,password) values('"+admission+"','"+password+"')";
			rs = statement.executeUpdate(query);
		}

		
	}
	
	public boolean userExists(String admission) throws SQLException {
		Statement statement = con.createStatement();
		ResultSet rs;
		String query = "select 1 from users WHERE admission_no='"+admission+"';";
		rs = statement.executeQuery(query);
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public void removeTemp(String admission) throws SQLException {
		Statement statement = con.createStatement();
		String query = "delete from tempdata WHERE admission='"+admission+"';";
		int res;
		res = statement.executeUpdate(query);
	}
	

}