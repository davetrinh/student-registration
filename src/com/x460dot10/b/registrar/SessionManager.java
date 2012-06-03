package com.x460dot10.b.registrar;

import java.util.Iterator;
import java.util.Vector;

public class SessionManager {
	
    public static SessionManager getInstance()
    {
         if (sessionManagerSingelton == null)
        	 sessionManagerSingelton = new SessionManager();
         return sessionManagerSingelton;
    }
	
	public void displayAllCourses(Vector<Course> courses)
	{
		Iterator<Course> iter = courses.iterator();	    
	    Course element = null;

	    while (iter.hasNext()) {
	    	element = iter.next();
	    	element.printCourseInfo();
	    }	   
		
	}
	
	public void displayStudentCourses(String studentName, Vector<Course> courses)
	{
		System.out.println("Student " + studentName + "is registered for the following courses: ");
		Iterator<Course> iter = courses.iterator();	    
	    Course element = null;

	    while (iter.hasNext()) {
	    	element = iter.next();
	    	element.printCourseInfo();
	    }	   
	}
	public void displayWelcomeMessage()
	{
		System.out.println("Hello, Welcome to the University Registration System");
	}
	
	public void displayExitMessage()
	{
		System.out.println("Thanks for using the University Registration System. Good-bye!");
	}
	
	private static SessionManager sessionManagerSingelton;

}
