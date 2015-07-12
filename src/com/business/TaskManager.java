package com.business;
import com.business.*;
import com.database.DataTask;

public class TaskManager {
	
	public TaskManager()
	{}
	
	public static int createTask(int projectID,Task addTask)
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
				
				if(DataTask.add(taskName, state, rate, level, projectID, milepost, budget, fathertaskID, summary, startTime, endTime, planEndtime, tasklogPath)==1)
				{
					return 1;
				}				
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}

}
