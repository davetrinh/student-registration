package com.x460dot10.b.registrar;

import java.util.Scanner;


/**
 * Provides the set of prompts for a user to create a new student account
 * and to be verified as a student in the university. This verification
 * allows the student to start a session in the system and use the 
 * system to make changes to his/her course schedule.
 * 
 * @author Katie Gustafson
 */
public class LoginManager {
	
	//private University uni;
	//private PasswordManager pwdmgr;
    private static LoginManager loginmgr;
    
 
    /**
     * Constructor for singleton of LoginManager, which is called only by
     * LoginManager.getInstance(). To instantiate a LoginManager object,
     * <code>LoginManager lm = LoginManager.getInstance();</code>
     */
    private LoginManager()
    {
	//	uni = University.getInstance();
	//	pwdmgr = PasswordManager.getInstance();
	}
    
    
    public static LoginManager getInstance()
    {
    	if(loginmgr == null)
    	{
    		loginmgr = new LoginManager();
    	}
    	return loginmgr;
    }
    

    /**
     * Directs new students and existing students to different sets of console
     * prompts.
     * (This is kind of a confusing method name)
     */
	public void displayLoginScreen()
	{
		String userAnswer;
		final String YES_ANSWER = "y";
		final String NO_ANSWER = "n";
		Scanner keyboardInput = new Scanner(System.in);
		
		System.out.print("Hello, welcome to the university! ");
			
		while (true)
		{
			System.out.print("Are you a new student? (y/n):");
			userAnswer = keyboardInput.next();

			if (userAnswer.equalsIgnoreCase(YES_ANSWER))
			{
				this.runNewStudent();
				break;
			}else if (userAnswer.equalsIgnoreCase(NO_ANSWER))
			{
				int currentStudentID = promptStudentLogin();
				this.runCurrentStudent(currentStudentID);
				break;
			}
		}
	}


	/**
	 * Prompts user for student username and password, and uses those two
	 * parameters to get the ID of the student logging in.
	 * 
	 * @return	student ID of the student who logged in
	 */
	private int promptStudentLogin()
	{
		int studentID;
		
		String username; 
		String password;
		
		Boolean loginAttempted = false;
		
		do
		{
			if (loginAttempted)
			{
				System.out.println("That username and password do not match a student record in " +
						"the system. Please try again.");
			}
			
			username = this.getItemOfUserInput("Username");
			password = this.getItemOfUserInput("Password");
//			studentID = pwdmgr.login(username, password);
		
			loginAttempted = true;
		} while (false/*!uni.hasStudent(studentID)*/);
	
		return -1; //		return studentID;
	}
	

	/**
	 * Once the student is logged in, the system passes the
	 * student to the session manager so s/he can use her online
	 * account.
	 * 
	 * @param studentID
	 */
	private void runCurrentStudent(int studentID)
	{
		String studentName = "";
		
		//pretty much need a University.getStudent(studentID) method so
		//I can either provide sessionManager.INitialize with student name,
		//or SessionManager can get student name from University by using
		//University.getStudent(studentID)
		
		SessionManager sm = SessionManager.getInstance();
		sm.initialize(studentName, studentID); //!! studentName is blank right now
		
		//might want to pass the student ID to displayMainMenu()
		//unless we end up using a student-like singleton object
		//to store the student 
		sm.displayMainMenu();
	}


	/**
	 * Gets the new student's information (e.g., name, chosen username, 
	 * password) and adds the new student's record to the University.
	 * Then logs the new student into the system.
	 */
	private void runNewStudent()
	{		
//		String firstName = "";
//		String lastName = "";
//		String username = "";
//		String password = "";
//		int studentID;
//		
//		Boolean studentAddedToUniversity = false;
//		
//		while (!studentAddedToUniversity)
//		{	
//			firstName = this.getItemOfNewStudentInfo("First name"); 
//			lastName = this.getItemOfNewStudentInfo("Last name");
//			username = this.getItemOfNewStudentInfo("Username");
//			password = this.getItemOfNewStudentInfo("Password");
//					
//			studentAddedToUniversity = uni.addStudent(firstName, lastName, username, password);
//			
//			if (!studentAddedToUniversity)
//			{
//				System.out.println("There was a problem adding your student record. " +
//									"Please enter your information again.");
//			}
//		}
//		
//		studentID = pwdmgr.login(username, password);
//		runCurrentStudent(studentID);
	}

	
	/**
	 * Prompts user through the console for a particular item of input used for
	 * creating new student accounts, and checks and returns the user's 
	 * response.
	 * In the case of a password input, this requires that the password be
	 * of minimum length.
	 * 
	 * @param itemTitle	The title that will appear in the prompt, e.g., 
	 * 					"First name" or "Username" or "Password"
	 * @return			The user's response to the prompt
	 */
	private String getItemOfNewStudentInfo(String itemTitle)
	{
		final String ITEM_TITLE_PASSWORD = "password";
		
		String userInput;
		String itemValue = "";
		
		do
		{
			userInput = getItemOfUserInput(itemTitle);
			
			if (itemTitle.equalsIgnoreCase(ITEM_TITLE_PASSWORD))
			{
				if (false)//(pwdmgr.isMinLength(userInput))
				{
					itemValue = userInput;
				}else
				{
					System.out.println("The password you chose is too short. Please try again.");
				}
			}
			else
			{
				itemValue = userInput;
			}
		} while(itemValue.length() < 1);
		
		return itemValue;
	}
	
	
	/**
	 * Prompts user through the console for a particular item of input, and 
	 * returns the user's response.
	 * 
	 * @param itemTitle	The title that will appear in the prompt, e.g., 
	 * 					"First name" or "Username" or "Password"
	 * @return			The user's response to the prompt
	 */
	private String getItemOfUserInput(String itemTitle)
	{
		Scanner keyboardInput = new Scanner(System.in);
		
		String itemValue = "";
		String userInput = "";
		
		while (itemValue.length() < 1)
		{
			System.out.print(itemTitle + ": ");
			userInput = keyboardInput.next();
	
			if (userInput.length() > 0)
			{
				itemValue = userInput;
			}
		}
		
		return itemValue;
	}
	
}