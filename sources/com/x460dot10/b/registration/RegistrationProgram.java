/** RegistrationProgram.java --- 
 *
 * Copyright (C) 2012 Alexandros Bantis
 *
 * Author: Alexandros Bantis <ambantis@okosmos>
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

public class RegistrationProgram
{
     private static RegistrationProgram program;

     // we only want the system to be initialized one time, once the
     // program begins running, thus a singleton
     public static initializer()
     {
          if (program == null)
               program = new RegistrationProgram();
          return program;
     }

     private RegistrationProgram()
     {
          University university = University.getInstance();
          // check if students.txt exists and import it if it does
          // check if courses.txt exists and import it if it does
          // check if passwords.txt exists and import it if it does
          // check if registrations.txt exists and import it if it does
          // instantiate Login Manager singleton, which has displayLoginScreen()
          // within its constructor
     }

     public shutDown()
     {
          // this function gets called when actor sends message to log out
          // cycle through passwords, registrations, courses, & students
          // saving them to a text file, display a message that all files
          // saved, and then system exit
     }

     public static void main(String[] args)
     {
          RegistrationProgram program = RegistrationProgram.initializer();
     }
}
