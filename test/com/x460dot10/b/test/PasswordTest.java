package com.x460dot10.b.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.x460dot10.b.registrar.Password;


public class PasswordTest {

	@Test
	public void testtoCSV() {
		Password pwd = new Password("foo", "bar", 123);
		assertEquals("\"foo\",\"bar\",123", pwd.toCSV());
		
		pwd = new Password("", "", 0);
		assertEquals("\"\",\"\",0", pwd.toCSV());
	}

}
