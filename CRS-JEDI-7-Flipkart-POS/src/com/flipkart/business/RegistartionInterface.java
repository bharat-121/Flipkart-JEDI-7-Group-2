package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public interface RegistartionInterface {
    /**
     * Method to add Course selected by student
     * @param courseCode
     * @param studentId
     * @return boolean indicating if the course is added successfully
     */
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList);

    /**
     *  Method to drop Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is dropped successfully
     */
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList);

    public List<Course> viewCourses(String studentId);

    /** Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId
     * @return Fee Student has to pay
     */
    public double calculateFee(String  studentId);

    /**
     *  Method to check student registration status
     * @param studentId
     * @return boolean indicating if the student's registration status
     */
    boolean getRegistrationStatus(String studentId);

    /**
     * Method to set student registration status
     * @param studentId
     */
    void setRegistrationStatus(String studentId);

    List<Course> viewRegisteredCourses(String studentId);
}
