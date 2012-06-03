package com.x460dot10.b.registrar;


public class Student {
	
	public Student (String firstName, String lastName, Integer studentId/*, Password m_passwordInfo*/) 
	{
		
		this.m_firstName = firstName;
		this.m_lastName = lastName;
		this.m_studentId = studentId;
		this.m_valid = true;
		//this.m_passwordInfo = m_passwordInfo;
		
	}
	
	public Boolean isValid()
	{
		return m_valid;
	}
	
	public Integer getStudentId()
	{
		return m_studentId;
	}
	public String getLastName()
	{
		return m_lastName;
	}
	public String getFirstName()
	{
		return m_firstName;
	}
	public String getFullName()
	{
		return m_firstName + " " + m_lastName;
	}
	
	protected String m_firstName;
	protected String m_lastName;
	protected Integer m_studentId;
	protected Boolean m_valid;
	//private Password m_passwordInfo;
	

}
