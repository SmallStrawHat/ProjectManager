package com.business;

import java.text.SimpleDateFormat;
import java.util.*;

import com.database.DataProblemLog;
import com.database.DataProject;
import com.database.DataTask;
import com.database.DataUserTask;

public class ProjectManagement {
	private static Vector projectlist;
	
	public ProjectManagement(){}
	
	public static int init(){
		projectlist=new Vector();
		
		for(int i=0;i<DataProject.allProject().size();i++)
		{
			DataProject temp=(DataProject)(DataProject.allProject().get(i));
			Vector tasklist=DataTask.searchProjectTask(temp.getId());
			for(int j=0;j<tasklist.size();j++)
			{
				Task tempTask = (Task)tasklist.get(j);
				Vector userList = DataUserTask.searchUserIDList(tempTask.getTaskID());
				for(int k=0;k<userList.size();k++)
				{
					int userID = ((Integer)userList.get(k)).intValue();
					User tempUser = MemberInformation.seachUser(userID);
					///可能有问题？？？？？？？
					(tempTask.getUserList()).add(tempUser);
				}
				Vector problemList = DataProblemLog.search(tempTask.getTaskID());
				for(int k=0;k<problemList.size();k++)
				{
					ProblemLog tempPro = (ProblemLog)problemList.get(k);
					tempTask.addProblemLog(tempPro);
				}
			}
			
			
			Project pro=new Project(temp.getId(),temp.getName(),temp.getStarttime(),temp.getExpectendtime(),
					temp.getActualendtime(),temp.getBudget(),temp.getManagerid(),temp.getState(),temp.getPriority(),
					temp.getSchedule(),temp.getWorkedtime(),temp.getPlantime(),temp.getActualtime(),
					temp.getSummary(),tasklist);
			projectlist.add(pro);
			
		}
		ProjectManagement.changeProjectState();
		return 1;
		
	}
	
	
	
	public static int createProject(String name,String start,String end,float budget,int managerid,
			String state,int priority,float plantime,String summary)
	{
		Project pro=new Project();
		pro=pro.createProject(name, start, end, budget, managerid, state, priority, plantime, summary);
		if(pro!=null)
		{
			projectlist.add(pro);
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public static Project searchProject(int id){
		Project db=null;
		for(int i=0;i<ProjectManagement.projectlist.size();i++)
		{		
			db=(Project)ProjectManagement.projectlist.get(i);		
			if(db.getId()==id)
				break;
		}
		return db;		
	}
	
	public static Vector getAllProjectList(){
		return ProjectManagement.projectlist;		
	}
	
	public static Vector searchProject(String name){
		Vector result=new Vector();
		for(int i=0;i<projectlist.size();i++)
		{
			Project pro=(Project)projectlist.get(i);
			if(pro.getName().equals(name))
			{		
				result.add(pro);
			}
		}
		return result;
	}
	public static Vector fuzzySearch(String name){
		Vector result=new Vector();
		for(int i=0;i<projectlist.size();i++)
		{
			Project pro=(Project)projectlist.get(i);
			if(pro.getName().indexOf(name)!=-1)
			{		
				result.add(pro);
			}
		}
		return result;
	}
	public static void changeProjectState(){
		Vector temp=ProjectManagement.projectlist;
		for(int i=0;i<temp.size();i++)
		{
			Project pro=(Project)ProjectManagement.projectlist.get(i);
			Vector tas=pro.getTaskList();
			int tasked=0;
			int tasking=0;
			int endtime[]=new int[tas.size()];
			for(int k=0;k<endtime.length;k++)
			{
				endtime[k]=0;
			}
			for(int j=0;j<tas.size();j++)
			{
				Task task=(Task)pro.getTaskList().get(j);
				System.out.println(task.getState());
		        if(task.getState().equals("进行中的"))
		        {
		        	System.out.println("******任务进行中******");
		        	
		        	tasking++;
//		        	break;
		        }
		        if(task.getState().equals("已经完成"))
		        {
		        	System.out.println("******已经完成******");
		        	String end=task.getEndtime().replaceAll("-", "");
		        	System.out.println(end);
		        	endtime[j]=Integer.parseInt(end);
		        	tasked++;
		        }
			}
			float rate=0;
			rate=(float)((float)tasked/(float)tas.size());
			rate=(float)rate*100;
			if(tasking!=0)
			{
				pro.setState("进行中");
	        	pro.setSchedule(rate);
	        	DataProject.updateState(pro.getId(), "进行中");
	        	DataProject.updateRate(pro.getId(), rate);
	        	break;
			}
			if(tasked==tas.size())
			{
				pro.setState("已完成");
				pro.setSchedule(rate);
				int projectendtime=ProjectManagement.maxtime(endtime);
				System.out.println("这里是最大时间的返回"+projectendtime);
				pro.setActualendtime(String.valueOf(projectendtime));
				DataProject.updateState(pro.getId(), "已完成");
				DataProject.updateRate(pro.getId(), rate);
				DataProject.updateEndtime(pro.getId(), String.valueOf(projectendtime));
			}
		}
	}
	public static int maxtime(int a[])
	{
		int n=a.length;
		if(n==1)
		{
			return a[0];
		}
		if(n==0)
		{
			return 100;
		}
		int max=a[0];
		for(int i=1;i<n;i++)
		{
			if(a[i]>max)
			{
				max=a[i];
			}
		}
		return max;
	}

	public static void main(String[] args)
	{
		MemberInformation.init();
	    ProjectManagement.init(); 
	    Project p=ProjectManagement.searchProject(Integer.parseInt("2"));
	    System.out.println(p.getName());
		Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String realDate = format.format(date); 
        String[] time = realDate.split(" ");
        
        ProjectManagement.changeProjectState();
   	}
	
}
