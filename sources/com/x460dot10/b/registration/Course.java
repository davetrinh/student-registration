
public class Course {

  public Course(String courseName, Integer courseId, String description, Integer maxSeats, Integer currentSeats)
	{
		this.m_courseName = courseName;
		this.m_courseId = courseId;
		this.m_courseDescription = description;
		this.m_maxSeats = maxSeats;
		this.m_currentSeats = currentSeats;
		
	}
	
	public Boolean isCourseFull()
	{
		Boolean seatsAvail = false;
		
		if (m_currentSeats < m_maxSeats && m_currentSeats != m_maxSeats)
		{
			seatsAvail = true;
		}
		
		return seatsAvail;
	}
	
	public Boolean addedStudent()
	{
		Boolean success = false;
		
		if (m_currentSeats < m_maxSeats && m_currentSeats != m_maxSeats)
		{
			m_currentSeats++;
			success = true;
			System.out.println("Successfully Added Student to Course: " + getCourseName());
		}
		else
		{
			System.out.println("Sorry Class " + getCourseName() + " is Full");
		}
		return success;
	}
	
	public Boolean removedStudent()
	{
		Boolean success = false;
		if(m_currentSeats != 0)
		{
			m_currentSeats--;
			success = true;
		}
		else
		{
			System.out.println("Error, there are no students enrolled to course " + getCourseName());
		}
		return success;
	}
	public String getCourseName()
	{
		return m_courseName;
	}
	
	public Integer getCourseId()
	{
		return m_courseId;
	}
	
	public String getCourseDescription()
	{
		return m_courseDescription;
	}
	
	private String  m_courseName;
	private Integer m_courseId;
	private String  m_courseDescription;
	private Integer m_maxSeats;
	private Integer m_currentSeats;
	
}
