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

}
