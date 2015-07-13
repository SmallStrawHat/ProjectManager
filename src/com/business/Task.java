package com.business;

import java.util.Vector;

public class Task {
	private int taskID;
	private String taskName;
	private String state;
	private float rate;
	private int level;
	private int milepost;
	private float budget;
	private int fathertaskID;
	private String summary;
	private String startTime;
	private String endTime;
	private String planEndtime;
	private String tasklogPath;
	private Vector userList;
	
	public Task()
	{
		this.taskID = -1;
		this.taskName = "";
		this.state = "";
		this.rate = (float)0.00;
		this.level = 0;
		this.milepost = 0;
		this.budget = (float)0.00;
		this.fathertaskID = -1;
		this.summary = "";
		this.startTime = "";
		this.endTime = "";
		this.planEndtime = "";
		this.tasklogPath = "";
		this.userList = new Vector(10,6);
	}
	
	public Task(int taskID,String taskName,String state,float rate,int level,int milepost,float budget,int fathertaskID,
			String summary,String startTime,String endTime,String planEndtime,String tasklogPath)
	{
		this.taskID = taskID;
		this.taskName = taskName;
		this.state = state;
		this.rate = rate;
		this.level = level;
		this.milepost = milepost;
		this.budget = budget;
		this.fathertaskID = fathertaskID;
		this.summary = summary;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planEndtime = planEndtime;
		this.tasklogPath = tasklogPath;
		this.userList = new Vector(10,6);;
	}
	
	public Vector getUserList()
	{
		return this.userList;
	}
	
	public int addUser(User temp)
	{
		this.userList.add(temp);
		return 1;
	}
	
	public String getTasklogPath()
	{
		return this.tasklogPath;
	}
	
	public String getPlanEndtime()
	{
		return this.planEndtime;
	}
	
	public String getStartTime()
	{
		return this.startTime;
	}
	
	public String getEndtime()
	{
		return this.endTime;
	}
	
	public String getSummary()
	{
		return this.summary;
	}
	
	public int getFathertaskID()
	{
		return this.fathertaskID;
	}
	
	public float getBudget()
	{
		return this.budget;
	}
	
	public int getMilepost()
	{
		return this.milepost;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	
	public int getTaskID()
	{
		return this.taskID;
	}
	
	public String getTaskName()
	{
		return this.taskName;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public float getRate()
	{
		return this.rate;
	}
    
}
