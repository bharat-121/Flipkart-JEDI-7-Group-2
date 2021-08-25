package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperations;

import java.sql.SQLException;
import java.util.List;

public class RegistrationOperation implements RegistartionInterface{

    static RegistrationOperation instance = null;

    public static RegistartionInterface getInstance() {
        if (instance == null) {
            instance = new RegistrationOperation();
        }
        return instance;
    }
    RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperations.getInstance();

    /**
     * Method to add Course selected by student
     * @param courseCode
     * @param studentId
     * @return boolean indicating if the course is added successfully
     */
    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws Exception {

        if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
        {
            System.out.println("Course limit exceeded");
            throw new Exception();
        }
        else if (registrationDaoInterface.isRegistered(courseCode, studentId))
        {
            return false;
        }
        else if (!registrationDaoInterface.seatAvailable(courseCode))
        {
            System.out.println("Seat Not Available");
            throw new Exception();
        }

        return registrationDaoInterface.addCourse(courseCode, studentId);
    }

    /**
     *  Method to drop Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is dropped successfully
     */
    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws Exception{

        return registrationDaoInterface.dropCourse(courseCode, studentId);
    }

    @Override
    public List<Course> viewCourses(String  studentId) throws SQLException{

        return registrationDaoInterface.viewCourses(studentId);
    }

    /** Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId
     * @return Fee Student has to pay
     */
    @Override
    public double calculateFee(String studentId) throws SQLException {

        return registrationDaoInterface.calculateFee(studentId);
    }


    /**
     *  Method to check student registration status
     * @param studentId
     * @return boolean indicating if the student's registration status
     */
    @Override
    public boolean getRegistrationStatus(String studentId) throws SQLException {
        return registrationDaoInterface.getRegistrationStatus(studentId);
    }

    /**
     * Method to set student registration status
     * @param studentId
     */
    @Override
    public void setRegistrationStatus(String studentId) throws SQLException {
        registrationDaoInterface.setRegistrationStatus(studentId);
    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewRegisteredCourses(studentId);
    }

    }

