package com.express.servlet;

import java.io.IOException;
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

import com.business.FileOperation;
import com.business.MemberInformation;
import com.business.ProblemLog;
import com.business.ProjectManagement;
import com.business.TaskManager;
import com.business.User;
import com.database.DataProblemLog;

/**
 * Servlet implementation class DealwithProblemlog
 */
@WebServlet("/DealwithProblemlog")
public class DealwithProblemlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealwithProblemlog() {
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
		int problemid=Integer.parseInt(request.getParameter("problemID"));
		String dealmethod=new String(request.getParameter("dealmethod").getBytes("ISO-8859-1"),"utf-8");
		String dealuserID=request.getParameter("dealuserID");
		String createrID=request.getParameter("createrID");
		String logpath=new String(request.getParameter("path").getBytes("ISO-8859-1"),"utf-8");
		String select=request.getParameter("selectstate");
		if(select.equals("L001"))
		{
//			select="发送";
			Date date=new Date();
	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String realDate = format.format(date); 
	        
	        String creater=(String)session.getAttribute("account");
	        User user=MemberInformation.seachUser(Integer.parseInt(createrID));
	        User user1=MemberInformation.seachUser(Integer.parseInt(dealuserID));
	        String data="问题处理时间"+" "+realDate+"\r\n"+"发送人"+" "+dealuserID+" "+user1.getName()+"\r\n"+dealmethod+"\r\n"+"接收人"+" "+createrID+" "+user.getName()+"\r\n";
	        FileOperation.saveAsFileWriter(logpath, data);	        
	        int id[]=new int[2];
	        id=DataProblemLog.searchSenduserid(problemid);
	        
	        DataProblemLog.updateExchange(problemid, id[1], id[0]);
//	        Vector temp=TaskManager.searchAllProblem();
	        for(int i=0;i<TaskManager.searchAllProblem().size();i++)
	        {
	        	ProblemLog problemlog=(ProblemLog)TaskManager.searchAllProblem().get(i);
	        	if(problemlog.getProblemID()==problemid)
	        	{
	        		problemlog.setDealUserID(id[1]);
	        	}
	        }
		}
		if(select.equals("L002"))
		{
			select="结束";
			DataProblemLog.updateState(problemid, 2);
			for(int i=0;i<TaskManager.searchAllProblem().size();i++)
	        {
	        	ProblemLog problemlog=(ProblemLog)TaskManager.searchAllProblem().get(i);
	        	if(problemlog.getProblemID()==problemid)
	        	{
	        		problemlog.setStatus(2);
	        	}
	        }
			
			
		}
		if(select.equals("L003"))
		{
			select="意外终止";
			DataProblemLog.updateState(problemid, 3);
			for(int i=0;i<TaskManager.searchAllProblem().size();i++)
	        {
	        	ProblemLog problemlog=(ProblemLog)TaskManager.searchAllProblem().get(i);
	        	if(problemlog.getProblemID()==problemid)
	        	{
	        		problemlog.setStatus(3);
	        	}
	        }
		}
//		int deal=Integer.parseInt(dealuserID);
//		int create=Integer.parseInt(createrID);
		System.out.println("*****这里是从网页获取的路径******");
		System.out.println(logpath);
		
		
        response.sendRedirect("displayTask.jsp");

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
