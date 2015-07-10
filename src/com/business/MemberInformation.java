package com.business;

import java.util.Vector;
import com.database.*;

public class MemberInformation {
	private static Vector userList;
	private static int staffNum;
	
	

	public MemberInformation()
	{
		
	}
	
	public static void init()
	{
		MemberInformation.userList = new Vector(10,6);
		
		Vector userTemp = Myuser.searchAll();
		for(int i=0;i<userTemp.size();i++)
		{
			Myuser tempMyuser = (Myuser)userTemp.get(i);
			//System.out.println(tempMyuser.getUserRole());
			User temp = new User(tempMyuser.getUserID(),tempMyuser.getName(),tempMyuser.getPhone(),tempMyuser.getEmail(),
					tempMyuser.getUserRole(),tempMyuser.getPassword(),tempMyuser.getUserState(),
					tempMyuser.getUserLogPath(),tempMyuser.getSummary());
			//System.out.println(temp.getUserRole());
			MemberInformation.userList.add(temp);
		}
		//System.out.println("init database successfully!");
		MemberInformation.staffNum = userTemp.size();
	}
	
	public static Vector getUserList()
	{
		return MemberInformation.userList;
	}
	
	public int addUser(User temp)
	{
		MemberInformation.userList.add(temp);
		return 1;
	}
	
	public static int deleteUser(int userID)
	{
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				MemberInformation.userList.remove(i);
				return 1;
			}
		}
		return 0;
	}
	
	public static int startUser(int userID)
	{
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)MemberInformation.userList.get(i)).setUserState(1);
				return 1;
			}
		}
		return 0;
	}
	
	public static int pauseUser(int userID)
	{
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)MemberInformation.userList.get(i)).setUserState(0);
				return 1;
			}
		}
		return 0;
	}
	
	public static int editUser(int userID,String name,String phone,String email,
			String userRole,String password,int userState)
	{
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)MemberInformation.userList.get(i)).setUserInformation(name, phone, email, userRole, password, userState);
				return 1;
			}
		}
		return 0;
	}
	
	public static int searchUserLog(String logPath)
	{
		//通过路径，直接读取数据库。
		return 1;
	}
	
	/*public static void main(String arg[])
	{
		MemberInformation.init();
    	Vector user = MemberInformation.getUserList();
    	for(int i=0;i<user.size();i++)
    	{
    		User tempUser = (User)user.get(i);
    		if(tempUser.getUserRole() != null)
    		{
    			System.out.println(tempUser.getUserRole());
    		}
    	}
    		
    	
    	System.out.println(user.size());
	}
	*/
	
	
}
