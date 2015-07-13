package com.business;

import java.util.Vector;

import com.database.DataProject;

public class Project {
	private int id;
    private String name;
    private String starttime;
    private String  expectendtime;    //预计结束时间
    private String  actualendtime;     //实际结束时间
    private float budget;    
    private int managerid;
    private String state;
    private int priority;
    private float schedule;
    private float workedtime;
    private float plantime;
    private float actualtime;
    private String summary;
    private Vector taskList;
    
    public Project(){}
    
    public Project(int id,String name,String starttime,String expectendtime,
    		String actualendtime,float budget,int managerid,String state,
    		int priority,float schedule,float workedtime,float plantime,
    		float actualtime,String summary,Vector tasklist){
    	this.id=id;
    	this.name=name;
    	this.starttime=starttime;
    	this.expectendtime=expectendtime;
    	this.actualendtime=actualendtime;
    	this.budget=budget;
    	this.managerid=managerid;
    	this.state=state;
    	this.priority=priority;
    	this.schedule=schedule;
    	this.workedtime=workedtime;
    	this.plantime=plantime;
    	this.actualtime=actualtime;
    	this.summary=summary;
    	this.taskList=new Vector();
    	this.taskList=tasklist;
    	
    }
    
    public Vector getTaskList()
    {
    	return this.taskList;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getExpectendtime() {
		return expectendtime;
	}
	public void setExpectendtime(String expectendtime) {
		this.expectendtime = expectendtime;
	}
	public String getActualendtime() {
		return actualendtime;
	}
	public void setActualendtime(String actualendtime) {
		this.actualendtime = actualendtime;
	}
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	public int getManagerid() {
		return managerid;
	}
	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public float getSchedule() {
		return schedule;
	}
	public void setSchedule(float schedule) {
		this.schedule = schedule;
	}
	public float getWorkedtime() {
		return workedtime;
	}
	public void setWorkedtime(float workedtime) {
		this.workedtime = workedtime;
	}
	public float getPlantime() {
		return plantime;
	}
	public void setPlantime(float plantime) {
		this.plantime = plantime;
	}
	public float getActualtime() {
		return actualtime;
	}
	public void setActualtime(float actualtime) {
		this.actualtime = actualtime;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public int addTask(Task task)
    {
		this.taskList.add(task);
    	return 1;
    }
	
	/*public static void init(){
		Vector temp=new Vector();
		temp=DataProject.allProject();
		sum=projectlist.size();
	}
	public static Vector initReturn(){
		projectlist=new Vector();
		//Vector pTemp = DataProject.allProject();
		projectlist=DataProject.allProject();
		return projectlist;
	}*/
    

}
