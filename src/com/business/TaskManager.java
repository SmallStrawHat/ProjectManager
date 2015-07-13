package com.business;
import java.util.Vector;

import com.business.*;
import com.database.DataTask;
import com.database.DataUserTask;

public class TaskManager {
	
	public TaskManager()
	{}
	
	public static int createTask(int projectID,Task addTask,String[] userList)
	{
		Project project = ProjectManagement.searchProject(projectID);
		if(project != null)
		{
			if(project.addTask(addTask)==1)
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
				
				int taskID = DataTask.add(taskName, state, rate, level, projectID, milepost, budget, fathertaskID, summary, startTime, endTime, planEndtime, tasklogPath);
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
						return 1;
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
