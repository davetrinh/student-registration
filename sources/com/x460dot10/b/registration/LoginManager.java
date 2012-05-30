
public class LoginManager {
	
	public static void displayLoginScreen()
	{
		System.out.println("Hello, welcome to the university!" + 
                             "Are you a new student? (y/n)");
		//wait for input from user
		//if y
			//runNewStudent()
		//elseif n
			//runCurrentStudent()
		//else reprompt for input
	}

	
	public static void runCurrentStudent()
	{
		//starts the process for a current student logging in
		SessionManager sm = new SessionManager();
	}

	
	public static void runNewStudent()
	{
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		
		Boolean studentAddedToUniversity = false;
		
		//starts the process for a new student creating an account and
		//starting a session
		//calls University.addStudent
		// ... need to prompt for the student's first and last name, and then
		// for username, password, and then call
		studentAddedToUniversity = University.addStudent(
               firstName, lastName, username, password);
		
		//if addStudent attempt was successful
          //prompt student to take next step
		//else
          //prompt student to re-enter info (this won't give a helpful
          //error message, just because it won't know why add attempt
          //wasn't successful
	}
	
}

