package com.business;

import java.util.Vector;

import com.database.*;

public class WorktimeInfomation {
	public static Vector systemsettinglist;
	public WorktimeInfomation()
	{		
	}
	
	public static void init()
	{
		WorktimeInfomation.systemsettinglist = new Vector();
		Vector temp=Worktime.searchAll();
		for(int i=0;i<temp.size();i++)
		{
			Worktime worktime=(Worktime)temp.get(i);
			Roleinformation info=new Roleinformation();
		//	System.out.println("是初始化的问题吗？？？？？");
			SystemSetting ss=new SystemSetting(worktime.getWorkdays(),worktime.getWorkdayHours(),worktime.getUserLog(),
					worktime.getuserLogPath(),worktime.getProblemLogPath(),info.allRole());
			WorktimeInfomation.systemsettinglist.add(ss);
		}
	}
	public static int addUserRole(String rolename,String power){
		Vector temp=new Vector();
		Vector tempset=new Vector();
		temp=WorktimeInfomation.getSystemsettinglist();
		Roleinformation r=new Roleinformation(rolename,power);
		for(int i=0;i<temp.size();i++)
		{
			
			SystemSetting ss=(SystemSetting)temp.get(i);
			Vector role=ss.getRolelist();
			role.add(r);
			ss.setRolelist(role);
			tempset.add(ss);
		}
		if(tempset!=null)
		{
			WorktimeInfomation.setSystemsettinglist(tempset);
			Role.insert(rolename, power);
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public static int deleteUserRole(String userrole){
		Role.deleteRole(userrole);
		/*WorktimeInfomation.init();
		return 1;*/
		Vector systemlist=WorktimeInfomation.getSystemsettinglist();
		Vector systemlistupdate=new Vector();
		for(int i=0;i<systemlist.size();i++)
		{
			SystemSetting system=(SystemSetting)systemlist.get(i);
			Vector rolelist=system.getRolelist();
			Vector list=system.getRolelist();
			for(int j=0;j<rolelist.size();j++)
			{
//				Roleinformation info=rolelist.get(j);
				if(((Roleinformation)rolelist.get(j)).getRolename().equals(userrole))
				{
					list.remove(rolelist.get(j));
				}
			}
			system.setRolelist(list);
			systemlistupdate.add(system);
		}
		if(systemlistupdate==null)
		{		
			
			return 0;
		}
		else
		{
			WorktimeInfomation.setSystemsettinglist(systemlistupdate);
			return 1;
		}
	}

	public static Vector getSystemsettinglist() {
		return systemsettinglist;
	}

	public static void setSystemsettinglist(Vector systemsettinglist) {
		WorktimeInfomation.systemsettinglist = systemsettinglist;
	}
	
}
