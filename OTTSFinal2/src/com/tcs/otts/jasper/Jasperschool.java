package com.tcs.otts.jasper;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilp.otts.bean.Bean;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class Jasperschool  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public Jasperschool() {
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
		ArrayList<Bean> rb=(ArrayList<Bean>)request.getAttribute("result");
		response.setContentType("application/pdf");
		Iterator<Bean> itr=rb.iterator();
		Document document=new Document();
		try{
			PdfWriter.getInstance(document,response.getOutputStream());    	
			document.open();  
			document.add(new Paragraph("SchoolAward"));
			document.add(Chunk.NEWLINE );

			while(itr.hasNext())
			{
				Bean rb1=itr.next();
				document.add(new Paragraph("Test ID : "+rb1.getTestid()+", Award Id : "+rb1.getAwardid()+",School Id :"+rb1.getSchoolid()+",Award Name :"+rb1.getAwardname()));			 
				document.add(Chunk.NEWLINE );
			}
			// request.setAttribute("location",location);
			//RequestDispatcher rd=request.getRequestDispatcher("/JSP/Success.jsp");
			//  rd.forward(request,response);
		}
		catch(Exception e)
		{}
		finally
		{ 
			document.close();
		}

	}
}

