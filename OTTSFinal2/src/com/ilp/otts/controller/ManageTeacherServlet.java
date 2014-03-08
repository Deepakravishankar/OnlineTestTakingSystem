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
import com.ilp.otts.bean.LoginBean;
import com.ilp.otts.bean.TeacherBean;
import com.ilp.otts.dao.AdminDao;
import com.ilp.otts.dao.TeacherDao;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class ManageTeacherServlet
 */
public class ManageTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageTeacherServlet() {
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
		String type;
		type=request.getParameter("type");


		if(type.equals("register")){
			
			HttpSession session =request.getSession(false);
			String cValue=request.getParameter("cvalue");
			String captchaImageValue=(String)session.getAttribute("CAPTCHA_STRING");
			if(!cValue.equals(captchaImageValue))
			{
				RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterTeacher.jsp");
				request.setAttribute("captcha", "failed");
				rd.forward(request, response);
				return;
			}
			
			String firstname,middlename,lastname,dob,address,email;
			String  phonenumber,day,month,year;
			
			firstname=request.getParameter("firstname");
			middlename=request.getParameter("middlename");
			lastname=request.getParameter("lastname");
			day=request.getParameter("day");
			month=request.getParameter("month");
			year=request.getParameter("year");
			email=request.getParameter("email");
			phonenumber=request.getParameter("phonenumber");
			address=request.getParameter("address");

			dob=day+"-"+month+"-"+year;
			
			TeacherBean teacherBean=new TeacherBean();
			teacherBean.setFirstname(firstname);
			teacherBean.setMiddlename(middlename);
			teacherBean.setLastname(lastname);
			teacherBean.setPhonenumber(phonenumber);
			teacherBean.setDob(dob);
			teacherBean.setEmail(email);
			teacherBean.setAddress(address);
			
			System.out.println(firstname);
			System.out.println(middlename);
			System.out.println(lastname);
			System.out.println(dob);
			System.out.println(email);
			System.out.println(phonenumber);
			System.out.println(address);
			System.out.println(type);
			
			LoginBean loginBean=TeacherDao.insert(teacherBean);
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterTeacher.jsp");
			request.setAttribute("result", "result");
			request.setAttribute("status",loginBean);
			rd.forward(request, response);

			
			return;

		}
		
		if(type.equals("delete")){
			int executeStatus=0;
			System.out.println("Going to delete id: "+request.getParameter("id"));
			executeStatus=TeacherDao.delete(request.getParameter("id"));
			
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/DeleteTeacher.jsp");
			if(executeStatus>0){

				request.setAttribute("status","success");
				rd.forward(request, response);
			}
			else{
				
				request.setAttribute("status","failed");
				rd.forward(request, response);
			}
			return;
		}
		
		if(type.equals("view")){
			String id=request.getParameter("id");
			System.out.println(id+" yes");
			
			if(id.equals("viewall")){
				ArrayList<TeacherBean> adminList=TeacherDao.viewTeachers();
	
				request.setAttribute("result", adminList);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewTeacher.jsp");
				rd.forward(request, response);
			}
			else if(id.equals("viewall-pdf")){
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control","private");
				response.setHeader("pragma", "none");
				Document document=new Document();
				try{
					// PdfWriter.getInstance(document,new FileOutputStream("C:\\Documents and Settings\\669986\\Desktop\\generatedPDF\\Mypdf.pdf"));
					ArrayList<TeacherBean> adminList=TeacherDao.viewTeachers();
					PdfWriter.getInstance(document,response.getOutputStream());
					document.open();  
					document.add(new Paragraph("Teacher List"));
					document.add( Chunk.NEWLINE );
					for(TeacherBean tb:adminList){
						document.add(new Paragraph("FirstName: "+tb.getFirstname()+
													"\nMiddleName:"+tb.getMiddlename()+
													"\nLastName: "+tb.getLastname()+
													"\nDOB: "+tb.getDob().substring(0,10)+
													"\nEmail: "+tb.getEmail()+
													"\nPhNo: "+tb.getPhonenumber()+
													"\nAddress: "+tb.getAddress()));
						document.add( Chunk.NEWLINE );
					}
					document.add(new Paragraph());

				}catch(Exception e){}
				finally{ document.close();}
			}
			else{
				ArrayList<TeacherBean> adminList=TeacherDao.viewTeacherId(id);
				request.setAttribute("result", adminList);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewTeacher.jsp");
				rd.forward(request, response);
			}
			
			return;
		}
		
		if(type.equals("getupdate")){
			String id=request.getParameter("id");
			ArrayList<TeacherBean> adminList=TeacherDao.viewTeacherId(id);
			request.setAttribute("result", adminList);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateTeacher.jsp");
			rd.forward(request, response);
			return;
		}
		
		if(type.equals("update")){
			
			String id=request.getParameter("id");
			String firstname,middlename,lastname,dob,address,email;
			String  phonenumber;
			int executeStatus=0;
			
			firstname=request.getParameter("firstname");
			middlename=request.getParameter("middlename");
			lastname=request.getParameter("lastname");
			dob=request.getParameter("dob");
			email=request.getParameter("email");
			phonenumber=request.getParameter("phonenumber");
			address=request.getParameter("address");

			TeacherBean teacherBean=new TeacherBean();
			teacherBean.setFirstname(firstname);
			teacherBean.setMiddlename(middlename);
			teacherBean.setLastname(lastname);
			teacherBean.setPhonenumber(phonenumber);
			teacherBean.setDob(dob);
			teacherBean.setEmail(email);
			teacherBean.setAddress(address);
			
			
			executeStatus=TeacherDao.update(teacherBean,id);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateTeacher.jsp");
			
			if(executeStatus>0){

				request.setAttribute("status","success");
				rd.forward(request, response);
			}
			else{
				
				request.setAttribute("status","failed");
				rd.forward(request, response);
			}

			return;
		}

		
		if(type.equals("update-profile")){
			
			String id=request.getParameter("id");
			String firstname,middlename,lastname,dob,address,email;
			String  phonenumber;
			int executeStatus=0;
			
			firstname=request.getParameter("firstname");
			middlename=request.getParameter("middlename");
			lastname=request.getParameter("lastname");
			dob=request.getParameter("dob");
			email=request.getParameter("email");
			phonenumber=request.getParameter("phonenumber");
			address=request.getParameter("address");

			TeacherBean teacherBean=new TeacherBean();
			teacherBean.setFirstname(firstname);
			teacherBean.setMiddlename(middlename);
			teacherBean.setLastname(lastname);
			teacherBean.setPhonenumber(phonenumber);
			teacherBean.setDob(dob);
			teacherBean.setEmail(email);
			teacherBean.setAddress(address);
			
			
			executeStatus=TeacherDao.update(teacherBean,id);
			RequestDispatcher rd=request.getRequestDispatcher("/Other/TeacherProfilePage.jsp");
			
			if(executeStatus>0){

				request.setAttribute("status","success");
				rd.forward(request, response);
			}
			else{
				
				request.setAttribute("status","failed");
				rd.forward(request, response);
			}

			return;
		}
		
		if(type.equals("update-profile-secretquestion")){
			System.out.println("in update question");
			String id=(String)request.getSession().getAttribute("userid");
			String question,answer;
			int executeStatus=0;
			
			question=request.getParameter("secretquestion");
			answer=request.getParameter("secretanswer").toLowerCase();

		
			
			executeStatus=TeacherDao.updateSecretQuestion(id,question,answer);
			RequestDispatcher rd=request.getRequestDispatcher("/Other/TeacherProfilePage.jsp");
			
			if(executeStatus>0){

				request.setAttribute("status","success");
				rd.forward(request, response);
			}
			else{
				
				request.setAttribute("status","failed");
				rd.forward(request, response);
			}

			return;
		}
	}


}
