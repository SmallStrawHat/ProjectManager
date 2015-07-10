package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataContacts {
	
	private int contactsID;
	private String name;
	private String phone;
	private String email;
	
	public DataContacts()
	{
		this.contactsID = -1;
		this.name = "";
		this.phone = "";
		this.email = "";
	}
	
	public DataContacts(int contactsID,String name,String phone,String email)
	{
		this.contactsID = contactsID;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
