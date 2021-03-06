package com.x460dot10.b.registrar;
/**
 * University.java
 * ------------------------------------------------------------------------
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

//package com.x460dot10.b.registration;

import java.util.ArrayList;
import java.util.Date;

import com.x460dot10.b.mock.MockStudent;

/**
 * Controls the objects in the Student Registration System.
 *
 * @author Alexandros Bantis
 * @version 1.2 Wed May 30 2012
 */
public class University implements Subject
{
     static University uni;
     
     private ArrayList<Observer> observers;
     private SystemStatus systemStatus;
     public LoginManager loginManager;
     public PasswordManager passwordManager;
     public SessionManager sessionManager;
     //public RegistrationManager registrationManager;
     protected ArrayList<Course> courses;
     protected ArrayList<MockStudent> students;
     
     public void startup()
     {
          
     }
     
     /**
      * Private default constructor for Singleton pattern
      */
     private University () {
          students = new ArrayList<MockStudent>();
          courses = new ArrayList<Course>();
          loginManager = LoginManager.getInstance();
          passwordManager = PasswordManager.getInstance();
          sessionManager = SessionManager.getInstance();
          // registrationManager = RegistrationManager.getInstance();
          observers = new ArrayList<Observer>();
          registerManagersAsObservers();
          setStatus(SystemStatus.STARTING_UP);

     }

     /**
      * Function to create Singleton instance.
      */
     public static University getInstance()
     {
          if (uni == null)
               uni = new University();
          return uni;
     }


     /**
      * Using the Observer pattern, registers all the Managers to receive
      * updates regarding changes to the <code>University.SystemStatus</code> 
      */
     void registerManagersAsObservers()
     {
          if (observers.size() > 0)
               return;
          
          //observers.add((Observer)loginManager);
          observers.add((Observer)passwordManager);
          //observers.add((Observer)sessionManager);
          //observers.add((Observer)registrationManager);
     }
     
     public void notifyObservers()
     {
          for (Observer observer : observers)
               observer.update(systemStatus);
     }
     
     private void setStatus(SystemStatus newStatus)
     {
          systemStatus = newStatus;
          notifyObservers();
     }
     
     
     /**
      * Adds a new <code>course</code> to <code>courses</code> 
      *
      * @author Alexandros Bantis
      * @param begin              Beginning date of course
      * @param end                Ending date of the course
      * @param name               Name of the course
      * @param description        Description of the course
      * @param maxNumber          Maximum student enrollment
      * @return                   <code>true</code> indicates course 
      *                           was successfully added else 
      *                           <code>false</code>
      */
     @SuppressWarnings("unused")
     public Boolean addCourse(Date begin, Date end, String name,
                              String description, int maxNumber)
     {
          Course newCourse = null;
          try
          {
               //newCourse = new Course(begin, end,
               //                       name, description, maxNumber);
               //courses.add(newCourse);
          }
          catch (Exception ex)
          {
               ex.printStackTrace();
          }
          if (newCourse == null)
               return false;
          else
               return true;
     }
     
     /**
      * Tests whether <code>University</code> should honor a request to create
      * a new account. 
      * 
      * @author Alexandros Bantis        
      * @param  first                       proposed student's first name
      * @param  last                        proposed student's last name
      * @param  dob                         proposed student's dob
      * @return boolean                     <code>true</code> indicates the 
      *                                     proposed record isn't a duplicate
      *                                     while; if dob is not valid or three
      *                                     parameters match an existing record
      *                                     then will throw exception.
      *                                     <code>students</code>
      * @throws IllegalArgumentException    if the first, last, & dob match an
      *                                     existing record in 
      *                                     <code>students</code> the will
      *                                     throw the exception
      */
     public boolean isValidNewStudentRecord(String first, 
               String last, String dob) throws IllegalArgumentException 
     {
          if (!MockStudent.dobIsValid(dob))
               throw new IllegalArgumentException("Error: birthdate is not " +
                         "valid format (mm-dd-yy");
          for (MockStudent student : students)
          {
               if (student.hasSameName(first, last) &&
                         student.getDOB().equals(dob))
                    throw new IllegalArgumentException("Error: you're first " +
                         "name, last name, and date of birth matches an " +
                         "existing student record. Unable to create " +
                         "new account ");
          }
          return true;
     }
     
     /**
      * Returns an <code>studentID</code> for a newly created account if no
      * duplicate records would be created, data formatted correctly, and 
      * follows password rules.
      * <p>
      * Creates a new <code>Student</code> and <code>Password</code> if there
      * is no match to an existing student (same first name, last name, and
      * date of birth), an existing password user name, the date
      * of birth was entered correctly, and the password follows the password
      * rules.
      * 
      * @author Alexandros Bantis
      * @param  first        the proposed student's first name
      * @param  last         the proposed student's last name
      * @param  dob          the proposed student's date of birth, entered as
      *                      a string in this format: mm-dd-yy
      * @param  userName     the proposed student's login user name
      * @param  password     the proposed student's login password
      * @return              the newly created student's <code>studentID</code>
      *                      else will return -1 and pass exception
      */
     public int createNewAccount(String first, String last, String dob,
               String userName, String password)
     {
          final int INVALID_STUDENT_ID = -1;
          
          if (!(isValidNewStudentRecord(first, last, dob) &&
                    passwordManager.isValidNewPasswordRecord(
                              userName, password)))
               return INVALID_STUDENT_ID;

          MockStudent newStudent = new MockStudent(first, last, dob);
          students.add(newStudent);
          passwordManager.createNewPasswordRecord(
                    newStudent.getID(), userName, password);
          return newStudent.getID();
     }
	
	
    /**
     * Checks whether the student exists in the university.
     *
     * @author Kathleen Gustafson
     * @param  studentID		    Unique identifying number for student
     * @return             	    <code>true</code> indicates University 
     *                            contains student with this ID, else
     *                            <code>false</code> indicates no match.
     */
	public Boolean hasStudent(int studentID)
	{
		//for each student in the vector
			//if it has this student id
				//return true
		for (Student s : students)
		{
			if (s.getStudentId() == studentID)
			{
				return true;
			}
		}
		
		return false;
	}

	/**
	* Validates that proposed student isn't a duplicate of an already
	* existing student object. 
	*
	* @author alexandros bantis
	* @param  firstName          first name
	* @param  lastName           last name
	* @param  dateOfBirth        date of birth string in MM/DD/YY format
	* @return                    <code>true</code> if no match with existing 
	*                            students, otherwise <code>false</code>
	*/
	public boolean proposedStudentIsValid(String firstName, 
	          String lastName, String dateOfBirth)
	{
	     return false;
	}
	

	/**
	 * Validates that there are no duplicate records in <code>students</code>. 
	 *
	 * @author alexandros bantis
	 * @return                    <code>true</code> if no duplicate records in 
	 *                            <code>students</code>, otherwise returns 
	 *                            <code>false</code>
	 */
	public boolean validateAllStudents()
	{
	     return false;
	}

     /**
      * Returns list of all <code>students.studentID</code> so that 
      * <code>StartupManager</code> can validate that text file import is
      * consistent with <code>passwordManager.passwords</code> and 
      * <code>registrationManager.registrations</code> 
      *
      * @author alexandros bantis
      * @return                    <code>true</code> if no duplicate records in 
      *                            <code>students</code>, otherwise returns 
      *                            <code>false</code>
      */
     public ArrayList<Integer> allStudentIDs()
     {
          return new ArrayList<Integer>();
     }
	
     /**
     * Returns copy of <code>students</code> for
     * <code>UniversityTest</code> class
     *
     * @return           <code>students</code>
     */   

     public ArrayList<MockStudent> getAllMockStudents()
	{
	     return students;
	}

	
	
	/**
     * Finds the largest student ID number in <code>students</code>
     *
     * @return   		max studentID or 0 if no students.
     */	
	@SuppressWarnings("unused")
     private int getLargestStudentID()
	{
		int largestID = 0;

		for (Student s : students)
		{
			if (s.getStudentId() > largestID)
			{
				largestID = s.getStudentId();
			}
		}
		
		return largestID;
	}
	
    /**
     * Prints the student full name and ID number to the console
     */
	public void printStudentInfo()
	{
		for (Student s : students)
		{
			System.out.println(s.getFullName() + " " + s.getStudentId());
		}
	}
}