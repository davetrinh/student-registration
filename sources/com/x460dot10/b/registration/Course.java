import java.sql.Date;

public class Course {

	public Course(String courseName, Integer courseId, String description, Integer maxSeats, Integer currentSeats, Date start, Date end)
	{
		this.m_courseName = courseName;
		this.m_courseId = courseId;
		this.m_maxSeats = maxSeats;
		this.m_currentSeats = currentSeats;
		this.m_beginDate = start;
		this.m_endDate = end;
		
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
	
	public void printCourseInfo()
	{
		System.out.println("{Course id: " + m_courseId + "} " + "{Course Name: " + m_courseName + "} "
				+ "{Begin Date: " + m_beginDate.toString() + "} " +  "{End Date: " + m_endDate.toString() + "} "
				+ "{Max Seats: " + m_maxSeats + "} " + "{Current Seats: " + m_currentSeats + "}" );
		
	}
	
	private String  m_courseName;
	private Integer m_courseId;
	private Integer m_maxSeats;
	private Integer m_currentSeats;
	private Date    m_beginDate;
	private Date    m_endDate;
	
}
