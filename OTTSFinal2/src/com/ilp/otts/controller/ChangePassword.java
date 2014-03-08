package com.ilp.otts.controller;

import java.sql.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.common.CommonResource;
import com.ilp.otts.db.DBConnection;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		String page=request.getParameter("page");
		System.out.println("in change pwd");
		if(page.equals("changepassword")){
			try{
				System.out.println("in change pwd");
				HttpSession session=request.getSession(false);
				String oldpwd=request.getParameter("oldpwd");
				String newpwd=request.getParameter("newpwd");
				String userid=(String)session.getAttribute("userid");
				String role=(String)session.getAttribute("role");
				String query;

				con=DBConnection.getConnection();
				Statement stmt=con.createStatement();

				query="select role from login where password='"+oldpwd+"'";
				ResultSet rs=stmt.executeQuery(query);

				if(rs.next()){
					query="update login set password='"+newpwd+"'"+
					",changepwd='0' where loginid='"+userid+"'";

					int status=stmt.executeUpdate(query);

					if(status==1)
						response.sendRedirect("WelcomeJSP/"+role+".jsp");
					con.commit();

				}else{
					System.out.println(" old password Mismatch");
					RequestDispatcher rd=request.getRequestDispatcher("/Other/ChangePassword.jsp");
					rd.include(request, response);
					response.getWriter().println("<div class=\"left\"><font color='red'>Password Change Failed</font></div>");
				}

			}catch(SQLException ex){
				ex.printStackTrace();
			}
			finally{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		}

		if(page.equals("get-secret-question")){
			String email=request.getParameter("email");
			System.out.println(email);

			con=DBConnection.getConnection();
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs=stmt.executeQuery("select loginid,role from login where username='"+email+"'");
				if(rs.next()){
					String id=rs.getString(1);
					String role=rs.getString(2).toLowerCase();
					request.setAttribute("userid",id);
					request.setAttribute("role",role);
					request.setAttribute("email",email);
					rs=stmt.executeQuery("select question from "+role+" where "+role+"id='"+id+"'");
					rs.next();
					request.setAttribute("secret-question",rs.getString(1));

				}else{
					request.setAttribute("username-error","notfound");
				}
				con.close();
				RequestDispatcher rd=request.getRequestDispatcher("Other/ForgotPassword.jsp");
				rd.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;

		}

		if(page.equals("check-secret-answer")){
			String userid=request.getParameter("userid");
			String role=request.getParameter("role");
			String answer=request.getParameter("secret-answer").toLowerCase();
			String username=request.getParameter("email");
			System.out.println(userid+role+answer+username);


			con=DBConnection.getConnection();
			Statement stmt;
			try {
				stmt = con.createStatement();
				
				ResultSet rs=stmt.executeQuery("select * from "+role+" where "+role+"id='"+userid+"' and answer='"+answer+"'");
				if(rs.next()){

					String newpwd=CommonResource.generatePassword();
					int status=stmt.executeUpdate("update login set changepwd=1, password='"+newpwd+"' where username='"+username+"'");
					if(status>0){
						request.setAttribute("newpwd",newpwd);
						request.setAttribute("status","Success");
					}
					else
						request.setAttribute("status","Failure");

				}else{
					request.setAttribute("answer-mismatch","Check your answer and try again");
				}
				con.commit();
				con.close();
				RequestDispatcher rd=request.getRequestDispatcher("Other/ForgotPassword.jsp");
				rd.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;

		}
	}

}
