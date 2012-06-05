/**
 * 
 */
package com.x460dot10.b.test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.x460dot10.b.mock.MockStudent;

import com.x460dot10.b.registrar.StartupManager;
import com.x460dot10.b.registrar.Observer;
import com.x460dot10.b.registrar.Password;
import com.x460dot10.b.registrar.Subject;
import com.x460dot10.b.registrar.SystemStatus;

/**
 * Tests methods of <code>StartupManager</code>
 * 
 * @author Alexandros Bantis
 */
public class InitializerTest implements Subject
{
     StartupManager program;
     ArrayList<MockStudent> expectedStudentsValid;
     ArrayList<Password> expectedPasswordsValid;
     ArrayList<Observer> observers;
     
     /**
      * @throws java.lang.Exception
      */
     @Before
     public void setUp() throws Exception
     {
          program = StartupManager.getInstance();
          observers = new ArrayList<Observer>();
          registerObservers();
     }
     
     @After
     public void tearDown() throws Exception
     {
          observers.clear();
     }
     
     void registerObservers()
     {
          if (!observers.isEmpty())
               return;
          
          //observers.add((Observer)program.uni.loginManager);
          observers.add((Observer)program.uni.pwdMngr);
          //observers.add((Observer)registrationManager);
     }     
     
     private void createExpectedStudentsValid()
     {
          Object homer = 
                    MockStudent.getStaticInstance(
                              1, "homer", "simpson", "01-01-1970").clone();
          expectedStudentsValid.add((MockStudent)homer);
          Object marge = 
                    MockStudent.getStaticInstance(
                              2, "marge", "simpson", "01-02-1970").clone();
          expectedStudentsValid.add((MockStudent)marge);
          Object bart = 
                    MockStudent.getStaticInstance(
                              3, "bart", "simpson", "01-03-1970").clone();
          expectedStudentsValid.add((MockStudent)bart);
          Object lisa = 
                    MockStudent.getStaticInstance(
                              4, "lisa", "simpson", "01-04-1970").clone();
          expectedStudentsValid.add((MockStudent)lisa);
          Object maggie = 
                    MockStudent.getStaticInstance(
                              5, "maggie", "simpson", "01-05-1970").clone();
          expectedStudentsValid.add((MockStudent)maggie);
     }
     
     private void createExpectedPasswordsValid()
     {
          Object homer = 
                    Password.getStaticInstance(1, "homer", "D'oh").clone();
          expectedPasswordsValid.add((Password)homer);
          Object marge = 
                    Password.getStaticInstance(2, "marge", "742").clone();
          expectedPasswordsValid.add((Password)marge);
          Object bart = 
                    Password.getStaticInstance(3, "bart", "cowabunga").clone();
          expectedPasswordsValid.add((Password)bart);
          Object lisa = 
                    Password.getStaticInstance(4, "lisa", "embiggen").clone();
          expectedPasswordsValid.add((Password)lisa);
          Object maggie = 
                    Password.getStaticInstance(5, "maggie", "suck").clone();
          expectedPasswordsValid.add((Password)maggie);
     }
     
     /**
      * Test method for 
      * {@link com.x460dot10.b.registrar.StartupManager#importStudents()}.
      */
     @Test
     public void importStudentsValidTest()
     {
          try
          {
               expectedStudentsValid = new ArrayList<MockStudent>();
               createExpectedStudentsValid();
               program.importStudents();
               ArrayList<MockStudent> actualStudents = 
                         program.uni.getAllMockStudents();
               assertEquals(expectedStudentsValid.size(),actualStudents.size());
               for (int i = 0; i < actualStudents.size(); ++i)
               {
                    assertEquals(expectedStudentsValid.get(i).getStudentId(),
                              actualStudents.get(i).getStudentId());

                    assertEquals(expectedStudentsValid.get(i).getFirstName(),
                              actualStudents.get(i).getFirstName());

                    assertEquals(expectedStudentsValid.get(i).getLastName(),
                              actualStudents.get(i).getLastName());

                    assertEquals(expectedStudentsValid.get(i).getDOB(),
                              actualStudents.get(i).getDOB());

               }
               
          } catch (IOException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }
     
     /**
      * Test method for 
      * {@link com.x460dot10.b.registrar.StartupManager#importPasswords()}.
      */
     @Test
     public void importPasswordsValidTest()
     {
          try
          {
               expectedPasswordsValid = new ArrayList<Password>();

               createExpectedPasswordsValid();
               program.importPasswords();
               /// need to set <code>SystemState.TESTING</code> in order to
               /// return <code>PasswordManager.passwords</code>
               notifyObservers();
               ArrayList<Password> actualPasswords = 
                         program.uni.pwdMngr.getAllPasswords();
               assertEquals(
                         expectedPasswordsValid.size(),
                         actualPasswords.size());
               for (int i = 0; i < actualPasswords.size(); ++i)
               {
                    assertTrue(actualPasswords.get(i).
                              equals(expectedPasswordsValid.get(i)));
               }
          } catch (IOException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     @Override
     public void notifyObservers()
     {
          for (Observer observer : observers)
               observer.update(SystemStatus.TESTING);          
     }    
}
