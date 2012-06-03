/**
 * 
 */
package com.x460dot10.b.test;

import com.x460dot10.b.registrar.Student;

/**
 * @author ambantis
 *
 */
public class MockStudent extends Student
{
     private static int countOfStudents = 0;
     public static MockStudent staticStudent;
     /// since dateOfBirth is only used for identification, 
     /// we don't need it to be a Date object
     private String dateOfBirth;
     
     
     /**
      * @param firstName
      * @param lastName
      * @param studentId
      * @param m_passwordInfo
      */
     public MockStudent(String firstName, String lastName, String dateOfBirth)
     {
          super(firstName, lastName, 0);
          this.m_studentId = countOfStudents++;
          this.dateOfBirth = dateOfBirth;
     }

     /**
      * Creates a static instance used in the import records process.

      * @author alexandros bantis
      * @return MockStudent a static instance of student
      */
     
     public static MockStudent staticInstance()
     {
          if (staticStudent == null)
               staticStudent = new MockStudent("","","");
          clearStaticInstance();
          return staticStudent;
     }
     
     /**
      * Sets the values of the static instance so that the text record
      * can be imported into University.students by the Initializer.

      * @author alexandros bantis
      * @param  studentID         integer student ID number
      * @param  firstName         student's first name
      * @param  lastName          student's last name
      * @param  dateOfBirth       string date of birth in MM-DD-YY format
      */
     public void setStaticInstance(int studentID, String firstName, 
               String lastName, String dateOfBirth)
     {
          this.m_studentId = studentID;
          this.m_firstName = firstName;
          this.m_lastName = lastName;
          this.dateOfBirth = dateOfBirth;
     }

     /**
      * Clears the values of the staticStudent; used by 
      * <code>staticInstance</code> method to ensure that it returns
      * a fresh copy for each text import iteration.

      * @author alexandros bantis
      */

     private static void clearStaticInstance()
     {
          staticStudent.m_studentId = 0;
          staticStudent.m_firstName = "";
          staticStudent.m_lastName = "";
          staticStudent.dateOfBirth = "00-00-00";
     }
     
     public static boolean dobIsValid(String dob)
     {
          return dob.length() == 8 && dob.charAt(2) == '-' &&
                    dob.charAt(5) == '-';
     }
     
     
}
     
     
   
