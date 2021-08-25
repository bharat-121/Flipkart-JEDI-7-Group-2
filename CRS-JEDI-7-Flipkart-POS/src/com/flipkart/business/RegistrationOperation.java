package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public class RegistrationOperation implements RegistartionInterface{

    static RegistrationOperation instance = null;

    public static RegistartionInterface getInstance() {
        if (instance == null) {
            instance = new RegistrationOperation();
        }
        return instance;
    }


    /**
     * Method to add Course selected by student
     * @param courseCode
     * @param studentId
     * @return boolean indicating if the course is added successfully
     */
    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    /**
     *  Method to drop Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is dropped successfully
     */
    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    @Override
    public List<Course> viewCourses(String  studentId) {
        return null;
    }

    /** Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId
     * @return Fee Student has to pay
     */
    @Override
    public double calculateFee(String studentId) {
        return 0;
    }


    /**
     *  Method to check student registration status
     * @param studentId
     * @return boolean indicating if the student's registration status
     */
    @Override
    public boolean getRegistrationStatus(String studentId) {
        return false;
    }

    /**
     * Method to set student registration status
     * @param studentId
     */
    @Override
    public void setRegistrationStatus(String studentId) {

    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) {
        return null;
    }

}
