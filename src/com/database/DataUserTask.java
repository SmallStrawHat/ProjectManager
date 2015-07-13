package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataUserTask {
	
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
