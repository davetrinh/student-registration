package com.x460dot10.b.registration;

//implements Saveable (interface) 

public class Password {

	private String m_password;
	private String m_username;
	private int m_studentID;
	
	public Password(String username, String password, int studentID)
	{
		this.m_password = password;
		this.m_username = username;
		this.m_studentID = studentID;
	}
	
	
	public String toCSV()
	{
		return m_username + "," + m_password +"," + m_studentID;
	}
	
	
	public String getUsername()
	{
		return m_username;
	}
	
	
	public String getPassword()
	{
		return m_password;
	}
	
	
	public int getStudentID()
	{
		return m_studentID;
	}
	
}
