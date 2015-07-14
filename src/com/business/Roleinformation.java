package com.business;

import java.util.Vector;
import com.database.*;

public class Roleinformation {
	private  String rolename;
	private  String power;
	public Roleinformation (){}
	
	public Roleinformation (String rolename,String power){
		this.rolename=rolename;
		this.power=power;
	}
	public  Vector allRole(){
		Vector role=Role.searchAll();
		Vector temp=new Vector();
		for(int i=0;i<role.size();i++)
		{
			Roleinformation roleinfo=new Roleinformation(((Role)role.get(i)).getUserRole(),((Role)role.get(i)).getAuthorization());
			temp.add(roleinfo);
		}
		return temp;
	}
	/*public Roleinformation addUserRole(String userRole,String Authorization)
	{
		Roleinformation role=new Roleinformation();
		if(Role.insert(userRole,Authorization)==1)
		{
			role=new Roleinformation(userRole,Authorization);
		}

		return role;
	}*/
	public  String getRolename() {
		return rolename;
	}
	public  void setRolename(String rolename) {
		this .rolename = rolename;
	}
	public  String getPower() {
		return power;
	}
	public  void setPower(String power) {
		this .power = power;
	}
	
	/*public static void main(String arg[]){
		Roleinformation r=new Roleinformation();
		Vector t=new Vector();
		t=r.allRole();
		for(int i=0;i<t.size();i++)
		{
		Role role=(Role)t.get(i);
		System.out.println(role.getUserRole());
		}*/
		
	}
	

