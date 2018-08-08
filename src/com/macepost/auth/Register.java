package com.macepost.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.macepost.global.Token;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Token tokens;
	public void init() {
		tokens = new Token();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String token = tokens.getToken();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
		request.setAttribute("token", token);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		if(request.getParameter("_token")!=null) {
			String token = request.getParameter("_token");
			if(tokens.checkToken(token)) {
				addUser(request);
			}else {
				out.print("Stop");
			}

		}
	}
	
	public void addUser(HttpServletRequest request) {
		if(request.getParameter("username")!=null) {
			if(request.getParameter("password")!=null) {
				if(request.getParameter("confirmpass")!=null) {
					if(request.getParameter("password").equals(request.getParameter("confirmpass"))) {
						
					}
				}
			}
		}
	}

}
