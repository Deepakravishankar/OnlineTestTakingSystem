package com.ilp.otts.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.dao.TestDAO;
import com.ilp.otts.bean.*;

import com.ilp.otts.common.CommonResource;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class TestController
 */
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		if(request.getParameter("page").equalsIgnoreCase("createtest1")){
			try{
			HttpSession session = request.getSession();
			String testid=CommonResource.getNextId("test");
			String testName=request.getParameter("tname");
			String duration=(request.getParameter("dur"));
			String noofStudents=(request.getParameter("noofstud"));
			String category= request.getParameter("category");
			TestBean tb=new TestBean();
			tb.setTestid(testid);
			tb.setCategory(category);
			tb.setTestName(testName);
			tb.setDuration(duration);
			tb.setNoofStudents(noofStudents);
			TestDAO obj = new TestDAO();
			TestBean object1=obj.createTest1(tb);
			ArrayList<TestTeacherBean> ttb=obj.ViewTeacherTest(tb);
			session.setAttribute("ctc", tb.getCategory());
			session.setAttribute("ctt", tb.getTestid());
			session.setAttribute("ttb", ttb);
			response.sendRedirect("http://localhost:8080/OTTS/jsp/CreateTest2.jsp");
			

		}
	catch(Exception e){
		e.printStackTrace();
	}
		}
	
	if(request.getParameter("page").equalsIgnoreCase("createtest2")){
		try{
		HttpSession session = request.getSession(false);
		String[] teacherid=request.getParameterValues("testteacher");
		String testid=(String)session.getAttribute("ctt");
		String category=(String)session.getAttribute("ctc");
		ArrayList<TestTeacherBean> ttb=new ArrayList<TestTeacherBean>();
		for(int i=0;i<teacherid.length;i++){
			TestTeacherBean t=new TestTeacherBean();
			t.setTeacherid(teacherid[i]);
			t.setTestid(testid);
			ttb.add(t);
		}
		TestDAO obj=new TestDAO();
		obj.addTestTeacher(ttb);
		ArrayList<TestQuestionBean> tqb=obj.viewTestQuestion(category,testid);
		session.setAttribute("tqb", tqb);
		response.sendRedirect("http://localhost:8080/OTTS/jsp/CreateTest3.jsp");
		}catch(Exception e){
			
		}
	}
	
	if(request.getParameter("page").equalsIgnoreCase("createtest3")){
		try{
		HttpSession session = request.getSession(false);
		String[] questionid=request.getParameterValues("testquestion");
		String testid=(String)session.getAttribute("ctt");
		ArrayList<TestQuestionBean> tqb=new ArrayList<TestQuestionBean>();
		for(int i=0;i<questionid.length;i++){
			TestQuestionBean t=new TestQuestionBean();
			t.setQuestionid(questionid[i]);
			t.setTestid(testid);
			tqb.add(t);
		}
		TestDAO obj=new TestDAO();
		obj.addTestQuestion(tqb);
		response.sendRedirect("/OTTS/jsp/Success.jsp");
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetest1")){
		try{
			String testid=request.getParameter("testid");
			HttpSession session=request.getSession(true);
			if(testid!=null){
			session.setAttribute("testid", testid);}
			TestDAO td=new TestDAO();
			TestBean tb=new TestBean();
			tb=td.updateViewTest(testid);
			request.setAttribute("updateviewtest", tb);
			getServletContext().getRequestDispatcher("/jsp/UpdateTest2.jsp")
			.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
	}
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetest2")){
		try{
			HttpSession session=request.getSession(true);
			String testid=(String)session.getAttribute("testid");
			String testName=request.getParameter("testname");
			String duration=request.getParameter("duration");
			String noofStudents=request.getParameter("noofstudents");
			TestBean tb=new TestBean();
			tb.setTestid(testid);
			tb.setTestName(testName);
			tb.setDuration(duration);
			tb.setNoofStudents(noofStudents);
			TestDAO t=new TestDAO();
			t.updateTest(tb);
			response.sendRedirect("OTTS/Success.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	if(request.getParameter("page").equalsIgnoreCase("viewteststudent")){
		HttpSession session=request.getSession(true);
		ArrayList<TestBean> tb=new ArrayList<TestBean>();
		
		tb=TestDAO.getStudentTests();
		System.out.println(tb.get(0).getTestid());
		session.setAttribute("teststud", tb);
		getServletContext().getRequestDispatcher("/jsp/TestStudent1.jsp")
		.forward(request, response);
	}
	if(request.getParameter("page").equalsIgnoreCase("teststudent1")){
		try{
			HttpSession session=request.getSession(true);
			String testid=request.getParameter("test");
			TestDAO td=new TestDAO();
			ArrayList<TestStudentBean> tsb=new ArrayList<TestStudentBean>();
			tsb=td.getStudents(testid);
			session.setAttribute("studs", tsb);
			session.setAttribute("testid",testid);
			getServletContext().getRequestDispatcher("/jsp/TestStudent2.jsp")
			.forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	if(request.getParameter("page").equalsIgnoreCase("teststudentassociation")){
		try{
			HttpSession session=request.getSession(true);
			String[] students=request.getParameterValues("studenttest");
			String testid=(String)session.getAttribute("testid");
			TestDAO td=new TestDAO();
			ArrayList<TestStudentBean> tqb=new ArrayList<TestStudentBean>();
			for(int i=0;i<students.length;i++){
				System.out.println(students[i]);
				TestStudentBean t=new TestStudentBean();
				t.setStudentid(students[i]);
				t.setTestid(testid);
				tqb.add(t);
			}
			td.addStudentTest(tqb);
			response.sendRedirect("/OTTS/jsp/Success.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetestview")){
		ArrayList<TestBean> test=new ArrayList<TestBean>();
		HttpSession session=request.getSession();
		TestDAO td=new TestDAO();
		test=td.viewUpdateTest();
		session.setAttribute("viewupdatetestid",test);
		getServletContext().getRequestDispatcher("/jsp/UpdateTest4.jsp").forward(request, response);
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetestid")){
		String testid=request.getParameter("testid"); 
		HttpSession session=request.getSession(true);
		session.setAttribute("testid",testid);
		getServletContext().getRequestDispatcher("/jsp/UpdateTest3.jsp").forward(request, response);
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetest3")){
		ArrayList<String> tqb1=new ArrayList<String>();
		ArrayList<TestQuestionBean> tqb2=new ArrayList<TestQuestionBean>();
		HttpSession session=request.getSession(true);
		String testid=(String)session.getAttribute("testid");
		
		
		tqb1=TestDAO.updateTestQuestion1(testid);
		tqb2=TestDAO.updateTestQuestion2(testid);
			for(int i=0;i<tqb2.size();i++){
			System.out.println(tqb2.get(i).getQuestionid());
		}
		request.setAttribute("selectedquestion", tqb1);
		request.setAttribute("allquestion", tqb2);
		getServletContext().getRequestDispatcher("/jsp/UpdateTest5.jsp").forward(request, response);
		
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetestquestion")){
		String[] qid=request.getParameterValues("testquestion");
		HttpSession session=request.getSession(true);
		String testid=(String)session.getAttribute("testid");
		ArrayList<TestQuestionBean> tqb=new ArrayList<TestQuestionBean>();
		for(int i=0;i<qid.length;i++){
		TestQuestionBean t=new TestQuestionBean();
		t.setTestid(testid);
		t.setQuestionid(qid[i]);
		tqb.add(t);
		}
		TestDAO.updateTestQuestionFinal(tqb);
		response.sendRedirect("/OTTS/Success.jsp");
		
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetestteacher")){
		HttpSession session=request.getSession(true);
		String testid=(String)session.getAttribute("testid");
		ArrayList<String> ttb1=new ArrayList<String>();
		ArrayList<TestTeacherBean> ttb2=new ArrayList<TestTeacherBean>();
		ttb1=TestDAO.updateTestTeacher1(testid);
		ttb2=TestDAO.updateTestTeacher2();
		request.setAttribute("selectedteacher", ttb1);
		request.setAttribute("allteacher", ttb2);
		getServletContext().getRequestDispatcher("/jsp/UpdateTest6.jsp").forward(request, response);
	}
	if(request.getParameter("page").equalsIgnoreCase("updatetestteacherfinal")){
		HttpSession session=request.getSession(true);
		ArrayList<TestTeacherBean> ttb2=new ArrayList<TestTeacherBean>();
		String testid=(String)session.getAttribute("testid");
		String[] teachers=request.getParameterValues("teacher");
		for(int i=0;i<teachers.length;i++){
			TestTeacherBean t=new TestTeacherBean();
			t.setTestid(testid);
			t.setTeacherid(teachers[i]);
			ttb2.add(t);
		}
		TestDAO.updateTestTeacherFinal(ttb2);
		response.sendRedirect("/OTTS/jsp/Success.jsp");
	}
	
	if(request.getParameter("page").equals("viewtests")){
		try{
		ArrayList<TestBean> vtb=new ArrayList<TestBean>();
			
		vtb=TestDAO.viewTests();
		
		request.setAttribute("testview", vtb);
		

		getServletContext().getRequestDispatcher("/jsp/viewtest.jsp").forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	if(request.getParameter("page").equalsIgnoreCase("deletetest")){
		try{
		String tid=request.getParameter("id");
		
		TestDAO obj=new TestDAO();
		obj.deleteTest(tid);
		System.out.println("success");
		response.sendRedirect("/OTTS/jsp/Success.jsp");
		return;

	
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	if(request.getParameter("page").equalsIgnoreCase("viewteststudent1")){
		
		//String studentid=request.getParameter("studentid");
		
		HttpSession session=request.getSession(false);
		String studentid=(String)session.getAttribute("userid");
		session.setAttribute("studentid", studentid);
		ArrayList<String> list=TestDAO.getTests(studentid);
		System.out.println(studentid);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		session.setAttribute("testassigned",list );
		
		response.sendRedirect("/OTTS/jsp/StudentViewTest.jsp");
		return;
		

	}
	if(request.getParameter("page").equalsIgnoreCase("starttest")){
		HttpSession session=request.getSession(false);
		if(session!=null){ 
			String testid=request.getParameter("testid");
			int a=TestDAO.getDuration(testid);
			a=a*60;
			session.setAttribute("testid", testid);
			ArrayList<QuestionBean> qlist=TestDAO.getQuestions(testid);
			session.setAttribute("questions", qlist);
			request.setAttribute("question", qlist.get(0));
			request.setAttribute("qno", "1");
			session.setAttribute("duration", a);
			RequestDispatcher rd=request.getRequestDispatcher("jsp/Test.jsp");
			rd.forward(request, response);
			

		}else
		{
			System.out.println("The session has expired");
		}
	}
	
	if(request.getParameter("page").equalsIgnoreCase("save")){
		HttpSession session=request.getSession(false);
		if(session!=null){ 
			String qno=request.getParameter("qno");
			String answer=request.getParameter("answer");
			String remainingtime=request.getParameter("remainingtime");
			
			session.setAttribute("duration", Integer.parseInt(remainingtime));
							
			ArrayList<QuestionBean> qlist=(ArrayList<QuestionBean>)session.getAttribute("questions");
			qlist.get(Integer.parseInt(qno)-1).setAnswer(answer);
			
			request.setAttribute("question", qlist.get(Integer.parseInt(qno)-1));
			request.setAttribute("qno", qno);
			
			RequestDispatcher rd=request.getRequestDispatcher("jsp/Test.jsp");
			rd.forward(request, response);
			

		}else
		{
			System.out.println("The session has expired");
		}
	}
if(request.getParameter("page").equalsIgnoreCase("getquestion")){
		HttpSession session=request.getSession(false);
		if(session!=null){ 
			String qno=request.getParameter("qno");
			String remainingtime=request.getParameter("remainingtime");
			System.out.println(remainingtime);
			session.setAttribute("duration", Integer.parseInt(remainingtime));
											
			ArrayList<QuestionBean> qlist=(ArrayList<QuestionBean>)session.getAttribute("questions");
						
			request.setAttribute("question", qlist.get(Integer.parseInt(qno)-1));
			request.setAttribute("qno", qno);
			RequestDispatcher rd=request.getRequestDispatcher("jsp/Test.jsp");
			rd.forward(request, response);
			

		}else
		{
			System.out.println("The session has expired");
		}
	}
	if(request.getParameter("page").equalsIgnoreCase("endtest")){
		HttpSession session=request.getSession(false);
		if(session!=null){ 
			String testid=(String)session.getAttribute("testid");
			ArrayList<QuestionBean> qlist=(ArrayList<QuestionBean>)session.getAttribute("questions");
			String studentid=(String)session.getAttribute("userid");
			int status=TestDAO.saveTest(qlist,testid,studentid);
			if(status>0)
				response.sendRedirect("jsp/Student.jsp");
			else
				System.out.println("savign failed");

			

		}else{
			System.out.println("The session has expired");
		}
	}
	
	if(request.getParameter("page").equalsIgnoreCase("time")){
		HttpSession session=request.getSession(false);
		String seconds=request.getParameter("remaining");
		session.setAttribute("duration", Integer.parseInt(seconds));
		//System.out.println("ajax sent "+seconds);
		
	
	}
	if(request.getParameter("page").equalsIgnoreCase("viewsingletest")){
		String testid=request.getParameter("testid");
		TestBean t=new TestBean();
		t=TestDAO.viewSingleTest(testid);
		request.setAttribute("viewsingletest1", t);
		getServletContext().getRequestDispatcher("/OTTS/jsp/viewbutton.jsp");
	}
	if(request.getParameter("page").equalsIgnoreCase("viewtestpdf")){
		response.setContentType("application/pdf");
		Document document=new Document();
		ArrayList<TestBean> t=new ArrayList<TestBean>();
		t=TestDAO.viewTests();
		try{
			PdfWriter.getInstance(document,response.getOutputStream());
			document.open();
			document.add(new Paragraph("ALL TEST DETAILS"));
			document.add( Chunk.NEWLINE );
			if(t!=null){for(int i=0;i<t.size();i++){
				document.add(new Paragraph("TestID:"+t.get(i).getTestid()));
				document.add(new Paragraph("TestName:"+t.get(i).getTestName()));
				document.add(new Paragraph("TestCategory:"+t.get(i).getCategory()));
				document.add(new Paragraph("TestDuration:"+t.get(i).getDuration()));
				document.add(new Paragraph("No of Students:"+t.get(i).getNoofStudents()));
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{ document.close();}
		
	}

}
}



		



	

