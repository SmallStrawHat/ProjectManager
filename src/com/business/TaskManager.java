package com.business;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.business.*;
import com.database.DataProblemLog;
import com.database.DataTask;
import com.database.DataUserTask;

public class TaskManager {
	
	public TaskManager()
	{}
	
	public static int createProblemLog(int taskID,String createTime,int createUserID,int dealUserID,
			int status,String problemDescreption)
	{
		Task tempTask = TaskManager.searchTask(taskID);
		if(tempTask != null)
		{
			String  logpath=new String();
    		for(int i=0;i<WorktimeInfomation.systemsettinglist.size();i++)
    		{
    			SystemSetting temp=(SystemSetting)WorktimeInfomation.systemsettinglist.get(i);    
    			logpath=temp.getProblemlogpath();
    		}
    		
			int problemID = DataProblemLog.insert(taskID, createTime, createUserID, dealUserID, status, problemDescreption,logpath);
			
			
    		String file=Integer.toString(taskID);			
    		logpath=logpath+file+"_"+problemID+".txt";    		
    		logpath=logpath.replaceAll("\\\\", "\\\\\\\\");
			
			
			if(problemID!=0)
			{
				//插入内存
				/*int problemID,int taskID,String createTime,int createUserID,int dealUserID,
				int status,String problemDescreption,String logpath*/
				ProblemLog tempProblem = new ProblemLog(problemID,taskID,createTime,createUserID,
						dealUserID,status,problemDescreption,logpath);
				tempTask.addProblemLog(tempProblem);
				return problemID;
			}
		}
		return 0;
	}
	
	/*projectID,taskID,level,fatherTaskID,userList,planEndtime,budget,summary,state,rate,endTime*/
	public static int editTask(int projectID,int taskID,int level,int fatherTaskID,Vector userList,String planEndtime,
			float budget,String summary,String state,float rate,String endTime)
	{
		Project project = ProjectManagement.searchProject(projectID);
		if(project != null)
		{
			//data add information
			Task tempTask = TaskManager.searchTask(taskID);
			tempTask.setLevel(level);
			tempTask.setFatherID(fatherTaskID);
			tempTask.setPlanEndTime(planEndtime);
			tempTask.setBudget(budget);
			tempTask.setSummary(summary);
			tempTask.setState(state);
			tempTask.setRate(rate);
			tempTask.setEndTime(endTime);
			
			Vector tempUserList = new Vector(10,6);
			for(int i=0;i<userList.size();i++)
			{
				User tempUser = MemberInformation.seachUser(Integer.parseInt((String)userList.get(i)));
				tempUserList.add(tempUser);
			}
			tempTask.setUserList(tempUserList);
			
			if(DataUserTask.update(taskID, userList)!=1)
				return 0;
			
			
			if(DataTask.update(taskID,level, fatherTaskID, planEndtime, budget, summary, state, rate, endTime)!=1)
				return 0;
			

			return 1;
		
		}
		return 0;
	}
	
	public static int createTask(int projectID,Task addTask,String[] userList)
	{
		Project project = ProjectManagement.searchProject(projectID);
		Task task=addTask;
		if(project != null)
		{
			//data add information
			String taskName = addTask.getTaskName();
			String state = addTask.getState();
			float rate = addTask.getRate();
			int level = addTask.getLevel();
			int milepost = addTask.getMilepost();
			float budget = addTask.getBudget();
			int fathertaskID = addTask.getFathertaskID();
			String summary = addTask.getSummary();
			String startTime = addTask.getStartTime();
			String endTime = addTask.getEndtime();
			String planEndtime = addTask.getPlanEndtime();
			String tasklogPath = addTask.getTasklogPath();
			
			String  logpath=new String();
    		for(int i=0;i<WorktimeInfomation.systemsettinglist.size();i++)
    		{
    			SystemSetting temp=(SystemSetting)WorktimeInfomation.systemsettinglist.get(i);    
    			logpath=temp.getTasklogpath();
    		}
    		
			
			int taskID = DataTask.add(taskName, state, rate, level, projectID, milepost, budget, fathertaskID, summary, startTime, endTime, planEndtime, logpath);
			
			String file=Integer.toString(projectID);			
    		logpath=logpath+file+"_"+taskID+".txt";    		
    		logpath=logpath.replaceAll("\\\\", "\\\\\\\\");
    		task.setTaskID(taskID);
    		task.setTasklogPath(logpath);
    		
			if(project.addTask(task)==1)
			{    		
				if(taskID !=0)
				{		
					if(DataUserTask.insert(taskID, userList)==1)
					{
						for(int i=0;i<userList.length;i++)
						{
							int userID = Integer.parseInt(userList[i]);
							User temp = MemberInformation.seachUser(userID);
							addTask.addUser(temp);
						}
						return taskID;
					}
				}				
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}

	public static Vector searchAllTask()
	{
		Vector resTask = new Vector(10,6);
		Vector projecList = ProjectManagement.getAllProjectList();
		for(int i=0;i<projecList.size();i++)
		{
			Project tempPro = (Project)projecList.get(i);
			Vector taskList = tempPro.getTaskList();
			for(int j=0;j<taskList.size();j++)
			{
				Task tempTask = (Task)taskList.get(j);
				resTask.add(tempTask);
			}
		}
		return resTask;
	}
	
	public static Vector searchAllProblem()
	{
		Vector resProblem = new Vector(10,6);
		Vector projecList = ProjectManagement.getAllProjectList();
		for(int i=0;i<projecList.size();i++)
		{
			Project tempPro = (Project)projecList.get(i);
			Vector taskList = tempPro.getTaskList();
			for(int j=0;j<taskList.size();j++)
			{
				Task tempTask = (Task)taskList.get(j);
				Vector tempProblem = tempTask.getProblemList();
				for(int k=0;k<tempProblem.size();k++)
				{
					ProblemLog problem = (ProblemLog)tempProblem.get(k);
					resProblem.add(problem);
				}
				
			}
		}
		return resProblem;
	}
	

	
	public static Task searchTask(int taskID)
	{
		Task resTask = null;
		Vector projecList = ProjectManagement.getAllProjectList();
		for(int i=0;i<projecList.size();i++)
		{
			Project tempPro = (Project)projecList.get(i);
			Vector taskList = tempPro.getTaskList();
			for(int j=0;j<taskList.size();j++)
			{
				Task tempTask = (Task)taskList.get(j);
				if(tempTask.getTaskID() == taskID)
				{
					resTask = tempTask;
					return resTask;
				}
			}
		}
		return resTask;
	}
	



}
