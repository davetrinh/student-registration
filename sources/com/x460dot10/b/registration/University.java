/** University.java ---
 * 
 */

package com.x460dot10.b.registration;

import java.Util.vector;

public class University {
     private static University university;

     private LoginManager loginManager;
     private PasswordManager passwordManager;
     private SessionManager sessionManager;
     private RegistrationManager registrationManager;
     private Vector<Course> courses;
     private Vector<Student> students;

     // singleton of university
     private University () {}
     public static University getInstance()
     {
          if (university == null)
               university = new University();
          return university;
     }


     public Boolean addCourse(Date begin, Date end, String name,
               String description, int max)
     {
          Course newCourse = null;
          try
          {
               newCourse = new Course(begin, end, name, description, max);
               courses.add(newCourse);
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

	public Boolean addStudent(String first, String last, String user, 
               String password)
     {
          Student newStudent = null;
          try
          {
               newStudent = new Student(first, last, user, password);
               students.add(newStudent);
          }
          catch (Exception ex)
          {
               ex.printStackTrace();
          }
          finally 
          {
               if (newStudent == null)
                    return false;
               else
                    return true;
          }
     }
}
