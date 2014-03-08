package com.ilp.otts.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.bean.LoginBean;
import com.ilp.otts.bean.SchoolBean;
import com.ilp.otts.bean.StudentBean;
import com.ilp.otts.bean.TeacherBean;
import com.ilp.otts.dao.SchoolDao;
import com.ilp.otts.dao.StudentDao;
import com.ilp.otts.dao.TeacherDao;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class ManageStudentServlet
 */
public class ManageStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageStudentServlet() {
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
				RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterStudent.jsp");
				request.setAttribute("captcha", "failed");
				rd.forward(request, response);
				return;
			}
			
			String firstname,middlename,lastname,dob,address,email;
			String  phonenumber,day,month,year,gender,school;
			
			firstname=request.getParameter("firstname");
			middlename=request.getParameter("middlename");
			lastname=request.getParameter("lastname");
			day=request.getParameter("day");
			month=request.getParameter("month");
			year=request.getParameter("year");
			email=request.getParameter("email");
			phonenumber=request.getParameter("phonenumber");
			address=request.getParameter("address");
			gender=request.getParameter("gender");
			school=request.getParameter("school");

			dob=day+"-"+month+"-"+year;
			
			StudentBean studentBean=new StudentBean();
			studentBean.setFirstname(firstname);
			studentBean.setMiddlename(middlename);
			studentBean.setLastname(lastname);
			studentBean.setPhonenumber(phonenumber);
			studentBean.setDob(dob);
			studentBean.setEmail(email);
			studentBean.setAddress(address);
			studentBean.setGender(gender);
			studentBean.setSchool(school.split(" ")[0]);
			
			System.out.println(firstname);
			System.out.println(middlename);
			System.out.println(lastname);
			System.out.println(dob);
			System.out.println(email);
			System.out.println(phonenumber);
			System.out.println(address);
			System.out.println(type);
			System.out.println(gender);
			System.out.println(school);
			
			LoginBean loginBean=StudentDao.insert(studentBean);
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterStudent.jsp");
			request.setAttribute("result", "result");
			request.setAttribute("status",loginBean);
			rd.forward(request, response);

			
			return;

		}
		
		if(type.equals("delete")){
			int executeStatus=0;
			System.out.println("Going to delete id: "+request.getParameter("id"));
			executeStatus=StudentDao.delete(request.getParameter("id"));
			
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/DeleteStudent.jsp");
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
				ArrayList<StudentBean> List=StudentDao.viewStudents();
	
				request.setAttribute("result", List);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewStudent.jsp");
				rd.forward(request, response);
			}

			else if(id.equals("viewall-pdf")){
				System.out.println("in pdf");
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control","private");
				response.setHeader("pragma", "none");
				Document document=new Document();
				try{
					// PdfWriter.getInstance(document,new FileOutputStream("C:\\Documents and Settings\\669986\\Desktop\\generatedPDF\\Mypdf.pdf"));
					ArrayList<StudentBean> List=StudentDao.viewStudents();
					PdfWriter.getInstance(document,response.getOutputStream());
					document.open();  
					document.add(new Paragraph("Student List"));
					document.add( Chunk.NEWLINE );
					for(StudentBean sb: List){
						document.add(new Paragraph("FirstName: "+sb.getFirstname()+
													"\nMiddleName:"+sb.getMiddlename()+
													"\nLastName: "+sb.getLastname()+
													"\nDOB: "+sb.getDob().substring(0,10)+
													"\nGender: "+sb.getGender()+
													"\nEmail: "+sb.getEmail()+
													"\nPhNo: "+sb.getPhonenumber()+
													"\nAddress: "+sb.getAddress()+
													"\nSchool: "+sb.getSchool()));
						document.add( Chunk.NEWLINE );
					}
					document.add(new Paragraph());
					System.out.println("out pdf");
				}catch(Exception e){
					e.printStackTrace();
				}
				finally{ document.close();}
			}
			else{

				ArrayList<StudentBean> List=StudentDao.viewStudentId(id);
				request.setAttribute("result", List);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewStudent.jsp");
				rd.forward(request, response);
			}
			
			return;
		}
		
		if(type.equals("getSchools")){
			PrintWriter out=response.getWriter();
			String id=request.getParameter("id");
			System.out.println("in getschool yes");
			ArrayList<SchoolBean> adminList=SchoolDao.viewSchools();
			for(SchoolBean sb: adminList){
					out.println("<option>"+sb.getSchoolid()+" "+sb.getName()+"</option>");
					System.out.println("<option>"+sb.getSchoolid()+" "+sb.getName()+"</option>");
			}
			
			
			return;
		}
		
		if(type.equals("getupdate")){
			String id=request.getParameter("id");
			ArrayList<StudentBean> adminList=StudentDao.viewStudentId(id);
			request.setAttribute("result", adminList);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateStudent.jsp");
			rd.forward(request, response);
			return;
		}
		
		if(type.equals("update")){
			
			
			String schoolid,email,id;
			String  phonenumber;
			int executeStatus=0;
			

			id=request.getParameter("id");
			email=request.getParameter("email");
			phonenumber=request.getParameter("phonenumber");
			schoolid=request.getParameter("schoolid");

			StudentBean studentBean=new StudentBean();
			studentBean.setId(id);
			studentBean.setPhonenumber(phonenumber);
			studentBean.setEmail(email);
			studentBean.setSchool(schoolid);
			
			
			executeStatus=StudentDao.update(studentBean);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateStudent.jsp");
			
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
