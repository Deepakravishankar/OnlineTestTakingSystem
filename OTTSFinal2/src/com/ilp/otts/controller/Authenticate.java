package com.ilp.otts.controller;

import java.io.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.db.DBConnection;

/**
 * Servlet implementation class Authenticate
 */
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		System.out.println(username+" "+password);
		try{
		Connection con=DBConnection.getConnection();
		if(con==null)
			System.out.println("con is null");
		Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select loginid,role,changepwd,accesslevel from login where username='" +username+"' and password='"+password+"'");
		
		if(rs.next()){
			HttpSession session=request.getSession();
			//session.setMaxInactiveInterval(180);
	
			session.setAttribute("userid",rs.getString(1));
			session.setAttribute("name",username);
			session.setAttribute("role",rs.getString(2));
			session.setAttribute("accesslevel",rs.getString(4));
			if(rs.getInt(3)==1){
				//response.sendRedirect("Other/ChangePassword.jsp");
				RequestDispatcher rd=request.getRequestDispatcher("Other/ChangePassword.jsp");
				rd.include(request, response);
				response.getWriter().println("<div class=\"left\"><font color='red'>Your Password Expired Change Immediately</font></div>");
			}
			else
				response.sendRedirect("WelcomeJSP/"+rs.getString(2)+".jsp");
			System.out.println(rs.getString(1));
		}
		else{
			System.out.println("Login Mismatch");
			RequestDispatcher rd=request.getRequestDispatcher("/WelcomeJSP/Login.jsp");
			rd.include(request, response);
			response.getWriter().println("<font color='red'>Login Failed Check USERNAME/PASSWORD</font>");
			
			
		}
		con.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

}
