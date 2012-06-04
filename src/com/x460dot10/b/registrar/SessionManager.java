package com.x460dot10.b.registrar;

import java.util.Iterator;
import java.util.Vector;
import java.util.Scanner;
import java.io.IOException;

public class SessionManager {
	
    public static SessionManager getInstance()
    {
         if (sessionManagerSingelton == null)
        	 sessionManagerSingelton = new SessionManager();
         return sessionManagerSingelton;
    }
	/*
    // Displays all the courses information
	public void displayAllCourses()
	{
		Iterator<Course> iter = m_allCourses.iterator();	    
	    Course element = null;

	    while (iter.hasNext()) {
	    	element = iter.next();
	    	element.printCourseInfo();
	    }	   
		
	}
	*/
	//Initializes the current session for the current user
	// params
	// studentName: Current student's full name
	// id:          Current student's id
	// allCourses:  Vector of all the courses
	// myCourses:   Vector of the student's courses
	public void initialize(String studentName, Integer id)
	{
		this.m_studentName = studentName;
		this.m_studentId = id;
		loginManagerSingleton.getInstance();
		//registrationManagerSingleton.getInstance();
		initializerSingleton.getInstance();
		
	}
	/*
	//Displays all the current Student's registered courses
	public void displayStudentCourses()
	{
		System.out.println("Student " + m_studentName + "is registered for the following courses: ");
		Iterator<Course> iter = m_studentCourses.iterator();	    
	    Course element = null;
	    //Iterate through the whole vector and display the course information
	    while (iter.hasNext()) {
	    	element = iter.next();
	    	element.printCourseInfo();
	    }	   
	}
	*/
	//Display the welcome message screen
	public void displayWelcomeMessage()
	{
		Integer selection = 0;
		String temp;
		Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.
		
		System.out.println("Hello, Welcome to the University Registration System");
		while(selection != 1 || selection != 2)
		{
			System.out.println("Please choose from the following choices:");
			System.out.println("1. Logon");
			System.out.println("2. Create New Account");
			temp=input.next(); // Get what the user types.
			try {
			    // convert the string to int
				selection = Integer.valueOf(temp);
			} catch (Exception ex) {
			    // Print out the exception that occurred
				System.out.println("Invalid selection! Please try again...");
			}
			System.out.println("User Entered: " + selection);
			if(selection == 0 || selection > 2 )
			{
				System.out.println("Invalid selection! Please try again...");
			}
		}
		
	}
	// Display's interface to register for a course
	public void registerForCourse()
	{
		Boolean success = false;
		Integer selection = 0;
		String temp;
		Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.
		while(success != true)
		{ 
			System.out.println("Please enter in the Course id for the class you wish to register for: ");
			temp=input.next(); // Get what the user types.
			try {
			    // convert the string to int
				selection = Integer.valueOf(temp);
			} catch (Exception ex) {
			    // Print out the exception that occurred
				System.out.println("Invalid selection! Please try again...");
			}
			success = registrationManagerSingleton.addtoSchedule(m_studentId, selection);
			if (success)
			{
				System.out.println("You have successfully registered for course: #" + selection);
			}
			else
			{
				System.out.println("Sorry, the registration system cannot add course # " + selection + "to your schedule");
			}
		}
		
	}
	// Display's interface to unregister for a course
	public void unregisterForCourse()
	{
		Boolean success = false;
		Integer selection = 0;
		String temp;
		Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.
		while(success != true)
		{ 
			System.out.println("Please enter in the Course id for the class you wish to unregister for: ");
			temp=input.next(); // Get what the user types.
			try {
			    // convert the string to int
				selection = Integer.valueOf(temp);
			} catch (Exception ex) {
			    // Print out the exception that occurred
				System.out.println("Invalid selection! Please try again...");
			}
			success = registrationManagerSingleton.dropFromSchedule(selection);
			if (success)
			{
				System.out.println("You have successfully unregistered for course: #" + selection);
			}
			else
			{
				System.out.println("Sorry, the registration system cannot drop course # " + selection + "to your schedule");
			}
		}
		
	}
	// Display's interface for the main menu
	public void displayMainMenu()
	{
		Boolean success = false;
		Integer selection = 0;
		String temp;
		Scanner input=new Scanner(System.in); // Decl. & init. a Scanner.
		while(!success)
		{
			System.out.println("Please choose from the following choices:");
			System.out.println("1. View All Courses");
			System.out.println("2. View My Courses");
			System.out.println("3. Register for Course");
			System.out.println("4. UnRegister for Course");
			System.out.println("5. Exit");
			temp=input.next(); // Get what the user types.
			try {
			    // convert the string to int
				selection = Integer.valueOf(temp);
			} catch (Exception ex) {
			    // Print out the exception that occurred
				System.out.println("Invalid selection! Please try again...");
			}
			System.out.println("User Entered: " + selection);
			if(selection == 0 || selection > 5 )
			{
				System.out.println("Invalid selection! Please try again...");
				success = false;
			}
			else
			{
				success = true;
			}
		}
		
		switch(selection){
		case 1: 
			registrationManagerSingleton.showAllCourses();
			break;
		case 2: 
			registrationManagerSingleton.showMyCourses(m_studentId);
			break;
		case 3: 
			registerForCourse();
			break;
		case 4:
			unregisterForCourse();
			break;
		case 5:
			displayExitMessage();
			initializerSingleton.shutDown();
			break;
		}
		
	}
	// Display's interface to register for a course
	public void displayExitMessage()
	{
		System.out.println("Thanks for using the University Registration System. Good-bye!");
	}
	
	private static SessionManager sessionManagerSingelton;
	private static LoginManager  loginManagerSingleton;
	private static RegistrationManager registrationManagerSingleton;
	private static Initializer initializerSingleton;
	protected String m_studentName;
	protected Integer m_studentId;
	

}
