package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;

public class Role {
	private String userRole;
	private String Authorization;
	
	public Role()
	{
		this.userRole = null;
		this.Authorization = null;
	}
	
	public Role(String userRole,String authorization)
	{
		this.userRole = userRole;
		this.Authorization = authorization;
	}
	
	public static Vector searchAll()
	{
		Vector resVec = new Vector(10,6);
		Role findRes = null;
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from Role";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				findRes = new Role(res.getString(1),res.getString(2));
				resVec.add(findRes);
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
	
	public static int deleteRole(String Role)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "delete from role where user_role='"+Role+"';";
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
/*
	public static int update(int account,int lag)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql;
			if(lag==1)
			{
				sql = "update myuser set user_state=1 where user_id="+account+";";
			}
			else
			{
				sql = "update myuser set user_state=0 where user_id="+account+";";
			}
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
	*/
	
	public static int insert(String userRole,String Authorization)
	{
	
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			
			String sql = "insert into role values('"+userRole+"','"+Authorization+"');";
			stm.execute(sql);
			
			return 0;
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
	
	public String getUserRole()
	{
		return this.userRole;
	}
	
	public String getAuthorization()
	{
		return this.Authorization;
	}
		
	
/*	public static void main(String arg[])
	{
		Myuser temp = new Myuser();
		Role.deleteRole("111111");
	}*/
	
}
