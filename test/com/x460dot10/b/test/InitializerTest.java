/**
 * 
 */
package com.x460dot10.b.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.x460dot10.b.mock.MockStudent;
import com.x460dot10.b.registrar.Initializer;

/**
 * Tests methods of <code>Initializer</code>
 * 
 * @author Alexandros Bantis
 */
public class InitializerTest
{
     Initializer program;
     ArrayList<MockStudent> simpsons;
     /**
      * @throws java.lang.Exception
      */
     @Before
     public void setUp() throws Exception
     {
          program = Initializer.getInstance();
          simpsons = new ArrayList<MockStudent>();
          addSimpsons();
     }
     
     private void addSimpsons()
     {
          MockStudent homer = 
                    MockStudent.getStaticInstance(
                              1, "homer", "simpson", "01-01-1970");
          simpsons.add(homer);
          MockStudent marge = 
                    MockStudent.getStaticInstance(
                              2, "marge", "simpson", "01-02-1970");
          simpsons.add(marge);
          MockStudent bart = 
                    MockStudent.getStaticInstance(
                              3, "bart", "simpson", "01-03-1970");
          simpsons.add(bart);
          MockStudent lisa = 
                    MockStudent.getStaticInstance(
                              4, "lisa", "simpson", "01-04-1970");
          simpsons.add(lisa);
          MockStudent maggie = 
                    MockStudent.getStaticInstance(
                              5, "maggie", "simpson", "01-05-1970");
          simpsons.add(maggie);
     }
          
     /**
      * Test method for 
      * {@link com.x460dot10.b.registrar.Initializer#importStudents()}.
      */
     @Test
     public void testImportStudents()
     {
          try
          {
               program.importStudents();
               ArrayList<MockStudent> actualStudents = 
                         program.uni.getAllMockStudents();
               assertEquals(simpsons.size(),actualStudents.size());
               for (int i = 0; i < actualStudents.size(); ++i)
               {
                    assertEquals(simpsons.get(i).getStudentId(),
                              actualStudents.get(i).getStudentId());

                    assertEquals(simpsons.get(i).getFirstName(),
                              actualStudents.get(i).getFirstName());

                    assertEquals(simpsons.get(i).getLastName(),
                              actualStudents.get(i).getLastName());

                    assertEquals(simpsons.get(i).getDOB(),
                              actualStudents.get(i).getDOB());

               }
               
          } catch (IOException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

}
