/**
 * 
 */
package com.x460dot10.b.mock;

import com.x460dot10.b.registrar.Student;

/**
 * @author ambantis
 *
 */
public class MockStudent extends Student implements Cloneable
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
     
     public static void staticInstance()
     {
          if (staticStudent == null)
               staticStudent = new MockStudent("","","");
          clearStaticInstance();
     }
     
     /**
      * Sets the values of the static instance so that the text record
      * can be imported into University.students by the StartupManager.

      * @author alexandros bantis
      * @param  studentID         integer student ID number
      * @param  firstName         student's first name
      * @param  lastName          student's last name
      * @param  dateOfBirth       string date of birth in MM-DD-YY format
      */
     public static MockStudent getStaticInstance(int studentID, String firstName, 
               String lastName, String dateOfBirth)
     {
          staticInstance();
          staticStudent.m_studentId = studentID;
          staticStudent.m_firstName = firstName;
          staticStudent.m_lastName = lastName;
          staticStudent.dateOfBirth = dateOfBirth;
          return staticStudent;
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
     
     public String getDOB()
     {
          return dateOfBirth;
     }
     
     public int getID()
     {
          return m_studentId;
     }
     
     public String toCSV()
     {
          StringBuilder export = new StringBuilder();
          export.append('\"');
          export.append(m_studentId);
          export.append("\",\"");
          export.append(m_firstName);
          export.append("\",\"");
          export.append(m_lastName);
          export.append("\",\"");
          export.append(dateOfBirth);
          export.append('\"');
          return export.toString();
     }
     
     public boolean hasSameName(String first, String last)
     {
          StringBuilder thisName = 
                    new StringBuilder(this.m_firstName);
          thisName.append(' ');
          thisName.append(this.m_lastName);
          StringBuilder otherName =
                    new StringBuilder(first);
          otherName.append(' ');
          otherName.append(last);
          return thisName.toString().equalsIgnoreCase(otherName.toString());
     }
     
     @Override
     public Object clone()
     {
          try
          {
               return super.clone();
          }
          catch (CloneNotSupportedException ex)
          {
               return null;
          }
     } 
}
     
     
   
