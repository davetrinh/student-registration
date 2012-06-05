package com.x460dot10.b.registrar;

import com.x460dot10.b.mock.MockStudent;

public class Applicant extends MockStudent
{
     private String userName;
     String password;

     public Applicant(String firstName, String lastName, 
               String dateOfBirth, String userName, String password)
     {
          super(firstName, lastName, dateOfBirth);
          this.userName = userName;
          this.password = password;
          this.m_studentId = 0;
     }

     public String getUserName()
     {
          return userName;
     }

     public void setUserName(String userName)
     {
          this.userName = userName;
     }

     public String getPassword()
     {
          return password;
     }

     public void setPassword(String password)
     {
          this.password = password;
     }
     
     
     
     
     

}
