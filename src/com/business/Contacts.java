package com.business;

import java.util.Vector;

public class Contacts {
	private Vector staffList;//save userpublic object
	
	public Contacts()
	{
		this.staffList = new Vector(10,6);
	}
	
	public static int addStaff(int userID,Vector userIDList)
	{
		MemberInformation.init();
		//staffList.add(staff);
		return 1;
	}
	
	public int deleteStaff(int userID)
	{
		int i;
		for(i=0;i<this.staffList.size();i++)
		{
			int tempUserID = ((Userpublic)this.staffList.get(i)).getUserID();
			if(tempUserID == userID)
			{
				this.staffList.remove(i);
				return 1;
			}
		}
		return 0;
	}
	
	
}
