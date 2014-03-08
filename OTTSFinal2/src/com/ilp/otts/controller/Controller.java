package com.ilp.otts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ilp.otts.bean.Bean;
import com.ilp.otts.dao.DAO;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
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
		try
		{
			if(request.getParameter("page").equalsIgnoreCase("view"))
			{
				String studentid =request.getParameter("name");
				Bean a=new Bean();
				a.setStudentid(studentid);
				Bean beanobj=DAO.view(a);
				request.setAttribute("obj",beanobj);
				System.out.println("in controller");
				if(DAO.i==1){
					getServletContext().getRequestDispatcher("/awardjsp/viewawardalloted.jsp").forward(request,response);}
				else{
					getServletContext().getRequestDispatcher("/awardjsp/studentawardallocation.jsp").include(request,response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		finally
		{
		}
		try{

			if(request.getParameter("page").equalsIgnoreCase("viewall"))
			{
				ArrayList<Bean> list= new ArrayList<Bean>();
				System.out.println("entry");
				list = DAO.ViewAll();
				request.setAttribute("vlist", list);
				System.out.println("dispatch");
				getServletContext().getRequestDispatcher("/awardjsp/viewallstudent.jsp").forward(request,response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		finally{
		}
		try{
			if(request.getParameter("page").equalsIgnoreCase("eligible"))
			{

				System.out.println("entry");
				ArrayList<Bean> list = DAO.EligibleAll();
				request.setAttribute("vlist", list);
				System.out.println("dispatch");
				getServletContext().getRequestDispatcher("/awardjsp/eligibleall.jsp").forward(request,response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		finally{
		}
		if(request.getParameter("page").equalsIgnoreCase("listaward"))
		{
			System.out.println("inside list awards");
			try {
				request.setAttribute("awardlist",DAO.getAwards());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd=request.getRequestDispatcher("/awardjsp/AllocateAward.jsp");
			rd.forward(request, response);
		}

		if(request.getParameter("page").equals("get-eligible-students")){
			String awid=request.getParameter("awardid");
			System.out.println(awid);			


			try {
				request.setAttribute("eligible-students",DAO.getEligible(awid));
				request.setAttribute("awardlist",DAO.getAwards());
				request.setAttribute("awardname",DAO.getAwardName(awid));
				request.setAttribute("awardid",awid);
				RequestDispatcher rd=request.getRequestDispatcher("awardjsp/AllocateAward.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		if(request.getParameter("page").equals("allocate-awards")){
			try{
				String id[]=request.getParameterValues("id");
				int status=1;
				for(int i=0;i<id.length;i++)
					status*=DAO.allocateAward(id[i].split(","));

				getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp").forward(request, response);


			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{	
			}
		}

		if(request.getParameter("page").equals("display")){
			try {
				ArrayList<Bean> ar=DAO.viewStudents();
				if (null != ar && !ar.isEmpty()) {

					request.setAttribute("viewschools", ar);
					//request.setAttribute("testid",testid);


					RequestDispatcher rdp= request.getRequestDispatcher("/awardjsp/schoolsview.jsp");
					rdp.forward(request, response);
				}
				else
				{
					RequestDispatcher rdp= request.getRequestDispatcher("/awardjsp/schoolsnoview.jsp");
					rdp.forward(request, response);
				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
			}

		}

		if(request.getParameter("page").equals("allocateschools")){
			System.out.println("in controller school award");
			try{
				String id[]=request.getParameterValues("id");

				int status=1;
				for(int i=0;i<id.length;i++)
					status*=DAO.allocateSchools(id[i].split(","));
				RequestDispatcher rd=request.getRequestDispatcher("/awardjsp/awardsuccess.jsp");
				rd.forward(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{	
			}
		}


		if (request.getParameter("page").equalsIgnoreCase("Deleteaward")){
			try{
				System.out.println("in controller");
				String studentid= request.getParameter("studentid");
				Bean a= new Bean();
				System.out.println("in controller");
				a.setStudentid(studentid);
				DAO b = new DAO();
				b.deleteAward(a);
				if(DAO.i==1){
					getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp")
					.forward(request, response);}
				else if(DAO.i==2){
					getServletContext().getRequestDispatcher("/awardjsp/deleteaward.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:30%;top:45%;color:red> <H4>Invalid ID</H4></div>");
				}

			}	
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{	
			}}

		if (request.getParameter("page").equalsIgnoreCase("create"))
		{
			try {
				String awardid= request.getParameter("awardid");
				String awardtype= request.getParameter("awardtype");
				String awardname= request.getParameter("awardname");
				String awardamount= request.getParameter("awardamount");
				String awardreason= request.getParameter("awardreason");
				String awarddetails= request.getParameter("awarddetails");
				String low= request.getParameter("low");
				String high= request.getParameter("high");

				Bean a = new Bean();
				a.setAwardid(awardid);
				a.setAwardtype(awardtype);
				a.setAwardname(awardname);
				a.setAwardamount(awardamount);
				a.setAwardreason(awardreason);
				a.setAwarddetails(awarddetails);
				a.setLow(low);
				a.setHigh(high);


				Bean obj1 = DAO.create(a);

				getServletContext()
				.getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp").forward(
						request, response);
			}	
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{
			}}


		if (request.getParameter("page").equalsIgnoreCase("delete")){
			try{
				String awardid= request.getParameter("awardid");
				String awardreason= request.getParameter("awardreason");
				Bean a= new Bean();

				a.setAwardid(awardid);
				DAO b = new DAO();
				DAO.i = DAO.deleteDetails(a);
				if(DAO.i==1){
					getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp")
					.forward(request, response);}
				else{
					getServletContext().getRequestDispatcher("/awardjsp/AwardDeletion.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}

			}	
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{
			}}

		if (request.getParameter("page").equalsIgnoreCase("update"))

		{
			try{
				System.out.println("ok");

				String awardid= request.getParameter("awardid");
				String awardtype= request.getParameter("awardtype");
				String awardname= request.getParameter("awardname");
				String awardamount= request.getParameter("awardamount");
				String awardreason= request.getParameter("awardreason");
				String awarddetails= request.getParameter("awarddetails");
				Bean a = new Bean();
				a.setAwardid(awardid);
				a.setAwardtype(awardtype);
				a.setAwardname(awardname);
				a.setAwardamount(awardamount);
				a.setAwardreason(awardreason);
				a.setAwarddetails(awarddetails);
				System.out.println("in update");

				DAO.update(a);		
				getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp").forward(request, response);

			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{
			}}

		if (request.getParameter("page").equalsIgnoreCase("viewaward")) {

			ArrayList<Bean> list1= new ArrayList<Bean>();



			System.out.println("inside view update controller");
			try {
				list1 = DAO.viewAward();

				if (null != list1 && !list1.isEmpty()) {

					request.setAttribute("viewresult", list1);


					RequestDispatcher rdp= request.getRequestDispatcher("/awardjsp/updatecomplete1.jsp");
					rdp.forward(request, response);

				}

				else{
					getServletContext().getRequestDispatcher("/awardjsp/createaward.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();

					out.println("<div style=position:absolute;right:30%;top:22%;color:red> <H4>There are no Awards to display..Create an award here</H4></div>");

				}




			}
			catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		if(		request.getParameter("page").equalsIgnoreCase("viewone"))
		{
			try
			{
				String awardid =request.getParameter("awardid");

				Bean vw=new Bean();
				vw.setAwardid(awardid);
				ArrayList<Bean> bean1=DAO.viewone(vw);
				request.setAttribute("awardlist",bean1);
				if(DAO.i==1){
					System.out.println(DAO.i);
					getServletContext().getRequestDispatcher("/awardjsp/awardsuccess.jsp").forward(request, response);}
				else{System.out.println(DAO.i);
				PrintWriter out=response.getWriter();
				getServletContext().getRequestDispatcher("/awardjsp/update.jsp").include(request, response);
				out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}	
			}

			catch(Exception e)
			{
			}
			finally
			{
			}
		}
		if(request.getParameter("page").equalsIgnoreCase("GenerateResult"))
		{
			try {
				String testid= request.getParameter("testid");
				Bean rb=new Bean();
				rb.setTestid(testid);
				System.out.println(rb.getTestid());
				ArrayList<Bean> r=DAO.generateStudents(rb);

				if (null != r && !r.isEmpty()) {

					request.setAttribute("awardList", r);
					request.setAttribute("testid",testid);
				}
				if(DAO.i==1){
					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/complete.jsp");
					rdp.forward(request, response);
				}
				else{
					getServletContext().getRequestDispatcher("/resultgeneration/generateresult.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}

			} catch (Exception e) {
			} finally {
			}
		}
		if(request.getParameter("page").equalsIgnoreCase("viewresult"))
		{
			System.out.println("inside viewresult controller");
			try {
				ArrayList<Bean> ar=new ArrayList<Bean>();
				ar=DAO.viewResult();

				if (null != ar && !ar.isEmpty()) {

					request.setAttribute("viewresult", ar);}

				if(DAO.i==1){
					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/viewcomplete.jsp");
					rdp.forward(request, response);
				}

				else {
					System.out.println("error");
					getServletContext().getRequestDispatcher("/resultgeneration/viewresult.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>There are no results to display</H4></div>");
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(request.getParameter("page").equalsIgnoreCase("pdfgenerate"))
		{
			System.out.println("inside viewresult controller");
			try {
				ArrayList<Bean> ar=new ArrayList<Bean>();
				ar=DAO.viewResult();

				if (null != ar && !ar.isEmpty()) {

					request.setAttribute("viewresult", ar);}

				if(DAO.i==1){
					RequestDispatcher rdp= request.getRequestDispatcher("/Jasperresult");
					rdp.forward(request, response);
				}

				else {
					System.out.println("error");
					getServletContext().getRequestDispatcher("/resultgeneration/viewresult.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>There are no results to display</H4></div>");
				}

			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*if(request.getParameter("page").equalsIgnoreCase("pdfgenerate")){
			try{
				System.out.println("in viewallocatedaward controller");
				ArrayList<Bean> ar=new ArrayList<Bean>();
				ar=DAO.viewResult();
				request.setAttribute("viewresult",ar);
				RequestDispatcher rd=request.getRequestDispatcher("/Jasperresult");
				rd.forward(request,response);
				//	getServletContext().getRequestDispatcher("/awardjsp/viewawardsuccess.jsp").forward(request,response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}*/
		if(request.getParameter("page").equalsIgnoreCase("updateresult"))
		{
			try
			{

				String testid =request.getParameter("testid");
				String studentid =request.getParameter("studentid");
				System.out.println(testid);
				System.out.println(studentid);
				Bean vw=new Bean();
				vw.setTestid(testid);
				vw.setStudentid(studentid);

				ArrayList<Bean> ar=DAO.viewoneResult(vw);
				if (null != ar ) {

					request.setAttribute("updateresult", ar);}


				if(DAO.i==1)
				{
					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/updatecomplete.jsp");
					rdp.forward(request, response);}

				else if(DAO.i==2)
				{
					getServletContext().getRequestDispatcher("/resultgeneration/updateresult.jsp")
					.include(request, response);

					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}	

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
			}

		}
		if(request.getParameter("page").equalsIgnoreCase("StudentResult"))
		{
			String studentid=request.getParameter("studentid");
			String testid=request.getParameter("testid");
			String schoolid=request.getParameter("schoolid");
			Bean rb=new Bean();
			rb.setStudentid(studentid);
			rb.setTestid(testid);
			rb.setSchoolid(schoolid);
			try {
				int score=DAO.generateResult(rb);
				//request.setAttribute("", score);
				request.setAttribute("score", score);
				request.setAttribute("testid",testid);
				request.setAttribute("studentid",studentid);
				request.setAttribute("schoolid",schoolid);
				if(DAO.i==10)
				{
					System.out.println("result has already been generated for this student");
					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/alreadyresult.jsp");
					rdp.forward(request, response);
				}

				if(DAO.i==1){
					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/successresult.jsp");
					rdp.forward(request, response);
				}
				else{
					getServletContext().getRequestDispatcher("/resultgeneration/generateresult.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}


			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		if(request.getParameter("page").equalsIgnoreCase("deleteresult"))
		{

			try {
				String testid=request.getParameter("testid");
				String studentid=request.getParameter("studentid");
				Bean rb=new Bean();
				rb.setTestid(testid);
				rb.setStudentid(studentid);

				Bean rb1=DAO.deleteDetail(rb);
				if(DAO.i==1){

					RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/successresult.jsp");
					rdp.forward(request, response);
				}
				else{
					getServletContext().getRequestDispatcher("/resultgeneration/deleteresult.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:35%;top:18%;color:red> <H4>Invalid ID</H4></div>");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		if(request.getParameter("page").equalsIgnoreCase("StoreUpdate"))
		{
			String testid=request.getParameter("testid");
			String studentid=request.getParameter("studentid");
			String score=request.getParameter("score");
			String avg=request.getParameter("avg");
			Bean rb=new Bean();
			rb.setTestid(testid);
			rb.setStudentid(studentid);	
			rb.setScore(score);
			rb.setAverage(avg);
			try {
				Bean rb1=DAO.storeUpdate(rb);
				RequestDispatcher rdp= request.getRequestDispatcher("/resultgeneration/successresult.jsp");
				rdp.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("page").equalsIgnoreCase("viewallocatedaward")){
			try{
				System.out.println("in viewallocatedaward controller");
				ArrayList<Bean> list=new ArrayList<Bean>();
				list=DAO.viewallocatedaward();
				request.setAttribute("result",list);

				getServletContext().getRequestDispatcher("/awardjsp/viewawardsuccess.jsp").forward(request,response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(request.getParameter("page").equalsIgnoreCase("generatePDF")){
			try{
				System.out.println("in viewallocatedaward controller");
				ArrayList<Bean> list=new ArrayList<Bean>();
				list=DAO.viewallocatedaward();
				request.setAttribute("result",list);
				RequestDispatcher rd=request.getRequestDispatcher("/Jasper");
				rd.forward(request,response);
				//	getServletContext().getRequestDispatcher("/awardjsp/viewawardsuccess.jsp").forward(request,response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		if(request.getParameter("page").equalsIgnoreCase("viewschoolaward")){
			try{
				System.out.println("in viewallocatedschoolaward controller");
				ArrayList<Bean> list=new ArrayList<Bean>();
				list=DAO.viewallocateschoolaward();
				request.setAttribute("result",list);
				getServletContext().getRequestDispatcher("/awardjsp/viewschoolawardsuccess.jsp").forward(request,response);
			}catch(Exception e) {
				e.printStackTrace();
			}

		}
		if(request.getParameter("page").equalsIgnoreCase("pdfgen")){
				try{
					System.out.println("in viewallocatedaward controller");
					ArrayList<Bean> list=new ArrayList<Bean>();
					list=DAO.viewallocateschoolaward();
					request.setAttribute("result",list);
					RequestDispatcher rd=request.getRequestDispatcher("/Jasperschool");
					rd.forward(request,response);
					//	getServletContext().getRequestDispatcher("/awardjsp/viewawardsuccess.jsp").forward(request,response);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}




		if (request.getParameter("page").equalsIgnoreCase("deleteschoolaward")){
			try{
				String schoolid= request.getParameter("schoolid");

				Bean a= new Bean();

				a.setSchoolid(schoolid);
				DAO b = new DAO();
				b.deleteschoolaward(a);
				if(DAO.i==1){
					getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp")
					.forward(request, response);
				}
				else if(DAO.i==2){
					getServletContext().getRequestDispatcher("/awardjsp/deleteschoolaward.jsp")
					.include(request, response);
					PrintWriter out=response.getWriter();
					out.println("<div style=position:absolute;right:43%;top:40%;color:red> <H4>Invalid ID</H4></div>");
				}



			}	
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			finally
			{
			}

		} 
		if (request.getParameter("page").equalsIgnoreCase("back")){
			getServletContext().getRequestDispatcher("/awardjsp/search.jsp")
			.forward(request, response);

		}
		if (request.getParameter("page").equalsIgnoreCase("back1")){
			getServletContext().getRequestDispatcher("/awardjsp/schoollinks.jsp")
			.forward(request, response);

		}
		if (request.getParameter("page").equalsIgnoreCase("back2")){
			getServletContext().getRequestDispatcher("/resultgeneration/teacher.jsp")
			.forward(request, response);

		}
		if (request.getParameter("page").equalsIgnoreCase("viewupdate")){
			  try {
			System.out.println("In controller");
			String awardid=request.getParameter("awardid");
			System.out.println(awardid);
			Bean obj=new Bean();
			obj.setAwardid(awardid);
			ArrayList<Bean> a=new ArrayList<Bean>();
		    a=DAO.viewupdate(obj);
		    request.setAttribute("result",a);
		    if(DAO.i==1){
		    	getServletContext().getRequestDispatcher("/awardjsp/allocatedupdate.jsp")
				.forward(request, response);
		    }
		    else{
		    	 getServletContext().getRequestDispatcher("/awardjsp/updateallocatedaward.jsp")
					.include(request, response);
				 PrintWriter out=response.getWriter();
				 out.println("<div style=position:absolute;right:43%;top:80%;color:red> <H4>Invalid ID</H4></div>");
		    }
			  } catch (Exception e) {
			e.printStackTrace();
		} }
			  if (request.getParameter("page").equalsIgnoreCase("updatestudent")){	
				  try {
				  System.out.println("In UPDATE controller");
				  String testid=request.getParameter("testid");
				  String studentid=request.getParameter("studentid");
				  String awardid=request.getParameter("awardid");
				  String schoolid=request.getParameter("schoolid");
				  String awardname=request.getParameter("awardname");
				  Bean a=new Bean();
				  a.setTestid(testid);
				  a.setStudentid(studentid);
				  a.setAwardid(awardid);
				  a.setSchoolid(schoolid);
				  a.setAwardname(awardname);
				  System.out.println(testid);
				  DAO.updatestudentaward(a);
				  getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp")
					.forward(request, response);
				  
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			 
			  }
			  if (request.getParameter("page").equalsIgnoreCase("viewschoolupdate")){
				  try {
						System.out.println("In controller");
						String awardid=request.getParameter("awardid");
						System.out.println(awardid);
						Bean obj=new Bean();
						obj.setAwardid(awardid);
						ArrayList<Bean> a=new ArrayList<Bean>();
					    a=DAO.viewschoolupdate(obj);
					    request.setAttribute("result",a);
					    if(DAO.i==1){
					    	getServletContext().getRequestDispatcher("/awardjsp/allocatedschoolupdate.jsp")
							.forward(request, response);
					    }
					    else{
					    	 getServletContext().getRequestDispatcher("/awardjsp/updateschoolaward.jsp")
								.include(request, response);
							 PrintWriter out=response.getWriter();
							 out.println("<div style=position:absolute;right:43%;top:50%;color:red> <H4>Invalid ID</H4></div>");
					    }
						  } catch (Exception e) {
						e.printStackTrace();
					}
				  
			  }
			  if (request.getParameter("page").equalsIgnoreCase("updateschool")){
				  try {
					  System.out.println("In UPDATE controller");
					  String testid=request.getParameter("testid");
					  String awardid=request.getParameter("awardid");
					  String schoolid=request.getParameter("schoolid");
					  String awardname=request.getParameter("awardname");
					  Bean a=new Bean();
					  a.setTestid(testid);
					  a.setAwardid(awardid);
					  a.setSchoolid(schoolid);
					  a.setAwardname(awardname);
					  System.out.println(testid);
					  DAO.updateschoolaward(a);
					  getServletContext().getRequestDispatcher("/awardjsp/awardupdatedsuccess.jsp")
						.forward(request, response);
					  
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				  
			  }
		}
		
		}



