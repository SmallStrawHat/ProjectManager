package com.business;

import java.util.Vector;

import com.database.*;

public class WorktimeInfomation {
	private static Vector WorktimeList;
	private static int ListNum;
	
	public WorktimeInfomation()
	{
		
	}
	
	public static Vector getWorktimeList()
	{
		return WorktimeInfomation.WorktimeList;
	}
	
	public static void init()
	{
		WorktimeInfomation.WorktimeList = new Vector(10,6);
		
		Vector WorktimeTemp = Worktime.searchAll();
		for(int i=0;i<1;i++)
		{
			Worktime tempWorktime = (Worktime)WorktimeTemp.get(i);
			Worktime temp = new Worktime(
					tempWorktime.getWorkdays(),
					tempWorktime.getWorkdayHours(),
					tempWorktime.getUserLog(),
					tempWorktime.getuserLogPath(),
					tempWorktime.getProblemLogPath());
			WorktimeInfomation.WorktimeList.add(temp);
		}
		WorktimeInfomation.ListNum = 1;
	}
	
}
