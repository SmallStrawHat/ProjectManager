package com.express.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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
		if(session == null || session.getAttribute("account")==null || session.getAttribute("account").equals(""))
		{
			RequestDispatcher dispatch = request.getRequestDispatcher("signin.jsp");
			dispatch.forward(request, response);
			return ;
		}
    	
    	String function = request.getParameter("functionMy");
    	
    	if(function.equals("deleteUser"))
    	{
    		String userID = request.getParameter("accountID");
    		String operation = (String)session.getAttribute("account");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String dateEdit = df.format(new Date());// new Date()为获取当前系统时间
			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
			User editUser = MemberInformation.seachUser(Integer.parseInt(userID));
			String operationName = operationUser.getUserID()+" "+operationUser.getName();
			String path = editUser.getUserLogpath();
			
    		if(MemberInformation.deleteUser(Integer.parseInt(userID))==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			
    			FileOperation.saveAsFileWriter(path,"用户删除时间 "+dateEdit+"\r\n");
    			//System.out.println("创建时间 "+dateCreate);
    			FileOperation.saveAsFileWriter(path,"操作人 "+operationName+"\r\n");
    			
    			
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
    			String operation = (String)session.getAttribute("account");
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    			String dateEdit = df.format(new Date());// new Date()为获取当前系统时间
    			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
    			User editUser = MemberInformation.seachUser(Integer.parseInt(userID));
    			
    			String operationName = operationUser.getUserID()+" "+operationUser.getName();
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"状态暂停时间 "+dateEdit+"\r\n");
    			//System.out.println("创建时间 "+dateCreate);
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"状态编辑人 "+operationName+"\r\n");
    			
    			
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
    			String operation = (String)session.getAttribute("account");
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    			String dateEdit = df.format(new Date());// new Date()为获取当前系统时间
    			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
    			User editUser = MemberInformation.seachUser(Integer.parseInt(userID));
    			
    			String operationName = operationUser.getUserID()+" "+operationUser.getName();
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"状态启动时间 "+dateEdit+"\r\n");
    			//System.out.println("创建时间 "+dateCreate);
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"状态编辑人 "+operationName+"\r\n");
    			
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
    	
    	
    	if(function.equals("editUser"))
    	{
    		int userID = Integer.parseInt(request.getParameter("userID"));
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

    		if(MemberInformation.editUser(userID,userName, userPhone, userEmail, selectRole, userPassword, state, userSummary)==1)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			String operation = (String)session.getAttribute("account");
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    			String dateEdit = df.format(new Date());// new Date()为获取当前系统时间
    			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
    			User editUser = MemberInformation.seachUser(userID);
    			
    			String operationName = operationUser.getUserID()+" "+operationUser.getName();
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"信息编辑时间 "+dateEdit+"\r\n");
    			//System.out.println("创建时间 "+dateCreate);
    			FileOperation.saveAsFileWriter(editUser.getUserLogpath(),"信息编辑人 "+operationName+"\r\n");
    			
    			
    			response.sendRedirect("editUserDetail.jsp?actionID="+userID);
    			return ;
    		}
    		else
    		{
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
    		int userReturnID = MemberInformation.addUser(userName, userPhone, userEmail, selectRole, userPassword, state, userSummary);
    		if(userReturnID !=0)
    		{
    			//RequestDispatcher dispatch = request.getRequestDispatcher("userManager.jsp");
    			//dispatch.forward(request, response);
    			String operation = (String)session.getAttribute("account");
    			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    			String dateCreate = df.format(new Date());// new Date()为获取当前系统时间
    			User operationUser = MemberInformation.seachUser(Integer.parseInt(operation));
    			User createUser = MemberInformation.seachUser(userReturnID);
    			
    			String operationName = operationUser.getUserID()+" "+operationUser.getName();
    			FileOperation.saveAsFileWriter(createUser.getUserLogpath(),"创建时间 "+dateCreate+"\r\n");
    			//System.out.println("创建时间 "+dateCreate);
    			FileOperation.saveAsFileWriter(createUser.getUserLogpath(),"操作人 "+operationName+"\r\n");
    			//System.out.println("txt sucsee");
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
