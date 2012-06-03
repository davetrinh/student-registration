package com.x460dot10.b.registrar;

/**
 *  RegistrationManager class
 *
 *  Utility class for manipulation of Registration objects
 *  
 *  @author  Samir Bhargava
 *  @version 1.0
 *
 */

import java.util.*;

public class RegistrationManager {  
  protected Vector<Registration> registrations = new Vector<Registration>();

  /**
   *  Registration Constructor
   *
   *  Adds Registration objects to the RegistrationManager object
   *
   *  @param  studentID
   */
  public RegistrationManager(int studentID){}

  /**
   *  addToSchedule method
   *
   *  Adds a course to a student's schedule
   *
   *  @param  studentID
   *  @param  courseID
   *  @return success
   */
  public boolean addtoSchedule(int studentID, int courseID){
    boolean success = true;
    return success;
  }

  /**
   * courseEnrollmentCount method
   * 
   * Returns the enrollment count for a given course
   *
   * @param  courseID
   * @return enrollmentCount
   */
   public int courseEnrollmentCount(int courseID){
     int enrollmentCount = 0;
     return enrollmentCount;
   }

  /**
   * dropFromSchedule
   *
   * Drops a course from the given student's schedule
   *
   * @param  courseID
   * @return success
   */
   public boolean dropFromSchedule(int courseID){
     boolean success = true;
     return success;
   }

  /**
   * isCourseFull method
   *
   * Checks if course is full
   *
   * @param  courseID
   * @return success
   */
   public boolean isCourseFull( int courseID ){
     boolean success = true;
     return success;
   }

  /**
   * isCurrentlyEnrolled method
   * 
   * Checks if student is currently enrolled in course
   *
   * @param  studentID
   * @param  courseID
   * @return success
   */
  public boolean isCurrentlyEnrolled(int studentID, int courseID){
    boolean success = true;
    return success;
  }

  /**
   * showAllCourses method
   *
   * @return  allCourses
   */
  public String showAllCourses(){
    String allCourses = "";
    return allCourses;
  }

  /**
   * showMyCourses method
   *
   * Displays the courses for a given student
   *
   * @param  studentID
   * @return myCourses
   */
  public String showMyCourses(int studentID){
    String myCourses = "";
    return myCourses;
  }

  /**
   * validateRegistration method
   *
   * Validates Registration object
   */
  public void validateRegistration(){}
}