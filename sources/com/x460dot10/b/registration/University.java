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

package com.x460dot10.b.registration;

import java.Util.vector;

/**
 * Controls the objects in the Student Registration System.
 *
 * @author Alexandros Bantis
 * @version 1.2 Wed May 30 2012
 */
public class University 
{
     private static University uni;
     private LoginManager loginManager;
     private PasswordManager passwordManager;
     private SessionManager sessionManager;
     private RegistrationManager registrationManager;
     private Vector<Course> courses;
     private Vector<Student> students;

     /**
      * Private default constructor for Singleton pattern
      */
     private University () {}

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
      * Adds a new <code>course</code> to <code>courses</code> 
      *
      * @param begin        Beginning date of course
      * @param end          Ending date of the course
      * @param name         Name of the course
      * @param description  Description of the course
      * @param maxNumber    Maximum student enrollment
      * @return             Indicates course was successfully added.
      */
     public Boolean addCourse(Date begin, Date end, String name,
                              String description, int maxNumber)
     {
          Course newCourse = null;
          try
          {
               newCourse = new Course(begin, end,
                                      name, description, maxStudents);
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

     /**
      * Adds a new <code>student</code> to <code>students</code> 
      *
      * @param first        First name
      * @param last         Last name
      * @param userID       UserID to login
      * @param password     Password to login
      * @return             Indicates student was successfully added
      */
	public Boolean addStudent(String first, String last, String userID, 
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
