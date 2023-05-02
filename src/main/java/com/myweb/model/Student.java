package com.myweb.model;

import javax.persistence.*;

// Bean class/Encapsulated class
@Entity
public class Student 
{
	// Vaiables for bean class
	@Id  // declaring primary key for particular
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment property
	private int sno;
	private String name,email,password;
	private long mobile;
	
	// constructors for bean class
	public Student() 
	{
		super();
	}
	public Student(String name, String email, String password, long mobile) 
	{
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	public Student(int sno, String name, String email, String password, long mobile) 
	{
		super();
		this.sno = sno;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}
	
	
	// getters and setters for private variables
	public int getSno() 
	{
		return sno;
	}
	public void setSno(int sno) 
	{
		this.sno = sno;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public long getMobile() 
	{
		return mobile;
	}
	public void setMobile(long mobile) 
	{
		this.mobile = mobile;
	}
}
