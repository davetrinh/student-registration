import static org.junit.Assert.*;
import org.junit.Test;


public class PasswordManagerTest {

	@Test
	public void testCreatePassword() {
		PasswordManager pm = new PasswordManager();
		Student testStudent = new Student(12342);
		
		Boolean pwdCreated = false;
		
		pwdCreated = pm.createPassword(new Student(123), "abc", "123456");
		assertEquals(false, pwdCreated);	

		pwdCreated = pm.createPassword(new Student(123), "", "");
		assertEquals(false, pwdCreated);	

		pwdCreated = pm.createPassword(new Student(0), "abcde", "12345");
		assertEquals(false, pwdCreated);
		
		pwdCreated = pm.createPassword(new Student(22), "abcde", "12345");
		assertEquals(true, pwdCreated);
	}

	@Test
	public void testIsMinLength() {
		PasswordManager pm = new PasswordManager();
		Boolean pwdIsMinLength;
		pwdIsMinLength = pm.isMinLength("1");
		assertEquals(false, pwdIsMinLength);
		
		pwdIsMinLength = pm.isMinLength("123");
		assertEquals(false, pwdIsMinLength);
		
		pwdIsMinLength = pm.isMinLength("12345");
		assertEquals(true, pwdIsMinLength);
	}

/*
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateAllAccounts() {
		fail("Not yet implemented");
	}
	*/

}
