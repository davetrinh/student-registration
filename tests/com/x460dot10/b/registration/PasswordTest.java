import static org.junit.Assert.*;

import org.junit.Test;


public class PasswordTest {

	@Test
	public void testtoCSV() {
		Password pwd = new Password("foo", "bar", 123);
		assertEquals(pwd.toCSV(), "foo,bar,123");
		
		pwd = new Password("", "", 0);
		assertEquals(pwd.toCSV(), ",,0");
	}

}
