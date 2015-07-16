package com.express.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.business.*;

/**
 * Servlet implementation class TaskManager
 */
@WebServlet("/TaskManagerExpre")
public class TaskManagerExpre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskManagerExpre() {
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
    	
    	
    	if(function.equals("createProblemLog"))
    	{
    		int status = 1;
    		String problemDescreption = new String(request.getParameter("problemDescreption").getBytes("ISO-8859-1"),"utf-8");
    		int taskID = Integer.parseInt(request.getParameter("taskID"));
    		int dealUserID = Integer.parseInt(request.getParameter("dealUserID"));
    		int createUserID = Integer.parseInt(request.getParameter("createUserID"));
    		String createTime = request.getParameter("createTime");

    		String[] tempTime = createTime.split("/");
    		createTime = tempTime[0]+" "+tempTime[1];
    
    		

    		int problemID=com.business.TaskManager.createProblemLog(taskID,createTime,createUserID,dealUserID,
    				status,problemDescreption);

    		if(problemID!=0)
    		{
    			//写入任务日志
    			String  logpath=new String();
        		for(int i=0;i<WorktimeInfomation.systemsettinglist.size();i++)
        		{
        			SystemSetting temp=(SystemSetting)WorktimeInfomation.systemsettinglist.get(i);    
        			logpath=temp.getProblemlogpath();
        		}
        		String file=Integer.toString(taskID);			
        		logpath=logpath+file+"_"+"3"+".txt";
//        		logpath=logpath.replaceAll("\\\\", "\\\\\\\\");
        		System.out.println(logpath);
    			
    			Date date=new Date();
    	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        String realDate = format.format(date); 
    	        /*String[] temp = realDate.split(" ");
    	        realDate = temp[0]+"/"+temp[1];*/

    	        String creater=(String)session.getAttribute("account");
    	        User user=MemberInformation.seachUser(Integer.parseInt(creater));
    	        
//    	        Project project=ProjectManagement.searchProject(projectID);
    	        Task task=TaskManager.searchTask(taskID);
    	        
    	        String data="日志创建时间"+" "+realDate+"\r\n"+"创建人"+" "+session.getAttribute("account")+" "+user.getName()+"\r\n"+"所属项目"+" "+taskID+" "+task.getTaskName()+"\r\n";   	          	        
    	        FileOperation.saveAsFileWriter(logpath, data);
    	        
    	        
    	        User user1=MemberInformation.seachUser(dealUserID);
    	        String data1="发送方"+" "+session.getAttribute("account")+" "+user.getName()+"\r\n"+"接收方"+" "+dealUserID+" "+user1.getName()+"\r\n";
    	        FileOperation.saveAsFileWriter(logpath, data1);
    	        
    			response.sendRedirect("displayTask.jsp");
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
    	
    	if(function.equals("createTask"))
    	{
    		String taskName = new String(request.getParameter("taskName").getBytes("ISO-8859-1"),"utf-8");
    		int projectID = Integer.parseInt(request.getParameter("projectSelect"));
    		
    		String state = request.getParameter("taskStateSelect");
    		
    		if(state.equals("S001"))
    		{
    			state="进行中的";
    		}
    		if(state.equals("S002"))
    		{
    			state="已经完成";
    		}
    		if(state.equals("S003"))
    		{
    			state="出现问题";
    		}
    		
    		String levelStr = request.getParameter("taskLevelSelect");
    		int level = 1;
    		
    		if(levelStr.equals("L002"))
    		{
    			level = 2;
    		}
    		
    		int milepost = Integer.parseInt(request.getParameter("milepost"));
    		float rate = Float.parseFloat(request.getParameter("rate"));
    		String startTime = request.getParameter("startTime");
    		String[] tempTime = startTime.split("/");
    		if(tempTime.length==3)
    		{
    			startTime = tempTime[2]+"/"+tempTime[0]+"/"+tempTime[1];
    		}
    		else
    		{
    			startTime = "1000/01/01";
    		}
    		
    		
    		String endTime = "1000/01/01";
    		
    		String planEndtime = request.getParameter("planEndtime");
    		tempTime = planEndtime.split("/");
    		if(tempTime.length==3)
    		{
    			planEndtime = tempTime[2]+"/"+tempTime[0]+"/"+tempTime[1];
    		}
    		else
    		{
    			planEndtime = "1000/01/01";
    		}
    		
    		float budget = Float.parseFloat(request.getParameter("budget"));
    		String[] userSelect = request.getParameterValues("userSelect");
    		String summary = new String(request.getParameter("summary").getBytes("ISO-8859-1"),"utf-8");
    		

    		Task task = new Task(-1,taskName,state,rate,level,milepost,budget,-1,summary,startTime,endTime,planEndtime,"");
    		int id=com.business.TaskManager.createTask(projectID, task,userSelect);
    		if(id!=0)
    		{
    			//写入任务日志
    			String  logpath=new String();
        		for(int i=0;i<WorktimeInfomation.systemsettinglist.size();i++)
        		{
        			SystemSetting temp=(SystemSetting)WorktimeInfomation.systemsettinglist.get(i);    
        			logpath=temp.getTasklogpath();
        		}
        		String file=Integer.toString(projectID);			
        		logpath=logpath+file+"_"+id+".txt";
//        		logpath=logpath.replaceAll("\\\\", "\\\\\\\\");
    			
    			Date date=new Date();
    	        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        String realDate = format.format(date); 
    	        /*String[] temp = realDate.split(" ");
    	        realDate = temp[0]+"/"+temp[1];*/
    	        String creater=(String)session.getAttribute("account");
    	        User user=MemberInformation.seachUser(Integer.parseInt(creater));
    	        Project project=ProjectManagement.searchProject(projectID);
    	        
    	        String data="日志创建时间"+" "+realDate+"\r\n"+"创建人"+" "+session.getAttribute("account")+" "+user.getName()+"\r\n"+"所属项目"+" "+projectID+" "+project.getName()+"\r\n";
    	        
//    	        System.out.println(data);
    	        
    	        FileOperation.saveAsFileWriter(logpath, data);

    			response.sendRedirect("displayTask.jsp");
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
