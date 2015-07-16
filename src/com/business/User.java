package com.business;

import java.util.Vector;

public class User extends Userpublic{
	private Vector contact;//save contacts list
	private String password;
	private int userState;
	private String logPath;
	private String summary;
	
	public User()
	{
		super();
		this.contact = new Vector(10,6);
		this.password = "";
		this.userState = -1;
		this.logPath = "";
		this.summary = "";
	}
	
	/*
	 * User temp = new User(tempMyuser.getUserID(),tempMyuser.getName(),tempMyuser.getPhone(),tempMyuser.getEmail(),
					tempMyuser.getUserRole(),tempMyuser.getPassword(),tempMyuser.getUserState(),
					tempMyuser.getUserLogPath(),tempMyuser.getSummary());
	 * */
	
	public User(int userID,String name,String phone,String email,
			String userRole,String password,int userState,String logPath,String summary)
	{
		super(userID,name,phone,email,userRole);

		this.contact = new Vector(10,6);
		this.password = password;
		this.userState = userState;
		this.logPath = logPath;
		this.summary = summary;
	}
	
	public Vector getContact()
	{
		return this.contact;
	}
	
	public int addContacts(Userpublic userPub)
	{
		this.contact.add(userPub);
		return 1;
	}
	
	public int setUserState(int sign)
	{
		this.userState = sign;
		return 1;
	}
	
	public int getUserState()
	{
		return this.userState;
	}
	
	public int setUserInformation(String name,String phone,String email,
			String userRole,String password,int userState,String summary)
	{
		this.setUserPublicInformation(name, phone, email,userRole);

		this.password = password;
		this.userState = userState;
		this.summary = summary;
		return 1;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getSummary()
	{
		return this.summary;
	}
	
	public String getUserLogpath()
	{
		return this.logPath;
	}
	

}
