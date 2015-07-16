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
	private Vector problemlist;
	
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
		this.problemlist = new Vector(10,6);
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
		this.userList = new Vector(10,6);
		this.problemlist = new Vector(10,6);
	}
	
	public void addProblemLog(ProblemLog problem)
	{
		this.problemlist.add(problem);
	}
	
	public Vector getProblemList()
	{
		return this.problemlist;
	}
	
	public Vector getUserList()
	{
		return this.userList;
	}
	
	public void setUserList(Vector userList)
	{
		this.userList = userList;
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
	public void setTasklogPath(String tasklogPath)
	{
		this.tasklogPath=tasklogPath;
	}
	
	public String getPlanEndtime()
	{
		return this.planEndtime;
	}
	
	public void setPlanEndTime(String planEndtime)
	{
		this.planEndtime = planEndtime;
	}
	
	public String getStartTime()
	{ 
		return this.startTime;
	}
	
	public String getEndtime()
	{
		return this.endTime;
	}
	
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	public String getSummary()
	{
		return this.summary;
	}
	
	public void setSummary(String summary)
	{
		this.summary = summary;
	}
	
	public int getFathertaskID()
	{
		return this.fathertaskID;
	}
	
	public void setFatherID(int fathertaskID)
	{
		this.fathertaskID = fathertaskID;
	}
	
	public float getBudget()
	{
		return this.budget;
	}
	
	public void setBudget(float budget)
	{
		this.budget = budget;
	}
	
	public int getMilepost()
	{
		return this.milepost;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	
	
	public int getTaskID()
	{
		return this.taskID;
	}
	public void setTaskID(int taskid){
		this.taskID=taskid;
	}
	
	public String getTaskName()
	{
		return this.taskName;
	}
	
	public String getState()
	{
		return this.state;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public float getRate()
	{
		return this.rate;
	}
	
	public void setRate(float rate)
	{
		this.rate = rate;
	}
    
}
