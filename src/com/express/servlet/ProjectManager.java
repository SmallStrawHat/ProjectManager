package com.express.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.ProjectManagement;

/**
 * Servlet implementation class ProjectManager
 */
@WebServlet("/ProjectManager")
public class ProjectManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("account")==null || session.getAttribute("account").equals(""))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
		}
    	
    	String function = request.getParameter("function");
    	    	
    	if(function.equals("createProject"))
    	{
    		String pName = new String(request.getParameter("pname").getBytes("ISO-8859-1"),"utf-8");
    		
    		
    		String time1=new String(request.getParameter("starttime"));
    		String[] time3=time1.split("/");
    		
    		StringBuffer tb1=new StringBuffer();
    		String time5=new String();
    		tb1.append(time3[time3.length-1]);
    		for(int i=0;i<time3.length-1;i++)
    		{
    		    tb1.append(time3[i]);
 //   		    System.out.println(tb1);
    		}
    		time5=tb1.toString();
    		
    		
    		String time2=new String(request.getParameter("expectendtime"));
    		String[] time4=time2.split("/");
    		   		
    		StringBuffer tb2=new StringBuffer();
    		String time6=new String();
    		tb2.append(time4[time4.length-1]);
    		for(int i=0;i<time4.length-1;i++)
    		{
    		    tb2.append(time4[i]);
    		}
    		time6=tb2.toString();

    		
    		float budget = Float.parseFloat(request.getParameter("budget"));
    		String manager = request.getParameter("manager");
    		int managerid=-1;
    		if(manager.equals("m1"))
    		{
    			managerid=Integer.parseInt("111220");
    		}
    		else
    		{
    			managerid=Integer.parseInt("111220");
    		}
    		String state= request.getParameter("state");
    		if(state.equals("true"))
    		{
    			state="开启";
    		}
    		else
    		{
    			state="暂停";
    		}
    		int priority=Integer.parseInt(request.getParameter("priority"));
    		float plantime = Float.parseFloat(request.getParameter("plantime"));
    		String summary=new String(request.getParameter("summary").getBytes("ISO-8859-1"),"utf-8");

    		if(ProjectManagement.createProject(pName, time5,time6, budget, managerid, state, priority, plantime, summary)==1)
    		{
    			response.sendRedirect("displayProject.jsp");
    			return ;
    		}
    		else
    		{
    			//指向错误的页面
    			//RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
    			//dispatch.forward(request, response);
    			System.out.println("yayyayayayayayiduafoajdoi叫哦iajgfoa");
    			response.sendRedirect("error.jsp");
    			return ;
    		}
    	}

    }
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
