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

import com.ilp.otts.bean.SchoolBean;
import com.ilp.otts.bean.StudentBean;
import com.ilp.otts.dao.SchoolDao;
import com.ilp.otts.dao.StudentDao;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class ManageSchoolServlet
 */
public class ManageSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageSchoolServlet() {
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
				RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterSchool.jsp");
				request.setAttribute("captcha", "failed");
				rd.forward(request, response);
				return;
			}	
			
			
			
			SchoolBean sb=new SchoolBean();
			sb.setName(request.getParameter("schoolname"));
			sb.setLocation(request.getParameter("location"));
			sb.setPhone(request.getParameter("phonenumber"));
			sb.setBoard(request.getParameter("board"));
			sb.setEmail(request.getParameter("email"));
			sb.display();
			int insertStatus=SchoolDao.insert(sb);
			System.out.println("Status "+insertStatus);
			
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/RegisterSchool.jsp");
			if(insertStatus!=0)
				request.setAttribute("status", "success");
			else
				request.setAttribute("status", "failure");
			rd.forward(request, response);
		}
		
		if(type.equals("delete")){
			System.out.println("in delete ");
			int deleteStatus=SchoolDao.delete(request.getParameter("id"));
			System.out.println("Status "+deleteStatus);
			
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/DeleteSchool.jsp");
			if(deleteStatus!=0)
				request.setAttribute("status", "success");
			else
				request.setAttribute("status", "failure");
			rd.forward(request, response);
		}
		
		if(type.equals("view")){
			System.out.println("inside view");
			ArrayList<SchoolBean> list=new ArrayList<SchoolBean>();
			String id=request.getParameter("id");
			
			if(id.equals("viewall"))
				list=SchoolDao.viewSchools();	
			
			else if(id.equals("viewall-pdf")){
				System.out.println("in pdf");
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control","private");
				response.setHeader("pragma", "none");
				Document document=new Document();
				try{
					// PdfWriter.getInstance(document,new FileOutputStream("C:\\Documents and Settings\\669986\\Desktop\\generatedPDF\\Mypdf.pdf"));

					list=SchoolDao.viewSchools();
					PdfWriter.getInstance(document,response.getOutputStream());
					document.open();  
					document.add(new Paragraph("Student List"));
					document.add( Chunk.NEWLINE );
					for(SchoolBean sb: list){
						document.add(new Paragraph("Name: "+sb.getName()+
													"\nLocation:"+sb.getLocation()+
													"\nEmail: "+sb.getEmail()+
													"\nPhNo: "+sb.getPhone()+
													"\nBoard: "+sb.getBoard()));

						document.add( Chunk.NEWLINE );
					}
					document.add(new Paragraph());
					System.out.println("out pdf");
				}catch(Exception e){
					e.printStackTrace();
				}
				finally{ document.close();}
			}
			else				
				list=SchoolDao.view(request.getParameter("id"));				
			
			request.setAttribute("result",list);
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/ViewSchool.jsp");
			rd.forward(request, response);
		}
		
		if(type.equals("getExistingDetails")){
			System.out.println("getting update details ");
			request.setAttribute("existingDetails",SchoolDao.view(request.getParameter("id")));
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/UpdateSchool.jsp");
			rd.forward(request, response);
		}
		
		if(type.equals("update")){
			System.out.println("in update ");
			
			SchoolBean sb=new SchoolBean();
			sb.setSchoolid(request.getParameter("schoolid"));
			sb.setLocation(request.getParameter("location"));
			sb.setPhone(request.getParameter("phonenumber"));			
			sb.setEmail(request.getParameter("email"));
			
			int status=SchoolDao.update(sb);
			if(status>0)
				request.setAttribute("status","Success");
			else
				request.setAttribute("status","failure");
			RequestDispatcher rd=request.getRequestDispatcher("CRUDJSP/UpdateSchool.jsp");
			rd.forward(request, response);

		}
	}

}
