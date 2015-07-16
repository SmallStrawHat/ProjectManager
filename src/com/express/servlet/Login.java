package com.express.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.*;
import com.business.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(false);
		if(session == null )
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
		}
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	System.out.println(account+":"+password);
    	
    	if(account==null|| password==null )
    	{
    		RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
    	}
    	
    	if(account.equals("")|| password.equals("") )
    	{
    		RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
    	}
    	else
    	{
        	Myuser res = Myuser.search(Integer.parseInt(account));
        	if(res == null)
        	{
        		RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
    			dispatch.forward(request, response);
    			return ;
        	}
        	if(password.equals(res.getPassword()))
        	{
        		session.setAttribute("account", account);
        		
        		/*初始化数据到内存*/
        		WorktimeInfomation.init();
        		MemberInformation.init();
        		ProjectManagement.init();
        		
        		String operation = (String)session.getAttribute("account");
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    			String datelogin = df.format(new Date());// new Date()为获取当前系统时间
    			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
    			
    			FileOperation.saveAsFileWriter(operationUser.getUserLogpath(),"登录时间 "+datelogin+"\r\n");
        		
        		
        		response.sendRedirect("index.jsp");
    			return ;
        	}
        	else
        	{
        		RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
    			dispatch.forward(request, response);
    			return ;
        	}
    	}
    	
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		processRequest(request,response);
	}

}
