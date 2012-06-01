//not sure if we'll end up using this class at all. 

public class Password {

	private String password;
	private String username;
	private int studentID;
	
	public Password(String username, String password, int studentID)
	{
		this.password = password;
		this.username = username;
		this.studentID = studentID;
	}

	public int getStudentID()
	{
		return studentID;
	}
	
}
