package com.ilp.otts.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class for Servlet: GRSCaptchaServlet
 *
 */
 public class GRSCaptchaServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GRSCaptchaServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("************ Calling Captcha Servlet *********");
		ServletOutputStream outputStream=null;
		 BufferedImage captchaImg=null;
		 try {
			GRSCaptchaUtil grsCaptchaUtil = GRSCaptchaUtil.getInstance();
			String captchaStr = grsCaptchaUtil.getCaptchaString(new Integer(5));
			captchaImg = grsCaptchaUtil.getCaptchaImage(captchaStr, 200,60);
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			outputStream = response.getOutputStream();
			if (outputStream != null && captchaImg != null) {
				ImageIO.write(captchaImg, "jpeg", outputStream);
				System.out.println("************ Writing the Captcha Image *********");
			}
			HttpSession session=(HttpSession)request.getSession(false);
			System.out.println(captchaStr);
			session.setAttribute("CAPTCHA_STRING", captchaStr);
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (outputStream != null && captchaImg != null) {
					captchaImg.flush();
					outputStream.flush();
					outputStream.close();

				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
 }
