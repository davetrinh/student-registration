package com.x460dot10.b.registrar;

//implements Saveable (interface) 

/**
 * Container class for the login info (username, password) for a single student. 
 * 
 * @author Katie Gustafson
 */

public class Password {

	protected String m_password;
	protected String m_username;
	protected int m_studentID;
	


	public Password(String username, String password, int studentID)
	{
		this.m_password = password;
		this.m_username = username;
		this.m_studentID = studentID;
	}
	
	
	/**
	 * @return	a String containing the username, password and student ID, in CSV format
	 * 			and with username and password in double quotes
	 */
	public String toCSV()
	{
		return "\"" + m_username + "\",\"" + m_password +"\"," + m_studentID;
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
