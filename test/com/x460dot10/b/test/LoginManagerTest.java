package com.x460dot10.b.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.x460dot10.b.registrar.LoginManager;


public class LoginManagerTest {

	@Test
	public void testGetInstance() {
		//create 2 instances
		//assert that they have the same reference
		LoginManager lm1 = LoginManager.getInstance();
		LoginManager lm2 = LoginManager.getInstance();
		assertEquals(lm1, lm2);	
	}

	
	@Test
	public void testDisplayLoginScreen()
	{
		LoginManager lm = LoginManager.getInstance();
		
		//right now, this just runs this method so we can test it by using it. 
		//won't work until University.addStudent works.
		lm.displayLoginScreen();
	}
	
	@Test
	public void testRunCurrentStudent()
	{
		//add students to uni
		//try to log in as a student
		
		University uni;
		uni = University.getInstance();
		uni.addStudent("May", "Juin", "cornflower", "blues");
		
		PasswordManager pm = PasswordManager.getInstance();
		int studentID = pm.login("cornflower", "blues");
		
		System.out.println("student ID: " + studentID);
		//will want to call displayLoginScreen to test whether this student can log in, once added. ?
	}
}
