package com.x460dot10.b.registrar;

//implements Saveable (interface) 

/**
 * Container class for the login info (username, password) for a single student. 
 * 
 * @author Katie Gustafson
 */

public class Password implements Cloneable {

	protected String m_password;
	protected String m_username;
	protected int m_studentID;
	/// a temporary object so that data can be imported from text files by
	/// the Initializer into <code>PasswordManager.passwords</code>
	public static Password staticPassword;


	public Password(String username, String password, int studentID)
	{
		this.m_password = password;
		this.m_username = username;
		this.m_studentID = studentID;
	}
	
     public static void staticInstance()
     {
          if (staticPassword== null)
               staticPassword = new Password("","",0);
          clearStaticInstance();
     }
     
     private static void clearStaticInstance()
     {
          staticPassword.m_studentID = 0;
          staticPassword.m_username = "";
          staticPassword.m_password = "";
     }
	
     /**
      * Sets the values of the static instance so that the text record
      * can be imported into University.students by the Initializer.

      * @author alexandros bantis
      * @param  studentID         integer student ID number
      * @param  firstName         student's first name
      * @param  lastName          student's last name
      * @param  dateOfBirth       string date of birth in MM-DD-YY format
      */
     public static Password getStaticInstance(int studentID, String userName, 
               String password)
     {
          staticInstance();
          staticPassword.m_studentID = studentID;
          staticPassword.m_username = userName;
          staticPassword.m_password = password;
          return staticPassword;
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

     @Override
     public int hashCode()
     {
          final int prime = 31;
          int result = 1;
          result = prime * result
                    + ((m_password == null) ? 0 : m_password.hashCode());
          result = prime * result + m_studentID;
          result = prime * result
                    + ((m_username == null) ? 0 : m_username.hashCode());
          return result;
     }

     @Override
     public boolean equals(Object obj)
     {
          if (this == obj)
               return true;
          if (obj == null)
               return false;
          if (getClass() != obj.getClass())
               return false;
          Password other = (Password) obj;
          if (m_password == null)
          {
               if (other.m_password != null)
                    return false;
          } else if (!m_password.equals(other.m_password))
               return false;
          if (m_studentID != other.m_studentID)
               return false;
          if (m_username == null)
          {
               if (other.m_username != null)
                    return false;
          } else if (!m_username.equals(other.m_username))
               return false;
          return true;
     }

     @Override
     public String toString()
     {
          return "Password [" +
          		m_studentID + "," +	m_username + "," + m_password + "]";
     }

     @Override
     public Object clone()
     {
          try
          {
               return super.clone();
          }
          catch (CloneNotSupportedException ex)
          {
               return null;
          }
     }
}
