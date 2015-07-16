package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class DataUserTask {
	
	public static Vector searchUserIDList(int taskID)
	{
		Vector resList = new Vector();
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from usertask where task_id="+taskID+";"; 
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				resList.add(res.getInt(1));
			}
			
			stm.close();
			return resList;
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
		return resList;
	}
	
	public static int update(int taskID,Vector userList)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlDel = "delete from usertask where task_id= "+taskID+";";
			stm.execute(sqlDel);
			
			for(int i=0;i<userList.size();i++)
			{
				int userID = Integer.parseInt((String)userList.get(i));
				String sql = "insert into usertask values("+userID+","+taskID+");";
				stm.execute(sql);
			}
			
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
	
	public static int insert(int taskID,String[] userList)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			
			for(int i=0;i<userList.length;i++)
			{
				int userID = Integer.parseInt(userList[i]);
				String sql = "insert into usertask values("+userID+","+taskID+");";
				stm.execute(sql);
			}
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

/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
