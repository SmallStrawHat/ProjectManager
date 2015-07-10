package com.business;

import java.util.Vector;

public class User extends Userpublic{
	private String userRole;
	private Vector contact;//save contacts list
	private String password;
	private int userState;
	private String logPath;
	private String summary;
	
	public User()
	{
		super();
		this.userRole = "";
		this.contact = new Vector(10,6);
		this.password = "";
		this.userState = -1;
		this.logPath = "";
		this.summary = "";
	}
	
	public User(int userID,String name,String phone,String email,
			String userRole,String password,int userState,String logPath,String summary)
	{
		super(userID,name,phone,email);
		this.userRole = userRole;
		this.contact = new Vector(10,6);
		this.password = password;
		this.userState = userState;
		this.logPath = logPath;
		this.summary = summary;
	}
	
	public String getUserRole()
	{
		return this.userRole;
	}
	
	public int setUserState(int sign)
	{
		this.userState = sign;
		return 1;
	}
	
	public int setUserInformation(String name,String phone,String email,
			String userRole,String password,int userState)
	{
		this.setUserPublicInformation(name, phone, email);
		this.userRole = userRole;
		this.password = password;
		this.userState = userState;
		return 1;
	}
	

}
