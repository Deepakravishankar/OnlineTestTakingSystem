package com.ilp.otts.filter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet Filter implementation class CheckAccess
 */
public class CheckAccess implements Filter {
	static HashMap<String,Integer> accessList=new HashMap<String,Integer>();
	int count;
	/**
	 * Default constructor. 
	 */
	public CheckAccess() {
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

		// pass the request along the filter chain
		/*HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session =req.getSession();

		String path[]=req.getServletPath().split("/");
		String lastpath=path[path.length-1];

		if(lastpath.equals("Login.jsp")){
			chain.doFilter(request, response);
			return;
		}

		if(lastpath.equals("AccessDenied.jsp")){
			chain.doFilter(request, response);
			return;
		}
		if(lastpath.equals("Error.jsp")){
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
				chain.doFilter(request, response);
			return;
		}
		System.out.println(lastpath);
		try{
			int pageLevel=accessList.get(lastpath);
			int userLevel=Integer.parseInt((String)session.getAttribute("accesslevel"));

			if(userLevel<=pageLevel){
				System.out.println("level allowed");
				chain.doFilter(request, response);
			}
			else{
				System.out.println("level disallowed");
				res.sendRedirect("/OTTS/AccessDenied.jsp");
			}
		}catch(NullPointerException ex){
			System.out.println("level allowed cause null pointer");
			chain.doFilter(request, response);
		}*/
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		try {

			//URL textURL = getClass().getResource("AccessList.xml"); 
			//InputStream is = textURL.openStream();
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse("AccessList.xml");


			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("page");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					accessList.put(eElement.getElementsByTagName("url").item(0).getTextContent(),Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
