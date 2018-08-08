package com.macepost.auth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Auth() {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CheckAuthentication checkauthentication = new CheckAuthentication();
		String username = request.getParameter("username").toUpperCase();
		String password = request.getParameter("password");
		if(checkauthentication.check(username,password)) {
			System.out.println("login");
			session.setAttribute("Logedin", 1);
			response.sendRedirect("UserFeeds");
		}else
			response.sendRedirect("Login.jsp");
		

		
	}

}
