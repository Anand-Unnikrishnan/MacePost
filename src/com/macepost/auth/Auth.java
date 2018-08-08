package com.macepost.auth;

import java.io.IOException;
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

    /**
     * Default constructor. 
     */
    public Auth() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		CheckAuthentication checkauthentication = new CheckAuthentication();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String actual_password = checkauthentication.check(username,password);
		//out.print("sent : "+password+" actual : "+actual_password+"\n");
		if(password.equals(actual_password)) {
			session.setAttribute("Logedin", 1);
			response.sendRedirect("UserFeeds");
		}
		else
			response.sendRedirect("Login.jsp");
	}

}
