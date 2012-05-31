import java.util.Scanner;

public class LoginManager {
    
    private static LoginManager loginmanager = null;
    

    public static LoginManager getInstance()
    {
    	if(loginmanager == null)
    	{
    		loginmanager = new LoginManager();
    	}
    	return loginmanager;
    }
    
    
    protected LoginManager()
    {
    	displayLoginScreen();
    }

    
	private static void displayLoginScreen()
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
				runNewStudent();
				break;
			}else if (userAnswer.equalsIgnoreCase(NO_ANSWER))
			{
				runCurrentStudent();
				break;
			}
		}
	}

	
	private static void runCurrentStudent()
	{
		//starts the process for a current student logging in
		SessionManager sm = new SessionManager();
	}

	
	private static void runNewStudent()
	{		
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		
		Boolean studentAddedToUniversity = false;
		Boolean studentAddAttemptMade = false;
		
		while (!studentAddedToUniversity)
		{	
			firstName = getItemOfUserInput("First name"); 
			lastName = getItemOfUserInput("Last name");
			username = getItemOfUserInput("Username");
			password = getItemOfUserInput("Password");
					
			studentAddedToUniversity = University.addStudent(firstName, lastName, username, password);
			
			if (!studentAddedToUniversity)
			{
				System.out.println("There was a problem adding your student record. Please enter your information again.");
			}
		}
		
		runCurrentStudent();
	}

	
	private static String getItemOfUserInput(String itemTitle)
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
				if (PasswordManager.isMinLength(userInput))
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