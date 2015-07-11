package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import com.business.*;

public class DataContacts {
	
	private int contactsID;
	private String name;
	private String phone;
	private String email;
	
	public DataContacts()
	{
		this.contactsID = -1;
		this.name = "";
		this.phone = "";
		this.email = "";
	}
	
	public DataContacts(int contactsID,String name,String phone,String email)
	{
		this.contactsID = contactsID;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public static Vector search(int contactID)
	{
		Vector res = new Vector(10,6);
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			
			String sql = "select * from contacts where contacts_id="+contactID+";";
			ResultSet resSql = stm.executeQuery(sql);
			int bigID=-1;
			while(resSql.next())
			{
				res.add(resSql.getInt(2));
			}
			
			stm.close();
			return res;
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
		return res;
	}
	
	public static int delete(int dealID,int deleteID)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			String sql = "delete from contacts where contacts_id="+dealID+" and user_id="+deleteID+";"; 
			stm.execute(sql);
			
			
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
	
	
	
	public static int add(int dealID,Vector userPub)
	{
		Connection conn =null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","201576");
			Statement stm = conn.createStatement();
			
			for(int i=0;i<userPub.size();i++)
			{
				int userID = ((Userpublic)userPub.get(i)).getUserID();
				String name = ((Userpublic)userPub.get(i)).getName();
				String phone = ((Userpublic)userPub.get(i)).getPhone();
				String userRole = ((Userpublic)userPub.get(i)).getUserRole();
				String email = ((Userpublic)userPub.get(i)).getEmail();
				String sql = "insert into contacts values("+dealID+","+userID+",'"+name+"','"+phone+"','"+userRole+"','"+email+"');";
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
	
	/*public static void main(String arg[])
	{
		Vector tt = new Vector(10,5);
		Userpublic kk = new Userpublic(112005,"afaf","45646","fdasfasf@","fddddd");
		tt.add(kk);
		DataContacts.add(112001, tt);
		System.out.println("do!");
	}
*/
}
