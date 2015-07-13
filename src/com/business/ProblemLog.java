package com.business;

public class ProblemLog {
	private int problemID;
	private int taskID;
	private String createTime;
	private int createUserID;
	private int dealUserID;
	private int status;
	private String problemDescreption;
	private String logpath;
	
	
	
	public ProblemLog()
	{
		this.problemID = -1;
		this.taskID = -1;
		this.createTime = "";
		this.createUserID = -1;
		this.dealUserID = -1;
		this.status = 0;
		this.problemDescreption = "";
		this.logpath = "";
	}
	
	public ProblemLog(int problemID,int taskID,String createTime,int createUserID,int dealUserID,
			int status,String problemDescreption,String logpath)
	{
		this.problemID = problemID;
		this.taskID = taskID;
		this.createTime = createTime;
		this.createUserID = createUserID;
		this.dealUserID = dealUserID;
		this.status = status;
		this.problemDescreption = problemDescreption;
		this.logpath = logpath;
	}
	
	public String getLogPath()
	{
		return this.logpath;
	}
	
	public String getProblemDescreption()
	{
		return this.problemDescreption;
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	public int getDealUserID()
	{
		return this.dealUserID;
	}
	
	public int getCreateUserID()
	{
		return this.createUserID;
	}
	
	public int getProblemID()
	{
		return this.problemID;
	}
	
	public int getTaskID()
	{
		return this.taskID;
	}
	
	public String getCreateTime()
	{
		return this.createTime;
	}

}
