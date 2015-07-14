package com.business;

import java.util.Vector;

public class SystemSetting {
	private  int worktimeday;
	private  int worktimehour;
	private  String userlogpath;
	private  String tasklogpath;
	private  String problemlogpath;
	private  Vector rolelist;
	
	
	public SystemSetting(){}
	public SystemSetting(int day,int hour,String userlogpath,String tasklogpath,
			String problemlogpath,Vector rolelist){
		this.worktimeday=day;
		this.worktimehour=hour;
		this.userlogpath=userlogpath;
		this.tasklogpath=tasklogpath;
		this.problemlogpath=problemlogpath;
		this.rolelist=rolelist;
	}
	public  int getWorktimeday() {
		return worktimeday;
	}
	public  void setWorktimeday(int worktimeday) {
		this.worktimeday = worktimeday;
	}
	public  int getWorktimehour() {
		return worktimehour;
	}
	public  void setWorktimehour(int worktimehour) {
		this.worktimehour = worktimehour;
	}
	public  String getUserlogpath() {
		return userlogpath;
	}
	public  void setUserlogpath(String userlogpath) {
		this.userlogpath = userlogpath;
	}
	public  String getTasklogpath() {
		return tasklogpath;
	}
	public  void setTasklogpath(String tasklogpath) {
		this.tasklogpath = tasklogpath;
	}
	public  String getProblemlogpath() {
		return problemlogpath;
	}
	public  void setProblemlogpath(String problemlogpath) {
		this.problemlogpath = problemlogpath;
	}
	public  Vector getRolelist() {
		return rolelist;
	}
	public  void setRolelist(Vector rolelist) {
		this.rolelist = rolelist;
	}
	public Roleinformation[] getRole(){
		Vector temp=this.getRolelist();
		Roleinformation role[]=new Roleinformation[temp.size()];
		for(int i=0;i<temp.size();i++)
		{
			role[i]=(Roleinformation)temp.get(i);
		}
		return role;		
	}
}
