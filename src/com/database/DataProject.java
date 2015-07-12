package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import com.business.*;

public class DataProject {
	private int id;
    private String name;
    private String starttime;
    private String expectendtime;
    private String actualendtime;
    private float budget;
    private int priority;
    private int managerid;
    private String state;
    private float schedule;
    private float workedtime;
    private float plantime;
    private float actualtime;
    private String summary;
    

	public DataProject(){
    	this.id=-1;
    	this.name=null;
    	this.starttime=null;
    	this.expectendtime=null;
    	this.actualendtime=null;
    	this.budget=0;
    	this.priority=-1;
    	this.managerid=-1;
    	this.state=null;
    	this.schedule=0;
    	this.workedtime=0;
    	this.plantime=0;
    	this.actualtime=0;
    	this.summary=null;	
    }
	
    public DataProject(int id,String name,String start,String expect,String factend,float budget,int managerid,
    		String state,int priority,float rate,float workedtime,float plantime,float actualtime,String summary){
    	this.id=id;
    	this.name=name;
    	this.starttime=start;
    	this.expectendtime=expect;
    	this.actualendtime=factend;
    	this.budget=budget;
    	this.priority=priority;
    	this.managerid=managerid;
    	this.state=state;
    	this.schedule=rate;
    	this.workedtime=workedtime;
    	this.plantime=plantime;
    	this.actualtime=actualtime;
    	this.summary=summary;	
    }
    
    /*public DataProject(int id,String name,String start,String expect,float rate){
    	this.id=id;
    	this.name=name;
    	this.starttime=start;
    	this.expectendtime=expect;
    	this.schedule=rate;
    }*/
    
    public static int insert(String name,String start,String end,float budget,int managerid,
			String state,int priority,float plantime,String summary)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlID = "select max(project_id) from project";
			ResultSet res = stm.executeQuery(sqlID);
			int bigID=-1;
			while(res.next())
			{
				bigID = res.getInt(1);
			}
			bigID+=1;
			String sql = "insert into project(project_id,project_name,start_time,suppose_endtime,budget,manager_id,state,level,plan_hour,summary) values("+bigID+",'"+name+"','"+start+"','"+end+"',"+budget+","+managerid+",'"+state+"',"+priority+","+plantime+",'"+summary+"');";
			stm.execute(sql);
			res.close();
			return bigID;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return -1;
	}
    
    public static Vector allProject(){
    	Vector temp=new Vector();
    	DataProject dp = null;
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from project";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				dp = new DataProject(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),
						res.getFloat(6),res.getInt(7),res.getString(8),res.getInt(9),res.getFloat(10),res.getFloat(11),res.getFloat(12),res.getFloat(13),res.getString(14));
				temp.add(dp);
				//System.out.print(res.getString(6));
			}
			res.close();			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
    	return temp;   	
    }
    
   /* public static DataProject searchProject(int id){
    	DataProject dp=null;
    	Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from project where project_id="+id+";";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{			
				dp = new DataProject(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),
						res.getFloat(6),res.getInt(7),res.getString(8),res.getInt(9),res.getFloat(10),res.getFloat(11),res.getFloat(12),res.getFloat(13),res.getString(14));
			}
			res.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			try
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
    	return dp;
    }*/
    
  
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
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
	
	
	/*public static void main(String[] args)
	{
		DataProject temp =new DataProject();
    	Vector res = temp.allProject();
    	temp = (DataProject)res.get(0);
    	System.out.println(res.size());
    	System.out.println(temp.getName());
    	//DataProject.insert("软件工程344","2015/09/25","2015/11/25", 22, 112222, "开启", 1, (float)12.7,"很有意思啊");
	}*/
		
}
