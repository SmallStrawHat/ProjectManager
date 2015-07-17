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
	
	public static int addUser(String name,String phone,String email,
			String userRole,String password,int userState,String summary)
	{
		String  userlogpath=new String();
		for(int i=0;i<WorktimeInfomation.systemsettinglist.size();i++)
		{
			SystemSetting temp=(SystemSetting)WorktimeInfomation.systemsettinglist.get(i);    
			userlogpath=temp.getUserlogpath();
		}
		userlogpath=userlogpath.replaceAll("\\\\", "\\\\\\\\");
		int userid=Myuser.insert(name, phone, email, userRole, password, userState,userlogpath, summary);
		
		if(userid!=-1)
		{
			userlogpath=userlogpath+userid+".txt";
			User temp = new User(userid,name, phone, email, userRole, password, userState,userlogpath, summary);
			MemberInformation.userList.add(temp);
			return userid;
		}
		else
		{
			return 0;
		}
	}
	
	public static int deleteUser(int userID)
	{
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				if(Myuser.delete(userID)==1)
				{
					MemberInformation.userList.remove(i);
					return 1;
				}
				else
				{
					System.out.println("date fail!");
					return 0;
				}
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
				if(Myuser.update(userID,1)==1)
				{
					((User)MemberInformation.userList.get(i)).setUserState(1);
					return 1;
				}
				
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
				if(Myuser.update(userID,0)==1)
				{
					((User)MemberInformation.userList.get(i)).setUserState(0);
					return 1;
				}
				
			}
		}
		return 0;
	}
	
	public static User seachUser(int userID)
	{
		User res = null;
	//	int i;
		for(int i=0;i<MemberInformation.userList.size();i++)
		{
			int tempUserID = ((User)MemberInformation.userList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				return (User)MemberInformation.userList.get(i);
			}
			
		}
		
		return res;
	}
	
	public static Vector seachUserToRole(String role)
	{
		Vector res = new Vector(10,6);
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			String tempUserRole = ((User)MemberInformation.userList.get(i)).getUserRole();
			if(tempUserRole.equals(role))
			{
				res.add((User)MemberInformation.userList.get(i));
			}
			
		}
		return res;
	}
	
	public static int editUser(int userID,String name,String phone,String email,
			String userRole,String password,int userState,String summary)
	{
		
		int i;
		for(i=0;i<MemberInformation.userList.size();i++)
		{
			User searchUser = ((User)MemberInformation.userList.get(i));
			if(searchUser.getUserID() == userID)
			{
				searchUser.setUserInformation(name, phone, email, userRole, password, userState,summary);
				if(Myuser.updateAll(userID, searchUser)==1)
				{
					return 1;
				}
				return 0;
			}
		}
		
		return 0;
		
		
	}
	
	
	public static int searchUserLog(String logPath)
	{
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
