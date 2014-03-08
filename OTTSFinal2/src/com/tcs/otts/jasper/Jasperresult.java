package com.tcs.otts.jasper;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ilp.otts.bean.Bean;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Servlet implementation class pdfServlet
 */
public class Jasperresult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Jasperresult() {
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
		ArrayList<Bean> rb=(ArrayList<Bean>)request.getAttribute("viewresult");
		response.setContentType("application/pdf");
		Iterator<Bean> itr=rb.iterator();
		Document document=new Document();
	    try{
	    	  PdfWriter.getInstance(document,response.getOutputStream());    	
			  document.open();  
			  document.add(new Paragraph("Result"));
			  document.add(Chunk.NEWLINE );
			  
			  while(itr.hasNext())
			 {
				  Bean rb1=itr.next();
				  document.add(new Paragraph("Test ID : "+rb1.getTestid()+",Student Id : "+rb1.getStudentid()+", Score : "+rb1.getScore()+",School Id :"+rb1.getSchoolid()+",Percentage :"+rb1.getAverage()));			 
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
