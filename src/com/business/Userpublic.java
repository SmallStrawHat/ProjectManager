package com.business;

public class Userpublic {
	private int userID;
	private String name;
	private String phone;
	private String email;
	
	public Userpublic()
	{
		this.userID = -1;
		this.name = "";
		this.phone = "";
		this.email = "";
	}
	
	public Userpublic(int userID,String name,String phone,String email)
	{
		this.userID = userID;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public int getUserID()
	{
		return this.userID;
	}
	
	public int setUserPublicInformation(String name,String phone,String email)
	{
		this.name = name;
		this.phone = phone;
		this.email = email;
		return 1;
	}
	

}
