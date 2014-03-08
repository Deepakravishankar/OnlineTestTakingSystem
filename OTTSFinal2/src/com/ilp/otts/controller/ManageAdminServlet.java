package com.ilp.otts.controller;

import com.ilp.otts.bean.*;
import com.ilp.otts.dao.AdminDao;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ManageAdminServlet
 */
public class ManageAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageAdminServlet() {
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
		Logger log = Logger.getLogger(ManageAdminServlet.class);
		log.info(type);
		System.out.println(type);

		if(type.equals("register")){

			HttpSession session =request.getSession(false);
			String cValue=request.getParameter("cvalue");
			String captchaImageValue=(String)session.getAttribute("CAPTCHA_STRING");
			if(!cValue.equals(captchaImageValue))
			{
				RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterAdmin.jsp");
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

			AdminBean adminBean=new AdminBean();
			adminBean.setFirstname(firstname);
			adminBean.setMiddlename(middlename);
			adminBean.setLastname(lastname);
			adminBean.setPhonenumber(phonenumber);
			adminBean.setDob(dob);
			adminBean.setEmail(email);
			adminBean.setAddress(address);

			System.out.println(firstname);
			System.out.println(middlename);
			System.out.println(lastname);
			System.out.println(dob);
			System.out.println(email);
			System.out.println(phonenumber);
			System.out.println(address);
			System.out.println(type);

			LoginBean loginBean=AdminDao.insert(adminBean);
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterAdmin.jsp");
			request.setAttribute("result", "result");
			request.setAttribute("status",loginBean);
			rd.forward(request, response);


			return;

		}

		if(type.equals("delete")){
			int executeStatus=0;
			System.out.println("Going to delete id: "+request.getParameter("id"));
			executeStatus=AdminDao.delete(request.getParameter("id"));

			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/DeleteAdmin.jsp");
			if(executeStatus>0){

				request.setAttribute("status","success");
				rd.forward(request, response);
			}
			else{

				request.setAttribute("status","failure");
				rd.forward(request, response);
			}
			return;
		}

		if(type.equals("view")){
			String id=request.getParameter("id");
			System.out.println(id+" yes");

			if(id.equals("viewall")){
				ArrayList<AdminBean> adminList=AdminDao.viewAdmins();

				request.setAttribute("result", adminList);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewAdmin.jsp");
				rd.forward(request, response);
			}
			else if(id.equals("viewall-pdf")){
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control","private");
				response.setHeader("pragma", "none");
				Document document=new Document();
				try{
					// PdfWriter.getInstance(document,new FileOutputStream("C:\\Documents and Settings\\669986\\Desktop\\generatedPDF\\Mypdf.pdf"));
					ArrayList<AdminBean> adminList=AdminDao.viewAdmins();
					PdfWriter.getInstance(document,response.getOutputStream());
					document.open();  
					document.add(new Paragraph("Admin List"));
					document.add( Chunk.NEWLINE );
					for(AdminBean ab:adminList){
						document.add(new Paragraph("FirstName: "+ab.getFirstname()+
								"\nMiddleName:"+ab.getMiddlename()+
								"\nLastName: "+ab.getLastname()+
								"\nDOB: "+ab.getDob().substring(0,10)+
								"\nEmail: "+ab.getEmail()+
								"\nPhNo: "+ab.getPhonenumber()+
								"\nAddress: "+ab.getAddress()));
						document.add( Chunk.NEWLINE );
					}
					document.add(new Paragraph());
					
				}catch(Exception e){
					e.printStackTrace();
				}
				finally{ 
					if(document!=null)
						if(document.isOpen())
							document.close();
				}
			}
			else{
				ArrayList<AdminBean> adminList=AdminDao.viewAdminId(id);
				request.setAttribute("result", adminList);
				RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/ViewAdmin.jsp");
				rd.forward(request, response);
			}

			return;
		}

		if(type.equals("getupdate")){
			String id=request.getParameter("id");
			ArrayList<AdminBean> adminList=AdminDao.viewAdminId(id);
			request.setAttribute("result", adminList);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateAdmin.jsp");
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

			AdminBean adminBean=new AdminBean();
			adminBean.setFirstname(firstname);
			adminBean.setMiddlename(middlename);
			adminBean.setLastname(lastname);
			adminBean.setPhonenumber(phonenumber);
			adminBean.setDob(dob);
			adminBean.setEmail(email);
			adminBean.setAddress(address);


			executeStatus=AdminDao.update(adminBean,id);
			RequestDispatcher rd=request.getRequestDispatcher("/CRUDJSP/UpdateAdmin.jsp");

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

			AdminBean adminBean=new AdminBean();
			adminBean.setFirstname(firstname);
			adminBean.setMiddlename(middlename);
			adminBean.setLastname(lastname);
			adminBean.setPhonenumber(phonenumber);
			adminBean.setDob(dob);
			adminBean.setEmail(email);
			adminBean.setAddress(address);


			executeStatus=AdminDao.update(adminBean,id);
			RequestDispatcher rd=request.getRequestDispatcher("/Other/AdminProfilePage.jsp");

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



			executeStatus=AdminDao.updateSecretQuestion(id,question,answer);
			RequestDispatcher rd=request.getRequestDispatcher("/Other/AdminProfilePage.jsp");

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

		if(type.equals("checkemail")){
			String email=request.getParameter("email");
			String result=AdminDao.checkEmail(email);
			
			response.getWriter().write(result);

			return;
		}
	}

}
