package com.x460dot10.b.registrar;


import java.util.Scanner;

public class LoginManager {
	
	private University uni;
	private PasswordManager pwdmgr;
    private static LoginManager loginmgr;
    
 
    public static LoginManager getInstance()
    {
    	if(loginmgr == null)
    	{
    		loginmgr = new LoginManager();
    	}
    	return loginmgr;
    }
    
    
    private LoginManager()
    {
		uni = University.getInstance();
		pwdmgr = PasswordManager.getInstance();
		
		this.displayLoginScreen();    
	}
 

	private void displayLoginScreen()
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
				this.runCurrentStudent();
				break;
			}
		}
	}

	
	private void runCurrentStudent()
	{
		//starts the process for a current student logging in
		//ssionManager sm = new SessionManager();
	}

	
	private void runNewStudent()
	{		
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		
		Boolean studentAddedToUniversity = false;
		
		while (!studentAddedToUniversity)
		{	
			firstName = this.getItemOfUserInput("First name"); 
			lastName = this.getItemOfUserInput("Last name");
			username = this.getItemOfUserInput("Username");
			password = this.getItemOfUserInput("Password");
					
			studentAddedToUniversity = uni.addStudent(firstName, lastName, username, password);
			
			if (!studentAddedToUniversity)
			{
				System.out.println("There was a problem adding your student record. Please enter your information again.");
			}
		}
		
		runCurrentStudent();
	}

	
	private String getItemOfUserInput(String itemTitle)
	{
		final String ITEM_TITLE_PASSWORD = "password";
		Scanner keyboardInput = new Scanner(System.in);
		
		String itemValue = "";
		String userInput = "";
		
		while (itemValue.length() < 1)
		{
			System.out.print(itemTitle + ": ");
			userInput = keyboardInput.next();
	
			if (itemTitle.equalsIgnoreCase(ITEM_TITLE_PASSWORD))
			{
				if (pwdmgr.isMinLength(userInput))
				{
					itemValue = userInput;
				}else
				{
					System.out.println("The password you chose is too short.");
				}
			}
			else if (userInput.length() > 0)
			{
				itemValue = userInput;
			}
		}
		
		return itemValue;
	}
	
}