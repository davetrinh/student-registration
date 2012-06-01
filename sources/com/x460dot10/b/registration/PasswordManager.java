import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

//I want to import org.apache...StringUtils, is assoc w/this project, but is throwing an error when I attempt it. 


public class PasswordManager {

	private final int PASSWORD_MIN_LENGTH = 5;
	private ArrayList<Password> passwords = new ArrayList<Password>;
	
	private static PasswordManager passwordmanager = null;
	
	
	private PasswordManager() {}

	
	public static PasswordManager getInstance()
	{
		if (passwordmanager == null)
		{
			passwordmanager = new PasswordManager(); 
		}
		return passwordmanager;
	}
	
	
	public Boolean createPassword(Student student, String username, String password)
	{
		if(username.length() < 1 || password.length() < PASSWORD_MIN_LENGTH)
		{
			return false;
		}
          else if (!student.isValid() or usernameIsUsed()) 
		{
			return false;
		}
          else
		{
			//create password in the data file?
        	 //or, add a password to the collection, I think is what this means.
        	passwords.
			return true;
		}
	}
	
	
	private Boolean usernameIsUsed()
	{
		//if the username is in the passwords collection already 
			//return true 
		return true;
	}
	
	public Boolean isMinLength(String password)
	{
		return (password.length() >= PASSWORD_MIN_LENGTH);
	}
	
	
	//asks for username, password, and returns the student ID if the u+p are valid
	public int login(String username, String password)
	{
		int studentID = -1;
		
		//find the username in the passwords file
		//if the password matches what's given
			//return the associated studentID

		passwords.
		
		
		return studentID;
	}
	
	
	// this checks that only students in students.txt have passwords in passwords.txt,
	// and deletes any password records for which there is no corresponding
	// student record
	public void validateAllAccounts()
	{
		//haven't coded this yet.
	}
}
