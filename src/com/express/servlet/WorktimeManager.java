package com.express.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.*;
import com.database.Worktime;

/**
 * Servlet implementation class RoleManager
 */
@WebServlet("/WorktimeManager")
public class WorktimeManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorktimeManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("account")==null || session.getAttribute("account").equals(""))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
		}
    	
    	String operation = request.getParameter("operation");
    	
    	if(operation.equals("setworktime"))
    	{
    		int workDays = Integer.parseInt(request.getParameter("workdays"));
    		int workDayHours = Integer.parseInt(request.getParameter("workdayhours"));
    		String userLog = new String(request.getParameter("userlog").getBytes("ISO-8859-1"),"utf-8");
    		String userLogPath = new String(request.getParameter("userlogpath").getBytes("ISO-8859-1"),"utf-8");
    		String problemLogPath = new String(request.getParameter("problemlogpath").getBytes("ISO-8859-1"),"utf-8");
    		
    		if(Worktime.updateworktime(workDays, workDayHours,userLog,userLogPath,problemLogPath)==0)
    		{
    			response.sendRedirect("error.jsp");
    			return ;
    		}
    		else
    		{
    			Vector ss=new Vector();
    			for(int i=0;i<WorktimeInfomation.getSystemsettinglist().size();i++)
    			{
    				SystemSetting s=(SystemSetting)WorktimeInfomation.getSystemsettinglist().get(i);
    				Vector role=s.getRolelist();
    				SystemSetting temp=new SystemSetting(workDays, workDayHours,userLog,userLogPath,problemLogPath,role);    				
    				ss.add(temp);			
    			}
    			WorktimeInfomation.setSystemsettinglist(ss);
    			response.sendRedirect("setworktime.jsp");
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
