import static org.junit.Assert.*;

import org.junit.Test;


public class LoginManagerTest {

	@Test
	public void testGetInstance() {
		//create 2 instances
		//assert that they have the same reference
		LoginManager lm1 = LoginManager.getInstance();
		LoginManager lm2 = LoginManager.getInstance();
		
		assertEquals(lm1, lm2);
		
		//make sure that user can't use a constructor and make 2 instances
		LoginManager lm3 = new LoginManager();
		LoginManager lm4 = new LoginManager();
		
		
	}

}
