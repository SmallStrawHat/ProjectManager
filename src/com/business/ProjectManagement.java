package com.business;

import java.util.*;
import com.database.DataProject;

public class ProjectManagement {
	private static Vector projectlist;
	private static int sum;
	
	public ProjectManagement(){}
	
	public static int init(){
		projectlist=new Vector();
		
		for(int i=0;i<DataProject.allProject().size();i++)
		{
			DataProject temp=(DataProject)(DataProject.allProject().get(i));
			Vector tasklist=new Vector();
//记得处理任务列表  未完成！！！！！！！！！！！！！！！！！！！！！！！！！

			Project pro=new Project(temp.getId(),temp.getName(),temp.getStarttime(),temp.getExpectendtime(),
					temp.getActualendtime(),temp.getBudget(),temp.getManagerid(),temp.getState(),temp.getPriority(),
					temp.getSchedule(),temp.getWorkedtime(),temp.getPlantime(),temp.getActualtime(),
					temp.getSummary(),tasklist);
			projectlist.add(pro);
			
		}
		return 1;
		
	}
	public static int createProject(String name,String start,String end,float budget,int managerid,
			String state,int priority,float plantime,String summary)
	{
		/*String starttime=start.toString();
		String endtime=end.toString();*/
		if(DataProject.insert(name, start, end, budget, managerid, state, priority, plantime, summary)!=-1)		
		{
			sum++;
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

	/*public static void main(String[] args)
	{
	    ProjectManagement.init(); 
	    Project p=ProjectManagement.searchProject(Integer.parseInt("2"));
	    System.out.println(p.getName());
	    

   	}*/
	
	
	
	
	
}
