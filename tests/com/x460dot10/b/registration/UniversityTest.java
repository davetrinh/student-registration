import static org.junit.Assert.*;

import org.junit.Test;


public class UniversityTest {

	@Test
	public void testHasStudent() {
		University uni = University.getInstance();
				
		uni.addStudent("Mani", "Padme", "mani", "padme");
		uni.addStudent("Karin", "Felton", "horace", "ccsfs");
		uni.addStudent("Coren", "O'Rouke", "righto", "matey");
		
		uni.printStudentInfo();
		
		assertEquals(false, uni.hasStudent(99));
		assertEquals(true, uni.hasStudent(1));
	}

}
