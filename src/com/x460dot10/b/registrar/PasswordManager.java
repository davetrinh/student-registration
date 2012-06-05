package com.x460dot10.b.registrar;


import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

/**
 * Stores all login information (username, password, and associated student
 * ID) for the University. Is in charge of creating new Password records,
 * storing existing Password records while the program is running, and 
 * checking that these records correspond with the set of students in the 
 * University. 
 * 
 * @author Katie Gustafson
 */

public class PasswordManager implements Observer {

     /**
      * Implementation of Observer pattern, ensuring that all Managers know
      * changes to <code>SystemStatus</code> of <code>University</code>
      * 
      * @author Alexandros Bantis
      */
     private SystemStatus systemStatus;

     private static PasswordManager pwdmanager = null;
	private ArrayList<Password> passwords = new ArrayList<Password>();
	private final int PASSWORD_MIN_LENGTH = 5;
	private University uni;
	
/**
 * Constructor for singleton of PasswordManager, which is called only by
 * PasswordManager.getInstance(). To instantiate a PasswordManager object,
 * <code>PasswordManager pm;
 * 	pm.getInstance();</code>
 */
	protected PasswordManager() 
	{
	//	uni = University.getInstance();
	}

	public static PasswordManager getInstance()
	{
		if (pwdmanager == null)
		{
			pwdmanager = new PasswordManager(); 
		}
		return pwdmanager;
	}
	
	
	/**
	 * Deletes any password records in the class-level collection of passwords
	 * for which there is no corresponding student record, in the 
	 * University.students collection
	 */
	public void validateAllAccounts()
	{
		ArrayList<Password> pwdsToBeDeleted = new ArrayList<Password>();
		
		for (Password p : passwords)
		{
			if (!uni.hasStudent(p.getStudentID()))
			{
				//Add p to the list of passwords to be deleted. 
				//This avoids having an exception thrown when deleting from the 
				//collection while iterating over it.
				pwdsToBeDeleted.add(p);
			}
		}
		
		for (Password r : pwdsToBeDeleted)
		{
			passwords.remove(r);
		}		
		//... note that this doesn't take care of the case where there are students 
		//without passwords, which should be ok. There's an assumption that any 
		//student record is created only after the student has logged in to the
		//system for the first time.
	}
	
	
	/**
	 * Creates a record in the Password collection for a particular 
	 * Student, with a given username and password	
	 * 
	 * @param student	The Student for whom the username+password will be
	 * 					stored
	 * @param username	The chosen/assigned username
	 * @param password	The chosen/assigned password
	 * @return			<code>true</code> if the username+password record was
	 * 					created in the Password collection
	 */
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
	

	/**
	 * Given the entered username and password, returns the student ID 
	 * associated with that username+password pair.
	 * 
	 * @param username		The username entered by the user.
	 * @param password		Password entered by the user.
	 * @return studentID	The student ID of the user.
	 */
	public int login(String username, String password)
	{
		int studentID = -1;
		
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

	
	/**
	 * Checks that the length of the given password is at least as 
	 * long as the minimum length for passwords.
	 * 
	 * @param 	password	The string whose length is to be checked.
	 * @return 				<code>true</code> if the password is at least of
	 * 						minimum length		
	 */
	public Boolean isMinLength(String password)
	{
		return (password.length() >= PASSWORD_MIN_LENGTH);
	}
	
	
	/**
	 * Prints to the console each Password record, in the format that record 
	 * appears in the passwords.dat file.
	 * 
	 * @see	List of Password records, e.g., "username","password",studentID
	 */
	public void printAllPwdRecords()
	{
		for (Password p : passwords)
		{
			System.out.println(p.toCSV());
		}
	}
	
	/**
	 * Checks whether the given username is already in the Password 
	 * collection.
	 * 
	 * @param 	username	The username to be searched for
	 * @return				<code>true</code> if the username is already in 
	 * 						use
	 */
	private Boolean usernameIsUsed(String username)
	{
		for(Password pwd : passwords)
		{
			if (StringUtils.equals(username, pwd.getUsername()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the given Student has a username + 
	 * password in the Password collection.
	 * 
	 * @param 	stu	The Student for whom a "login" (i.e., 
	 * 				username + password) may or may not exist.
	 * @return		<code>true</code> if Student has a 				
	 * 				username + password record.
	 */
	
	private Boolean studentHasLogin(Student stu)
	{
		for (Password p : passwords)
		{
			if (p.getStudentID() == stu.getStudentId())
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Allows Initializer to populate passwords from <code>password.dat</code>
	 * but will ignore request if not <code>SystemStatus.STARTING_UP</code>
	 * 
	 * @author Alexandros Bantis
	 * @param passwords               password container from 
	 *                                <code>Initializer</code>
	 */
	public void importPasswords(ArrayList<Password> filePasswords)
	{
	     if (systemStatus != SystemStatus.STARTING_UP)
	          return;
	     	     
	     passwords.clear();
	     this.passwords.addAll(filePasswords);
	}
	/**
	 * Implements Observer pattern to stay synchronized with 
	 * <code>SystemStatus</code> of the <code>University</code>.
	 * 
	 * @author Alexandros Bantis
	 * @param status             possible values are:
	 *                           STARTING_UP = can import records from
	 *                                         <code>Initializer</code>
	 *                           RUNNING = program available for users.
	 *                                     to log in.
	 *                           SHUTTING_DOWN = system preparing to
	 *                                           shut down by saving data.
	 */
     @Override
     public void update(SystemStatus status)
     {
          systemStatus = status;
     }
	
     /**
      * Returns an ArrayList of <code>passwords</code> IF the program is
      * in a testing state.
      * 
      * @author Alexandros Bantis
      * @return                   If <code>!SystemStatus.TESTING</code> then
      *                           program returns an empty container, otherwise
      *                           returns <code>passwords</code>.
      */
     public ArrayList<Password> getAllPasswords()
     {
          if (systemStatus != SystemStatus.TESTING)
               return new ArrayList<Password>();
          else
               return passwords;
     }
     
     
     
     
}