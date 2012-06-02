package com.x460dot10.b.registration;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;


public class PasswordManager {

	private static PasswordManager pwdmanager = null;
	private ArrayList<Password> passwords = new ArrayList<Password>();
	
	private University uni;
	
	private final int PASSWORD_MIN_LENGTH = 5;

	
	protected PasswordManager() 
	{
		uni = University.getInstance();
	}

	
	public static PasswordManager getInstance()
	{
		if (pwdmanager == null)
		{
			pwdmanager = new PasswordManager(); 
		}
		return pwdmanager;
	}
	
	
	public Boolean createPassword(Student student, String username, String password)
	{
		Password newPwd;
		
		if (student == null)
		{
			return false;
		}
		
		if(username.length() < 1 || password.length() < PASSWORD_MIN_LENGTH)
		{
			return false;
		}
		
		if (usernameIsUsed(username))
		{
			return false;
		}

		if (studentHasLogin(student))
        {
        	//overwrite old username + pwd in the passwords collection.this runs through the entire collection
			int pwdIdx = -1;
			
			for (Password pwd : passwords)
			{
        		if (pwd.getStudentID() == student.getStudentId()) 
        		{
        			pwdIdx = passwords.indexOf(pwd);
        		}
        	}
			
			if (pwdIdx >= 0)
			{
    			passwords.remove(pwdIdx);
			}
        }
        	//create a Password object and add it to the collection
            //assumption is that when the program closes, this new pwd will be added to the
            //passwords data file
            newPwd = new Password(username, password, student.getStudentId());  
            passwords.add(newPwd);
      		return true; 
	}
	
	
	private Boolean usernameIsUsed(String uname)
	{
		for(Password pwd : passwords)
		{
			if (StringUtils.equals(uname, pwd.getUsername()))
			{
				return true;
			}
		}
		return false;
	}

	
	private Boolean studentHasLogin(Student stu)
	{
		//for each pwd record
			//if the studentID is in there
				//return true
		for (Password p : passwords)
		{
			if (p.getStudentID() == stu.getStudentId())
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	public Boolean isMinLength(String password)
	{
		return (password.length() >= PASSWORD_MIN_LENGTH);
	}
	
	
	//asks for username, password, and returns the student ID if the u+p are valid
	public int login(String username, String password)
	{
		int studentID = -1;
		
		//find the username+pwd in the passwords collection
		//if the login info is in the collection
			//return the associated studentID
		
		//for each item in the arraylist
			//if it has the matching username and password
				//return the studentID
		for (Password pwd : passwords)
		{
			if (StringUtils.equals(username, pwd.getUsername()) && 
					StringUtils.equals(password,  pwd.getPassword()))
			{
				studentID = pwd.getStudentID();
				break;
			}
		}

		return studentID;
	}
	
	
	// this deletes any password records for which there is no corresponding student record
	public void validateAllAccounts()
	{
		ArrayList<Password> pwdsToBeDeleted = new ArrayList<Password>();
		
		for (Password p : passwords)
		{
			if (!uni.hasStudent(p.getStudentID()))
			{
				//add p to the list of pwds to be deleted. 
				//Doing this to avoid exception thrown when deleting while iterating
				pwdsToBeDeleted.add(p);
			}
		}
		
		for (Password r : pwdsToBeDeleted)
		{
			passwords.remove(r);
		}		
		//... note that this doesn't take care of the case where there are students without passwords, which should be ok
	}


	//used for testing
	public void printAllPwdRecords()
	{
		for (Password p : passwords)
		{
			System.out.println(p.toCSV());
		}
	}
	
}

