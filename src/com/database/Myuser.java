package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
	private int contactsID;
	
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
		this.contactsID = -1;
	}
	
	public Myuser(int userID,String name,String phone,String email,String password,String userRole,
			int userState,String userLogPath,int contactsID)
	{
		this.userID =userID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.userState = userState;
		this.userLogPath = userLogPath;
		this.contactsID = contactsID;
	}
	
	public Myuser search(int account)
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
						res.getString(6),res.getInt(7),res.getString(8),res.getInt(9));
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
	
	public void getUserID()
	{}
	
	public void setUserID()
	{}
	
	public String getPassword()
	{
		return this.password;
	}
		
	
	/*public static void main(String arg[])
	{
		Myuser temp = new Myuser();
    	Myuser res = temp.search(Integer.parseInt("112001"));
    	System.out.println(res.getPassword());
	}*/
	
}
