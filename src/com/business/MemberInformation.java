package com.business;

import java.util.Vector;
import com.database.*;

public class MemberInformation {
	private Vector userList;
	private int staffNum;
	
	
	/*
	 * public User(int userID,String name,String phone,String email,
			String userRole,String password,int userState,String logPath)
	 * */
	public MemberInformation()
	{
		this.userList = new Vector(10,6);
		this.staffNum = 0;
		Vector userTemp = Myuser.searchAll();
		for(int i=0;i<userTemp.size();i++)
		{
			Myuser tempMyuser = (Myuser)userTemp.get(i);
			User temp = new User(tempMyuser.getUserID(),tempMyuser.getName(),tempMyuser.getPhone(),tempMyuser.getEmail(),
					tempMyuser.getUserRole(),tempMyuser.getPassword(),tempMyuser.getUserState(),
					tempMyuser.getUserLogPath(),tempMyuser.getSummary());
			this.userList.add(userTemp.get(i));
		}
	}
	
	public Vector getUserList()
	{
		return this.userList;
	}
	
	public int addUser(User temp)
	{
		this.userList.add(temp);
		return 1;
	}
	
	public int deleteUser(int userID)
	{
		int i;
		for(i=0;i<this.userList.size();i++)
		{
			int tempUserID = ((User)this.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				this.userList.remove(i);
				return 1;
			}
		}
		return 0;
	}
	
	public int startUser(int userID)
	{
		int i;
		for(i=0;i<this.userList.size();i++)
		{
			int tempUserID = ((User)this.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)this.userList.get(i)).setUserState(1);
				return 1;
			}
		}
		return 0;
	}
	
	public int pauseUser(int userID)
	{
		int i;
		for(i=0;i<this.userList.size();i++)
		{
			int tempUserID = ((User)this.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)this.userList.get(i)).setUserState(0);
				return 1;
			}
		}
		return 0;
	}
	
	public int editUser(int userID,String name,String phone,String email,
			String userRole,String password,int userState)
	{
		int i;
		for(i=0;i<this.userList.size();i++)
		{
			int tempUserID = ((User)this.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)this.userList.get(i)).setUserInformation(name, phone, email, userRole, password, userState);
				return 1;
			}
		}
		return 0;
	}
	
	public int searchUserLog(String logPath)
	{
		//通过路径，直接读取数据库。
		return 1;
	}
	
	
}
