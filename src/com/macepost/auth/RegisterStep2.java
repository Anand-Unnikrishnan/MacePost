package com.macepost.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.macepost.global.Token;

/**
 * Servlet implementation class RegisterStep2
 */
@WebServlet("/RegisterStep2")
public class RegisterStep2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Token tokens;
       CheckAuthentication dB = new CheckAuthentication();
	public void init() {
		tokens = new Token();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getAttribute("success")!=null) {
			System.out.println("aas");
			request.setAttribute("token", tokens.getToken());
			RequestDispatcher rd = request.getRequestDispatcher("RegisterStep2.jsp");
			rd.forward(request, response);
			//response.sendRedirect("RegisterStep2.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getAttribute("success")!=null) {
			request.setAttribute("token", tokens.getToken());
			request.setAttribute("token", tokens.getToken());
			RequestDispatcher rd = request.getRequestDispatcher("RegisterStep2.jsp");
			rd.forward(request, response);
			//response.sendRedirect("RegisterStep2.jsp");
		}
		HttpSession session = request.getSession();
		if(request.getParameter("_token")!=null) {
			if(tokens.checkToken((String)request.getParameter("_token"))) {
				if(request.getAttribute("username")!="") {
					if(request.getAttribute("current_year")!="") {
						if(request.getAttribute("phone")!="") {
							try {
								dB.addUser(((String)session.getAttribute("admission")).toUpperCase(),(String)request.getParameter("username"),(String)request.getParameter("current_year"),(String)request.getParameter("phone"));
								dB.removeTemp((String)session.getAttribute("admission"));
								session.removeAttribute("admission");
								PrintWriter out = response.getWriter();
								out.print("success");
								session.setAttribute("succ", "Registration successful. Please login to continue");
								response.sendRedirect("Login.jsp");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}

		}
	}

}
