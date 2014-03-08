package com.ilp.otts.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;


/**
 * Servlet Filter implementation class CheckSession
 */
public class CheckSession implements Filter {

    /**
     * Default constructor. 
     */
    public CheckSession() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//System.out.println("request called");
		//System.out.println("Check session called");
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        //System.out.println(req.getServletPath());

        HttpSession session=req.getSession(false);
        

        if(session!=null && session.getAttribute("name")!=null){
        	//System.out.println("request forwarded");
        	String path[]=req.getServletPath().split("/");
        	String lastpath=path[path.length-1];
        	//System.out.println("path "+lastpath);
        	chain.doFilter(request, response);
        }
        else{

        	String path[]=req.getServletPath().split("/");
        	String lastpath=path[path.length-1];
        	//System.out.println("path "+lastpath);

        	if(lastpath.equals("Login.jsp")){
        		chain.doFilter(request, response);
        		return;
        	}

        	if(lastpath.equals("Test.jsp")){
        		chain.doFilter(request, response);
        		return;
        	}
        	if(lastpath.equals("Date.jsp")){
        		chain.doFilter(request, response);
        		return;
        	}

        	if(lastpath.matches("^.*css$") || lastpath.matches("^.*js$") || lastpath.matches("^.*jpg$") || lastpath.matches("^.*png$")){
        		chain.doFilter(request, response);
        		return;
        	}

        	if(lastpath.equals("Authenticate")){
        		chain.doFilter(request, response);
        		return;
        	}
        	if(lastpath.equals("ForgotPassword.jsp")){
        		chain.doFilter(request, response);
        		return;
        	}

        	if(lastpath.equals("ChangePassword")){
        		String page=request.getParameter("page");
        		if(page.equals("get-secret-question") || page.equals("check-secret-answer"))        	
        			chain.doFilter(request, response);
        		return;
        	}

        	request.getRequestDispatcher("/WelcomeJSP/Login.jsp").include(request, response);
        	//System.out.println("redirect called");
        	//res.sendRedirect("/OTTS/WelcomeJSP/Login.jsp");
        	response.getWriter().println("<center style=\"color:red\">Please Login First<center>");
        }
	}
		//System.out.println("response called");
		
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
