//this is a stub (kind of) class, just to get the unit tests going, so that
//when those tests run on real code,
//they'll still pass/fail accordingly.
public class Student {

	private int studentID;
	
	public Student(int studentID)
	{
		this.studentID = studentID;
	}
	
	
	public Boolean isValid()
	{
		if(studentID > 0){
			return true;
		}else
		{
			return false;
		}
	}
	
}
