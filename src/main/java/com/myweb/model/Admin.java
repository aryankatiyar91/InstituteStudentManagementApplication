package com.myweb.model;

import javax.persistence.*;

//Bean class/Encapsulated class
@Entity
public class Admin 
{
	// Vaiables for bean class
	@Id  // declaring primary key for particular
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment property
	private int id;
	private String username,password;
	@OneToOne(cascade = CascadeType.ALL)
	private Admin a;
	
	// constructors for bean class
	public Admin() 
	{
		super();
	}
	public Admin(String username, String password) 
	{
		super();
		this.username = username;
		this.password = password;
	}
	
	
	// getters and setters for private variables
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
}