package com.macepost.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.macepost.global.Token;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Token tokens;
	CheckAuthentication dB = new CheckAuthentication();
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
				int res;
				try {
					res = addUser(request);
					if(res!=1) {
					switch(res) {
						case -1:request.setAttribute("error", "invalid id");
								break;
						case 0:
							request.setAttribute("error", "Enter admission number");
							break;
						case -2:
							request.setAttribute("error", "Password Should be greater than 8 char");
							request.setAttribute("admissionno", request.getParameter("admissionno"));
							break;
						case -3:
							request.setAttribute("admissionno", request.getParameter("admissionno"));
							request.setAttribute("error", "Password Confirmation error");
							break;
						case -4:
							request.setAttribute("error", "Enter confirm password");
							request.setAttribute("admissionno", request.getParameter("admissionno"));
							break;
						case -5:
							request.setAttribute("error", "Enter password");
							request.setAttribute("admissionno", request.getParameter("admissionno"));
							break;
						case -6:
							request.setAttribute("error", "You already have an account. Please login");
							
					}
					request.setAttribute("token", tokens.getToken());
					RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
					rd.forward(request, response);
					}
					else {
						try {
							dB.addTemp((String)request.getParameter("admissionno"), (String)request.getParameter("password"));
						} catch (SQLException | NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						HttpSession session = request.getSession();
						session.setAttribute("course", course);
						session.setAttribute("admission", request.getParameter("admissionno"));
						request.setAttribute("success", "true");
						RequestDispatcher rd = request.getRequestDispatcher("RegisterStep2");
						
						rd.forward(request, response);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}else {
				response.sendRedirect("Register");
			}

		}
	}
	
	private ArrayList<String> courses = new ArrayList<>(Arrays.asList("B","M"));
	private ArrayList<String> branches = new ArrayList<>(Arrays.asList("CS","ME","EC","EE"));
	String course;
	public int validateAdmissionNo(String admission) throws SQLException {
		course = admission.substring(0, 1).toUpperCase();
		
		if(admission.length()==8) {
			if(courses.contains(course)) {
				String year = admission.substring(1,3);
				int curryear = Year.now().getValue();
				year = "20"+year;
				int yer = Integer.parseInt(year);
				if(yer<=curryear) {
					String branch = admission.substring(3,5).toUpperCase();
					if(branches.contains(branch)) {
						int number = Integer.parseInt(admission.substring(5));
						System.out.println(number);
						if((number>0) && (number<999)) {
							if(!dB.userExists(admission.toUpperCase()))
								return 1;
							else
								return -1;
						}
					}
				}
			}
		}

		return 0;
	}
	
	public int addUser(HttpServletRequest request) throws SQLException {
		if(request.getParameter("admissionno")!="") {
			switch(validateAdmissionNo(request.getParameter("admissionno"))) {
				case 0:
					return -1;
				case -1:
					return -6;
			}
			if(request.getParameter("password")!="") {
				if(request.getParameter("password").length()<8) {
					return -2;
				}
				if(request.getParameter("confirmpass")!="") {
					if(request.getParameter("password").equals(request.getParameter("confirmpass"))) {
						String username = request.getParameter("admissionno");
						String password = request.getParameter("password");
						//dB.addUser(username, password);
						return 1;
					}
					else {
						return -3;
					}
				}else {
					return -4;
				}
			}else {
				return -5;
			}
			
		}else {
			return 0;
		}
		
	}
	


}
