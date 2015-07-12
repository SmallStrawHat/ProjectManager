package com.express.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.*;

/**
 * Servlet implementation class RoleManager
 */
@WebServlet("/RoleManager")
public class RoleManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("account")==null)
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
		}
    	
    	String operation = request.getParameter("operation");
    	
    	if(operation.equals("deleteUserRole"))
    	{
    		String userRole = new String(request.getParameter("Role").getBytes("ISO-8859-1"),"utf-8");
    		
    		if(RoleInfomation.deleteUserRole(userRole)==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("roleeditor.jsp");
    			return ;
    		}
    		else
    		{
    			//
    			//RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("error.jsp");
    			return ;
    		}
    	}
    	


    	
    	if(operation.equals("addUserRole"))
    	{
    		//String userRole = new String(request.getParameter("userRole").getBytes("ISO-8859-1"),"utf-8");
    		//String Authorization = new String(request.getParameter("Authorization").getBytes("ISO-8859-1"),"utf-8");

    		String userRole = new String(request.getParameter("userRole").getBytes("ISO-8859-1"),"utf-8");
    		String Authorization = new String(request.getParameter("Authorization").getBytes("ISO-8859-1"),"utf-8");
    		
    		if(RoleInfomation.addUserRole(userRole, Authorization)==0)
    		{
    			response.sendRedirect("error.jsp");
    			return ;
    		}
    		else
    		{
    			response.sendRedirect("roleeditor.jsp");
    			return ;
    		}
    	}

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
