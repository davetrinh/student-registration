import java.util.*;

public class PasswordManager {

	private static final int USERNAME_PASSWORD_MIN_LENGTH = 5;
	
	
	//I think of this as, "createLogin", and passwords.txt as the file that
	//stores login info (rather than pwds specifically)
	public static Boolean createPassword(Student student, String username, String password)
	{
		//if the username is empty, or the pwd is, return false and don't
		//create the pwd in the data file
		if(username.length() < USERNAME_PASSWORD_MIN_LENGTH || password.length() < USERNAME_PASSWORD_MIN_LENGTH)
		{
			return false;
		}
          else if (student == null) //do we need to check that student is valid, more deeply than this check?
		{
			return false;
		}
          else
		{
			//create password in the data file - haven't coded this yet.
			return true;
		}
	}
	
	public static Boolean isMinLength(String password)
	{
		return (password.length() >= USERNAME_PASSWORD_MIN_LENGTH);
	}
	
	//asks for username, password, and returns the student ID if the u+p are valid
	public static int login(String username, String password)
	{
		return 1;
	}
	
	// this checks that only students in students.txt have passwords in passwords.txt,
	// and deletes any password records for which there is no corresponding
	// student record
	public static void validateAllAccounts()
	{
		//haven't coded this yet.
	}
}
