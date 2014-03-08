package com.ilp.otts.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.bean.AdminBean;
import com.ilp.otts.bean.TeacherBean;
import com.ilp.otts.dao.AdminDao;
import com.ilp.otts.dao.TeacherDao;

/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
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
		String page=request.getParameter("page");
		HttpSession session=request.getSession(false);
		String role=(String)session.getAttribute("role");
		String id=(String)session.getAttribute("userid");

		if(page.equals("profilepage")){
			if(role.equals("Admin")){
				ArrayList<AdminBean> adminList=AdminDao.viewAdminId(id);
				request.setAttribute("result", adminList);
				RequestDispatcher rd=request.getRequestDispatcher("Other/"+role+"ProfilePage.jsp");
				rd.forward(request, response);
			}
			if(role.equals("Teacher")){
				ArrayList<TeacherBean> List=TeacherDao.viewTeacherId(id);
				request.setAttribute("result", List);
				RequestDispatcher rd=request.getRequestDispatcher("Other/"+role+"ProfilePage.jsp");
				rd.forward(request, response);
			}
		}
	}

}
