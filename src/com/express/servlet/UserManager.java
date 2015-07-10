package com.express.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.Myuser;
import com.business.*;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
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
    	
    	String function = request.getParameter("function");
    	
    	if(function.equals("deleteUser"))
    	{
    		String userID = request.getParameter("accountID");
    		if(MemberInformation.deleteUser(Integer.parseInt(userID))==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("userManager.jsp");
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
    	
    	if(function.equals("purseUser"))
    	{
    		String userID = request.getParameter("accountID");
    		if(MemberInformation.pauseUser(Integer.parseInt(userID))==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("userManager.jsp");
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
    	
    	if(function.equals("startUser"))
    	{
    		String userID = request.getParameter("accountID");
    		if(MemberInformation.startUser(Integer.parseInt(userID))==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("userManager.jsp");
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
    	
    	if(function.equals("addUser"))
    	{
    		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
    		String selectRole = request.getParameter("selectRole");
    		if(selectRole.equals("OP"))
    		{
    			selectRole="项目经理";
    		}
    		else
    		{
    			selectRole="项目成员";
    		}
    		String userPassword = request.getParameter("userPassword");
    		String selectState = request.getParameter("selectState");
    		int state=-1;
    		if(selectState.equals("AC"))
    		{
    			state = 1;
    		}
    		else
    		{
    			state = 0;
    		}
    		String userPhone = request.getParameter("userPhone");
    		String userEmail = request.getParameter("userEmail");
    		String userSummary = new String(request.getParameter("userSummary").getBytes("ISO-8859-1"),"utf-8");

    		
    		/*
    		 * 如果JS来判断，这里就不需要添加代码
    		if(userPhone==null || userEmail==null || userPassword==null || selectState==null)
    		{
    			RequestDispatcher dispatch = request.getRequestDispatcher("newuser.jsp");
    			dispatch.forward(request, response);
    			return ;
    		}*/
    		if(MemberInformation.addUser(userName, userPhone, userEmail, selectRole, userPassword, state, userSummary)==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("userManager.jsp");
    			return ;
    		}
    		else
    		{
    			//指向错误的页面
    			//RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
    			//dispatch.forward(request, response);
    			response.sendRedirect("error.jsp");
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
