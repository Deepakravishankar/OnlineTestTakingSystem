package com.ilp.otts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ilp.otts.dao.QuestionDAO;
import com.ilp.otts.bean.QuestionBean;
import com.ilp.otts.common.CommonResource;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	QuestionBean qb=new QuestionBean();
	QuestionDAO qd=new QuestionDAO();



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionController() {
		super();


		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<QuestionBean> al1 =qd.view();
		request.setAttribute("s", al1);
		//for(int i=0;i<al.size();i++){
		//	System.out.println(al.get(i).getQuestionid());
		//}
		RequestDispatcher r1=getServletContext().getRequestDispatcher("/jsp/viewquestions.jsp");
		r1.forward(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		try
		{
			if(request.getParameter("page").equalsIgnoreCase("createquestion")){
				String questionid=CommonResource.getNextId("question");
				String question=request.getParameter("questionname");
				String category=request.getParameter("Category");
				String option1=request.getParameter("option1");
				String option2=request.getParameter("option2");
				String option3=request.getParameter("option3");
				String option4=request.getParameter("option4");
				String answer=request.getParameter("answer");

				QuestionBean q=new QuestionBean();
				q.setQuestionid(questionid);
				q.setQuestion(question);
				q.setCategory(category);
				q.setOption1(option1);
				q.setOption2(option2);
				q.setOption3(option3);
				q.setOption4(option4);
				q.setAnswer(answer);
				QuestionDAO.createQuestion(q);
				response.sendRedirect("/OTTS/jsp/CreateQuestion.jsp");

			}

			if(request.getParameter("page").equalsIgnoreCase("delete"))
			{
				String questionid = request.getParameter("QuestionId");	
				QuestionBean a = new QuestionBean();
				a.setQuestionid(questionid);
				QuestionDAO b = new QuestionDAO();
				QuestionBean obj1 = b.DeleteQuestions(a);
				getServletContext()
				.getRequestDispatcher("/jsp/Success.jsp").forward(
						request, response);
			}
			if(request.getParameter("page").equalsIgnoreCase("update"))
			{
				String questionid = request.getParameter("QuestionId");	
				String question = request.getParameter("Question");	
				String category = request.getParameter("Category");	
				String option1 = request.getParameter("Option1");	
				String option2= request.getParameter("Option2");
				String option3 = request.getParameter("Option3");	
				String option4 = request.getParameter("Option4");	
				String answer = request.getParameter("answer");	

				QuestionBean a = new QuestionBean();
				a.setQuestionid(questionid);
				a.setQuestion(question);
				a.setCategory(category);
				a.setOption1(option1);
				a.setOption2(option2);
				a.setOption3(option3);
				a.setOption4(option4);
				a.setAnswer(answer);

				QuestionDAO b = new QuestionDAO();
				QuestionBean obj1 = b.UpdateQuestions(a);
				getServletContext()
				.getRequestDispatcher("/jsp/Success.jsp").forward(
						request, response);
			}
			if(request.getParameter("page").equalsIgnoreCase("view")){
				String qid=request.getParameter("QuestionId");
				QuestionBean v =new QuestionBean();
				QuestionDAO obj = new QuestionDAO();
				String total = obj.viewQuestion(qid);
				PrintWriter out=response.getWriter();
				response.setContentType("text/html");

				out.println("<table border=1><thead><tr>");
				out.println("<th><B>QUESTION_ID</B></th>");
				out.println("<th><B>QUESTION</B></th>");
				out.println("<TH><B>CATEGORY</B></TH>");
				out.println("<th><B>OPTION1</B></th>");
				out.println("<th><B>OPTION2</B></th>");
				out.println("<th><B>OPTION3</B></th>");
				out.println("<th><B>OPTION4</B></th>");
				out.println("<th><B>ANSWER</B></th><</tr></thead>");
				out.println("<tr><td>");
				out.println(obj.getquestionid());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getquestion());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getcategory());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getoption1());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getoption2());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getoption3());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getoption4());
				out.println("</td>");
				out.println("<td>");
				out.println(obj.getanswer());
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<a href=http://localhost:8080/OnlineTestTakingSystem/jsp/adminlogin.jsp> Go Back To Main Page</a>");
				//getServletContext().getRequestDispatcher("/jsp/Success.jsp").forward(request, response);
			}
		} 
		catch (Exception e) {
		} 
		finally {
		}
	}
}
