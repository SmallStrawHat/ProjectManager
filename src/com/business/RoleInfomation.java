package com.business;

import java.util.Vector;
import com.database.*;

public class RoleInfomation {
	private static Vector userRoleList;
	private static int roleNum;
	
	

	public RoleInfomation()
	{
		
	}
	
	public static void init()
	{
		RoleInfomation.userRoleList = new Vector(10,6);
		
		Vector userTemp = Role.searchAll();
		for(int i=0;i<userTemp.size();i++)
		{
			Role tempRole = (Role)userTemp.get(i);
			//System.out.println(tempMyuser.getUserRole());
			Role temp = new Role(tempRole.getUserRole(),tempRole.getAuthorization());
			//System.out.println(temp.getUserRole());
			RoleInfomation.userRoleList.add(temp);
		}
		//System.out.println("init database successfully!");
		RoleInfomation.roleNum = userTemp.size();
	}
	
	public static Vector getUserList()
	{
		return RoleInfomation.userRoleList;
	}
	
	public static int addUserRole(String userRole,String Authorization)
	{
		if(Role.insert(userRole,Authorization)!=-1)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public static int deleteUserRole(String userRole)
	{
		int i,num;

//		num = RoleInfomation.userRoleList.size();
		num = 9;
		for(i=0;i<num;i++)
		{
			String tempRole = ((Role)RoleInfomation.userRoleList.get(i)).getUserRole();
			
			if(tempRole.equals(userRole) == true)
			{
				if(Role.deleteRole(userRole)==1)
				{
					RoleInfomation.userRoleList.remove(i);
					return 1;
				}
				else
				{
					return 0;
				}
			}
		}
		return 0;
	}
	
	public static int startUser(int userID)
	{
		int i;
		for(i=0;i<RoleInfomation.userRoleList.size();i++)
		{
			int tempUserID = ((User)RoleInfomation.userRoleList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				if(Myuser.update(userID,1)==1)
				{
					((User)RoleInfomation.userRoleList.get(i)).setUserState(1);
					return 1;
				}
				
			}
		}
		return 0;
	}
	
	public static int pauseUser(int userID)
	{
		int i;
		for(i=0;i<RoleInfomation.userRoleList.size();i++)
		{
			int tempUserID = ((User)RoleInfomation.userRoleList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				if(Myuser.update(userID,0)==1)
				{
					((User)RoleInfomation.userRoleList.get(i)).setUserState(0);
					return 1;
				}
				
			}
		}
		return 0;
	}
	
	public static User seachUser(int userID)
	{
		User res = null;
		int i;
		for(i=0;i<RoleInfomation.userRoleList.size();i++)
		{
			int tempUserID = ((User)RoleInfomation.userRoleList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				return (User)RoleInfomation.userRoleList.get(i);
			}
			
		}
		return res;
	}
	
	public static int editUser(int userID,String name,String phone,String email,
			String userRole,String password,int userState)
	{
		int i;
		for(i=0;i<RoleInfomation.userRoleList.size();i++)
		{
			int tempUserID = ((User)RoleInfomation.userRoleList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				((User)RoleInfomation.userRoleList.get(i)).setUserInformation(name, phone, email, userRole, password, userState);
				return 1;
			}
		}
		return 0;
	}
	
	public static int searchUserLog(String logPath)
	{
		//ͨ��·����ֱ�Ӷ�ȡ���ݿ⡣
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
