package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;

import com.business.*;

public class Worktime {
	private int workDays;
	private int workDayHours;
	private String userLog;
	private String userLogPath;
	private String problemLogPath;
	
	public Worktime()
	{
		this.workDays = -1;
		this.workDayHours = -1;
		this.userLog = null;
		this.userLogPath = null;
		this.problemLogPath = null;
	}
	
	public Worktime(int workDays,int workDayHours,String userLog,String userLogPath,String problemLogPath)
	{
		this.workDays = workDays;
		this.workDayHours = workDayHours;
		this.userLog = userLog;
		this.userLogPath = userLogPath;
		this.problemLogPath = problemLogPath;
	}
	
	public static Vector searchAll()
	{
		Vector resVec = new Vector(10,6);
		Worktime findRes = null;
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from worktime";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				findRes = new Worktime(res.getInt(1), res.getInt(2), res.getString(3),res.getString(4), res.getString(5));
				resVec.add(findRes);
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
		return resVec;
	}
	
	public static int updateworktime(
			int workDays,
			int workDayHours,
			String userLog,
			String userLogPath,
			String problemLogPath)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			
			int oldDataWorkDays = -1;
			String sql1 = 
					"select workdays from worktime;";
			ResultSet res = stm.executeQuery(sql1);
			while(res.next())
			{
				oldDataWorkDays = res.getInt(1);
			}
			res.close();
			
			String sql2 = 
					"update worktime set workdays='"+workDays
					+"',workdayhours='"+workDayHours
					+"',userlog='"+userLog
					+"',userlogpath='"+userLogPath
					+"',problemlogpath='"+problemLogPath
					+"' where workdays='"+oldDataWorkDays +"';";
			stm.execute(sql2);

			System.out.print(sql1+" 123 "+oldDataWorkDays+" 456 "+sql2);
			stm.close();
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
	
	public int getWorkdays()
	{
		return workDays;
	}
	
	public int getWorkdayHours()
	{
		return workDayHours;
	}
	
	public String getUserLog()
	{
		return userLog;
	}

	public String getuserLogPath()
	{
		return userLogPath;
	}
	
	public String getProblemLogPath()
	{
		return problemLogPath;
	}
		
	
	/*public static void main(String arg[])
	{
	//	Vector resVec = new Vector(10,6);
		Worktime resVec=(Worktime)Worktime.searchAll().get(0);
		System.out.println(resVec.getUserLog());
//		Worktime.updateworktime(3, 5,"D:\\\\WorktimeInfomation1\\\\","D:\\\\WorktimeInfomation2\\\\","D:\\\\WorktimeInfomation3\\\\");
	}*/
	
}
