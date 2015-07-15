package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.business.*;

public class DataTask {
	private int taskID;
	private String taskName;
	private String state;
	private float rate;
	private int level;
	private int projectID;
	private int milepost;
	private float budget;
	private int fathertaskID;
	private String summary;
	private String startTime;
	private String endTime;
	private String planEndtime;
	private String tasklogPath;
	
	public DataTask()
	{
		this.taskID = -1;
		this.taskName = "";
		this.state = "";
		this.rate = (float)0.00;
		this.level = 0;
		this.projectID = -1;
		this.milepost = 0;
		this.budget = (float)0.00;
		this.fathertaskID = -1;
		this.summary = "";
		this.startTime = "";
		this.endTime = "";
		this.planEndtime = "";
		this.tasklogPath = "";
	}
	
	public DataTask(int taskID,String taskName,String state,float rate,int level,int projectID,int milepost,float budget,int fathertaskID,
			String summary,String startTime,String endTime,String planEndtime,String tasklogPath)
	{
		this.taskID = taskID;
		this.taskName = taskName;
		this.state = state;
		this.rate = rate;
		this.level = level;
		this.projectID = projectID;
		this.milepost = milepost;
		this.budget = budget;
		this.fathertaskID = fathertaskID;
		this.summary = summary;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planEndtime = planEndtime;
		this.tasklogPath = tasklogPath;
	}
	
	public static int updateLogpath(String logpath)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlSelect = "select task_id,project_id from  task;";
			ResultSet res = stm.executeQuery(sqlSelect);
			while(res.next())
			{
				String tempLogpath = logpath+res.getInt(2)+"_"+res.getInt(1)+".txt";
				String sql = "update task set tasklog_path='"+tempLogpath+"';";
				stm.execute(sql);
			}
			
			
			return 1;
			
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
		
		return 0;
	}

	public static Vector searchProjectTask(int projectID)
	{
		Vector list = new Vector(10,6);
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from task where project_id="+projectID+";";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				Task tempTask = new Task(res.getInt(1),res.getString(2),res.getString(3),res.getFloat(4),res.getInt(5),
						res.getInt(7),res.getFloat(8),res.getInt(9),res.getString(10),res.getString(11),res.getString(12),
						res.getString(13),res.getString(14));
				list.add(tempTask);
			}
			res.close();
			return list;
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
		
		return list;
	}

	
	public static int add(String taskName,String state,float rate,int level,int projectID,int milepost,float budget,int fathertaskID,
			String summary,String startTime,String endTime,String planEndtime,String tasklogPath)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlID = "select max(task_id) from task";
			ResultSet res = stm.executeQuery(sqlID);
			int taskID =-1;
			if(res.next())
			{
				taskID = 1 + res.getInt(1);
			}
			else
			{
				taskID = 1;
			}
			
			/*
			int taskID,String taskName,String state,float rate,int level,int projectID,int milepost,float budget,int fathertaskID,
			String summary,String startTime,String endTime,String planEndtime,String tasklogPath)
			*/
			
			String sql = "insert into task values("+taskID+",'"+taskName+"','"+state+"',"+rate+","+level+","+projectID+","+milepost+","+budget+", "+fathertaskID+", '"+summary+"','"+startTime+"','"+endTime+"','"+planEndtime+"','"+tasklogPath+"');";
			stm.execute(sql);
			
			
			stm.close();
			return taskID;
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
		return 0;
	}
	
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataTask.add("test","wenti",50.23,1,1,1,20.23,-1,"zongjie",,String endTime,String planEndtime,String tasklogPath);
	}*/

}
