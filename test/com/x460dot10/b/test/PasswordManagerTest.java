package com.x460dot10.b.test;
import static org.junit.Assert.*;
import org.junit.Test;

import com.x460dot10.b.registrar.Password;
import com.x460dot10.b.registrar.PasswordManager;
import com.x460dot10.b.registrar.Student;
import com.x460dot10.b.registrar.University;


public class PasswordManagerTest {

	@Test
	public void testValidateAllAccounts() {
		University uni = University.getInstance();
		PasswordManager pm = PasswordManager.getInstance();
		
//		uni.addStudent("Mani", "Padme", "mani", "padme");
//		uni.addStudent("Karin", "Felton", "horace", "ccsfs");
//		uni.addStudent("Coren", "O'Rouke", "righto", "matey");
		
		//add passwords to the collection
		//(add one that isn't assoc w/any of these students)
		//check that the odd one is added
		//validateAllAccounts
		//check that the odd one has been removed
		
		Student newStu;
		Student rogueStu; //not added to uni
		Password newPwd;
		Password roguePwd;
		
		newPwd = new Password("mani", "padme", 0);
		newStu = new Student("Mani", "Padme", 0);
		pm.createPassword(newStu, newPwd.getUsername(), newPwd.getPassword());
		
		newPwd = new Password("horace", "ccsfs", 1);
		newStu = new Student("Karin", "Felton", 1);
		pm.createPassword(newStu, newPwd.getUsername(), newPwd.getPassword());
		
		//create rogue pwd object and add it to the pm
		roguePwd = new Password("rogue", "roguey", 567);
		rogueStu = new Student("Rogueish", "Jones", 567);
		pm.createPassword(rogueStu, roguePwd.getUsername(), roguePwd.getPassword());
		
		//test that this rogue student who's not in uni is added to pm's passwords collection
		assertEquals(567, pm.login(roguePwd.getUsername(), roguePwd.getPassword()));
		
		//should remove the rogue password
		pm.validateAllAccounts();
		
		assertEquals(-1, pm.login(roguePwd.getUsername(), roguePwd.getPassword()));
	}
	
	
	@Test
	public void testCreatePassword() {
		PasswordManager pm = PasswordManager.getInstance();		
		Password testPwd;
		Boolean pwdCreated = false;
		String replicatedUsername = "lojjiohj";
		
		testPwd = new Password("", "123456", 333);
		pwdCreated = pm.createPassword(new Student("abe", "lincoln", 333), "", "123456");
		assertEquals(false, pwdCreated);	

		testPwd = new Password("", "", 333);
		pwdCreated = pm.createPassword(new Student("abe", "lincoln", 333), "", "");
		assertEquals(false, pwdCreated);	

		testPwd = new Password("garrys", "12345", 333);
		pwdCreated = pm.createPassword(null, "garrys", "12345");
		assertEquals(false, pwdCreated);
		
		testPwd = new Password("abcde", "12345", 333);
		pwdCreated = pm.createPassword(new Student("abe", "lincoln", 333), "abcde", "12345");
		assertEquals(true, pwdCreated);
		
		//test that an already-used username cannot be added
		//this is weird - the student object already has a password object, so why am I passing in both the student and the
		//username + password?
		testPwd = new Password(replicatedUsername, "wokka2", 486);
		pwdCreated = pm.createPassword(new Student("abe", "lincoln", 486), replicatedUsername, "wokka2");
		assertEquals(true, pwdCreated);
		
		testPwd = new Password(replicatedUsername, "adfsd", 142);
		pwdCreated = pm.createPassword(new Student("Cory", "Mulligan", 142), replicatedUsername, "adfsd");
		assertEquals(false, pwdCreated);
		
		//test that a student that already has a username+pwd, their password record is overwritten w/new username-pwd
		assertEquals(486, pm.login(replicatedUsername, "wokka2"));	
		testPwd = new Password("riley234", "hortense45", 486);
		pm.createPassword(new Student("abe", "lincoln", 486), "riley234", "hortense45");
		assertEquals(486, pm.login("riley234", "hortense45"));
		
		pm.printAllPwdRecords();
		
		assertEquals(-1, pm.login(replicatedUsername, "wokka2"));
	}

	@Test
	public void testIsMinLength() {
		PasswordManager pm = PasswordManager.getInstance();
		Boolean pwdIsMinLength;
		pwdIsMinLength = pm.isMinLength("1");
		assertEquals(false, pwdIsMinLength);
		
		pwdIsMinLength = pm.isMinLength("123");
		assertEquals(false, pwdIsMinLength);
		
		pwdIsMinLength = pm.isMinLength("12345");
		assertEquals(true, pwdIsMinLength);
	}


	@Test
	public void testLogin() {
		PasswordManager pm = PasswordManager.getInstance();
		Student stu;
		Password stuPwd;
		String username;
		String password;
		int studentID;
		int expectedStudentID;
		
		//add 1st student's pwd info
		username = "studentTest";
		password = "tests";
		studentID = 123;
		stuPwd = new Password(username, password, studentID);
		stu = new Student("abe", "lincoln", studentID);
		
		pm.createPassword(stu, username, password);
		expectedStudentID = pm.login(username, password);
		assertEquals(expectedStudentID, studentID);	
		
		//add 2nd
		username = "moe";
		password = "riley2";
		studentID = 3432;
		stuPwd = new Password(username, password, studentID);
		stu = new Student("Moe", "Riley", studentID);
		
		pm.createPassword(stu, username, password);
		expectedStudentID = pm.login(username, password);
		assertEquals(expectedStudentID, studentID);	
	}
	
}
