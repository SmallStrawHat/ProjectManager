package com.business;

public class Userpublic {
	private int userID;
	private String name;
	private String phone;
	private String email;
	private String userRole;
	
	public Userpublic()
	{
		this.userID = -1;
		this.name = "";
		this.phone = "";
		this.email = "";
		this.userRole = "";
	}
	
	public Userpublic(int userID,String name,String phone,String email,String userRole)
	{
		this.userID = userID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userRole = userRole;
	}
	
	public int getUserID()
	{
		return this.userID;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPhone()
	{
		return this.phone;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public String getUserRole()
	{
		return this.userRole;
	}
	
	public int setUserPublicInformation(String name,String phone,String email,String userRole)
	{
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userRole = userRole;
		return 1;
	}
	

}
