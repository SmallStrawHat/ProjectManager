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

		num = RoleInfomation.userRoleList.size();

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
	

	
}
