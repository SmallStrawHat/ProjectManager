package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.RequestDispatcher;

public class Myuser {
	private int userID;
	private String name;
	private String phone;
	private String email;
	private String password;
	private String userRole;
	private int userState;
	private String userLogPath;
	private String summary;
	
	public Myuser()
	{
		this.userID =-1;
		this.name = null;
		this.phone = null;
		this.email = null;
		this.password = null;
		this.userRole = null;
		this.userState = 0;
		this.userLogPath = null;
		this.summary = null;
	}
	
	public Myuser(int userID,String name,String phone,String email,String password,String userRole,
			int userState,String userLogPath,String summary)
	{
		this.userID =userID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.userState = userState;
		this.userLogPath = userLogPath;
		this.summary = summary;
	}
	
	public static Myuser search(int account)
	{
		Myuser findRes = null;
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from myuser where user_id="+account+";";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				findRes = new Myuser(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),
						res.getString(6),res.getInt(7),res.getString(8),res.getString(9));
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
		
		return findRes;
	}
	
	public static Vector searchAll()
	{
		Vector resVec = new Vector(10,6);
		Myuser findRes = null;
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "select * from myuser";
			ResultSet res = stm.executeQuery(sql);
			while(res.next())
			{
				findRes = new Myuser(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),
						res.getString(6),res.getInt(7),res.getString(8),res.getString(9));
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
	
	public static int delete(int account)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "delete from myuser where user_id="+account+";";
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
	
	public static int insert(String name,String phone,String email,
			String userRole,String password,int userState,String summary)
	{
	
		Myuser findRes = null;
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sqlID = "select max(user_id) from myuser";
			ResultSet res = stm.executeQuery(sqlID);
			int bigID=-1;
			while(res.next())
			{
				bigID = res.getInt(1);
			}
			bigID+=1;
			String sql = "insert into myuser values("+bigID+",'"+name+"','"+phone+"','"+email+"','"+password+"','"+userRole+"',"+userState+",'','"+summary+"');";
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
	
	public int getUserID()
	{
		return this.userID;
	}
	
	public void setUserID()
	{}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getUserRole()
	{
		return this.userRole;
	}
	
	public int getUserState()
	{
		return this.userState;
	}
	
	public String getUserLogPath()
	{
		return this.userLogPath;
	}
	
	public String getSummary()
	{
		return this.summary;
	}
		
	
/*	public static void main(String arg[])
	{
		Myuser temp = new Myuser();
    	Vector res = temp.searchAll();
    	temp = (Myuser)res.get(0);
    	System.out.println(res.size());
    	System.out.println(temp.getUserRole());
		Myuser.insert("发的发顺丰", "156", "afa", "affd", "afaf", 1, "案发当时");
	}*/
	
}
