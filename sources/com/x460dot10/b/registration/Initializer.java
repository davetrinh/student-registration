/**
 * Initializer.java
 * ------------------------------------------------------------------------
 *
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

import java.io.*;

/**
 * Controls startup, shutdown, and File I/O
 *
 * @author Alexandros Bantis
 * @version 1.2 Wed May 30 2012
 */
public class Initializer
{
     private static Initializer initializer;

    /**
      * Singleton pattern constructor called at program startup
      */
      public static void Startup()
     {
          if (initializer == null)
               initializer = new Initializer();
          return initializer;
     }

     /**
      * Creates a private singleton that starts up the system.
      * <p>
      * This method reads the data files for passwords, students, courses, and
      * registrations, saving it to their respective data containers, then
      * validates the data, then instantiates <code>LoginManager</code>
      *
      * @return             Indicates data files loaded with no errors
      */
     private boolean Initializer()
     {
          University uni = University.getInstance();
          Boolean studentImportSuccessful = importStudents();
          Boolean passwordImportSuccessful = importPasswords();
          Boolean courseImportSuccessful = importCourses();
          Boolean registrationsImportSuccessful = importRegistrations();
          Boolean importValidated = validateData();
     }

     /**
      * Imports data/students.txt into <code>University.students</code>
      *
      * @return             Indicates import of students was successful
      */
     public boolean importStudents()
     {
          Boolean importStudentsSuccessful = true;
          BufferedReader reader = null;
          try
          {
               reader = new BufferedReader(
                   new FileReader("data/students.txt"));
               Scanner scanner = new Scanner(reader);
               while (scanner.hasNextLine())
               {
                    String line = scanner.nextLine();
                    uni.addStudent(Student.parseStudent(line));

               }                         
          }
          catch (Exception ex)
          {
               // TODO send error message to a log file
               System.err.println("Error: " + ex.getMessage());
               importStudentsSuccessful = false;
          }
          finally
          {
               if (reader != null)
                    reader.close();
          }
          return importStudentsSuccessful;
     }          

     /**
      * Imports data/passwords.txt into <code>University.passwords</code>
      *
      * @return             Indicates import of passwords was successful
      */
          public boolean importPasswords()
     {
          Boolean importPasswordsSuccessful = true;
          BufferedReader reader = null;
          try
          {
               reader = new BufferedReader(
                   new FileReader("data/students.txt"));
               Scanner scanner = new Scanner(reader);
               while (scanner.hasNextLine())
               {
                    String line = scanner.nextLine();
                    uni.addStudent(Student.parseStudent(line));
               }                         
          }
          catch (Exception ex)
          {
               // TODO send error message to a log file
               System.err.println("Error: " + ex.getMessage());
               importPasswordsSuccessful = false;
          }
          finally
          {
               if (reader != null)
                    reader.close();
          }
          return importPasswordsSuccessful;
     }          

     /**
      * Imports data/courses.txt into <code>University.courses</code>
      *
      * @return             Indicates import of courses was successful
      */
     public boolean importCourses()
     {
          Boolean importCoursesSuccessful = true;
          BufferedReader reader = null;
          try
          {
               reader = new BufferedReader(
                   new FileReader("data/students.txt"));
               Scanner scanner = new Scanner(reader);
               while (scanner.hasNextLine())
               {
                    String line = scanner.nextLine();
                    uni.addStudent(Student.parseStudent(line));
               }                         
          }
          catch (Exception ex)
          {
               // TODO send error message to a log file
               System.err.println("Error: " + ex.getMessage());
               importCoursesSuccessful = false;
          }
          finally
          {
               if (reader != null)
                    reader.close();
          }
     }          

     /**
      * Imports data/registrations.txt into <code>University.registrations</code>
      *
      * @return             Indicates import of registration data was successful
      */
     public boolean importRegistrations()
     {
          Boolean importRegistrationsSuccessful = true;
          BufferedReader reader = null;
          try
          {
               reader = new BufferedReader(
                   new FileReader("data/students.txt"));
               Scanner scanner = new Scanner(reader);
               while (scanner.hasNextLine())
               {
                    String line = scanner.nextLine();
                    uni.addStudent(Student.parseStudent(line));
               }                         
          }
          catch (Exception ex)
          {
               // TODO send error message to a log file
               System.err.println("Error: " + ex.getMessage());
               importRegistrationsSuccessful = false;
          }
          finally
          {
               if (reader != null)
                    reader.close();
          }
          return false;
     }          

     /**
      * Validates all data imports
      *
      * @return             Indicates all data is valid
      */
     private boolean validateData()
     {
          return false;
     }

     /**
      * Saves data to files and then shuts down program
      *
      */
     public void shutDown()
     {
          saveStudents();
          savePasswords();
          saveCourses();
          saveRegistrations();
     }

     /**
      * Saves data to students.txt
      *
      * @return             Indicates successful save of student data
      */
     public boolean saveStudents()
     {
          return false;
     }

     /**
      * Saves data to passwords.txt
      *
      * @return             Indicates successful save of password data
      */
     public boolean savePasswords()
     {
          return false;
     }

     /**
      * Saves data to courses.txt
      *
      * @return             Indicates successful save of course data
      */
     public boolean saveCourses()
     {
          return false;
     }

     /**
      * Saves data to registrations.txt
      *
      * @return             Indicates successful save of registration data
      */
     public boolean saveRegistrations()
     {
          return false;
     }


     public static void main(String[] args)
     {
          Initializer initializer = Initializer.StartUp();
     }
}