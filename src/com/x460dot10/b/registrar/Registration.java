package com.x460dot10.b.registrar;

/**
 * Registration class
 *
 * Registration container
 *
 * @author  Samir Bhargava
 * @version 1.0
 */
import java.util.*;

public class Registration {
  public static int registrationID = 0;
  private int  studentID = 0;
  private int  courseID = 0;
  private Date  registrationDate;
  private String  memo = "";
  private int  registrationCount = 0;  

  /**
   * Registration constructor
   *
   * @param  studentID
   * @param  courseID
   */
  public Registration( int studentID, int courseID ) {
    this.registrationID++;
    this.registrationDate = new Date();
    this.studentID = studentID;
    this.courseID = courseID;
  }
    
  /**
   * setMemo
   *
   * Sets the memo field
   *
   * @param  memo
   */
  public void setMemo( String memo ) {
    this.memo = memo;
  }

  /**
   * getMemo
   *
   * Gets the memo field
   *
   * @return  memo
   */
  public String getMemo() {
    return this.memo;
  }

  /**
   * addCourse
   *
   * Increments the registration counnt
   *
   */
  public void addCourse() {
    registrationCount++;
  }

  /**
   * dropCourse
   *
   * Decrements the registration count
   *
   */
  public void dropCourse() {
    registrationCount--;
  }
}