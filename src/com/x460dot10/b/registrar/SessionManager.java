package com.x460dot10.b.registrar;
import java.util.Iterator;
import java.util.Vector;


public class SessionManager {
  
	public void displayAllCourses(Vector<Course> courses)
	{
		Iterator<Course> iter = courses.iterator();	    
	    Course element = null;

	    while (iter.hasNext()) {
	    	element = iter.next();
	    	element.printCourseInfo();
	    }	   
		
	}
	
	public void displayStudentCourses(Course course)
	{
		

		
	}
	public void displayWelcomeMessage()
	{
		System.out.println("Hello, welcome to the university!  Please input your user name or password: ");
	}

}
