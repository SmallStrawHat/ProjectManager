package com.business;

import java.util.Vector;

import com.database.DataContacts;

public class Contacts {
	
	public Contacts()
	{
	}
	
	public static Vector serachContact(int userID)
	{
		return DataContacts.search(userID);
	}
	
	public static int addStaff(int userID,Vector userIDList)
	{
		Vector newContacts = new Vector(10,6);

		int i;
		for(i=0;i<userIDList.size();i++)
		{
			User temp = MemberInformation.seachUser(((Integer)userIDList.get(i)).intValue());
			if(temp != null)
			{
				System.out.println(((Integer)userIDList.get(i)).intValue());
				//int userID,String name,String phone,String email,String userRole
				Userpublic tempPublic = new Userpublic(temp.getUserID(),temp.getName(),temp.getPhone(),temp.getEmail(),temp.getUserRole());
				newContacts.add(tempPublic);
			}
		}
		if(DataContacts.add(userID, newContacts)==1)
		{
			return 1;
		}
		
		return 0;
	}
	
	public static int deleteStaff(int userID,int deleteID)
	{
		if(DataContacts.delete(userID, deleteID)==1)
		{
			return 1;
		}
		
		return 0;
	}
	
	
}
