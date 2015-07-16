package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.business.*;

public class DataProblemLog {
	
	public static Vector search(int taskID)
	{
		Vector resVec = new Vector(10,6);
		ProblemLog findProblem = null;
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from problemlog where task_id="+taskID+";";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				/*int problemID,int taskID,String createTime,int createUserID,int dealUserID,
				int status,String problemDescreption,String logpath*/
				findProblem = new ProblemLog(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),
						res.getInt(6),res.getString(7),res.getString(8));
				resVec.add(findProblem);
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
		return resVec;
	}
	
	public static int updateLogpath(String logpath)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			Statement stmSql = conn.createStatement();
			String sqlSelect = "select problem_id,task_id from  problemlog;";
			ResultSet res = stmSql.executeQuery(sqlSelect);
			while(res.next())
			{
				String tempLogpath = logpath+res.getInt(2)+"_"+res.getInt(1)+".txt";
				String sql = "update problemlog set logpath='"+tempLogpath+"' where problem_id="+res.getInt(1)+";";
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
	
	public static int insert(int taskID,String createTime,int createUserID,int dealUserID,
			int status,String problemDescreption,String logPath)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlID = "select max(problem_id) from problemlog";
			ResultSet res = stm.executeQuery(sqlID);
			int bigID=0;
			while(res.next())
			{
				bigID = res.getInt(1);				
			}
			bigID+=1;
			
			
			String logpath=logPath;
			String file=Integer.toString(taskID);			
    		logpath=logpath+file+"_"+bigID+".txt";    		
    		logpath=logpath.replaceAll("\\\\", "\\\\\\\\");
    		int senduserid=0;
    		
			String sql = "insert into problemlog values("+bigID+","+taskID+",'"+createTime+"',"+createUserID+","+dealUserID+","+status+",'"+problemDescreption+"','"+logpath+"','"+senduserid+"');";
			stm.execute(sql);
			res.close();
			System.out.print("bigid是：");
			System.out.println(bigID);
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
		return 0;
	}
	public static int updateSenduserid(int problemid,int senduserid)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement(); 		
			String sql = "update problemlog set senduserid='"+senduserid+"' where problem_id='"+problemid+"'";
			stm.execute(sql);
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
	public static int updateExchange(int problemid,int senduserid,int dealuserid)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement(); 		
			String sql = "update problemlog set senduserid='"+dealuserid+"',dealuser_id='"+senduserid+"' where problem_id='"+problemid+"'";
			stm.execute(sql);
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
	
	public static int[] searchSenduserid(int problemid)
	{
		Connection conn =null;
		int id[]=new int[2];
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement(); 		
			String sql = "select dealuser_id,senduserid from problemlog where problem_id='"+problemid+"';";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
			  id[0]=res.getInt(1);
			  id[1]=res.getInt(2);
			  return id;
			}
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
		return id;
	}
	
	public static int updateState(int problemid,int state)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement(); 		
			String sql = "update problemlog set status='"+state+"' where problem_id='"+problemid+"'";
			stm.execute(sql);
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
	

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	int n[]=new int[2];
		int n=DataProblemLog.insert(1, "20150306", 112001, 112005, 1, "", "");
		System.out.println(n);
	//	System.out.println(n[1]);
		
	}*/
}
