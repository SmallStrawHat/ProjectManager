package com.express.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.MemberInformation;
import com.business.ProjectManagement;
import com.business.WorktimeInfomation;
import com.database.Myuser;

/**
 * Servlet implementation class LogCheck
 */
@WebServlet("/LogCheck")
public class LogCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogCheck() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void destroy()
    {
    	super.destroy();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    /*	response.setContentType("text/xml");
    	response.setHeader("Cache-Control","no-cache");*/
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	System.out.println(account+":"+password+"4654");
    	
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
        		System.out.println("account:null");
        		//StringBuffer sb = new StringBuffer("<type_name>0</type_name>");
        		response.getWriter().write("0");
        		response.getWriter().close();
    			return ;
        	}
        	if(password.equals(res.getPassword()))
        	{
        		System.out.println("account:pass");
        		response.getWriter().write("1");
        		response.getWriter().close();
    			return ;
        	}
        	else
        	{
        		System.out.println("password:error");
        		response.getWriter().write("0");
        		response.getWriter().flush();
        		response.getWriter().close();
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
		processRequest(request,response);
	}

}
