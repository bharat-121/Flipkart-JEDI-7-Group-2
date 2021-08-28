package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Registration Operations
 *
 */

public interface RegistartionInterface {
    /**
     * Method to add Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is added successfully
     * @throws CourseNotFoundException
     * @throws SeatNotAvailableException
     * @throws CourseLimitExceedException
     * @throws SQLException
     */
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException;


    /**
     *  Method to drop Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is dropped successfully
     * @throws CourseNotFoundException
     * @throws SQLException
     */
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws CourseNotFoundException, SQLException;

    /**
     *  Method to view the list of available courses
     * @param studentId
     * @return List of courses
     * @throws SQLException
     */

    public List<Course> viewAvailableCourses(String studentId) throws SQLException;


    /** Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId
     * @return Fee Student has to pay
     * @throws SQLException
     */
    public double calculateFee(String  studentId) throws SQLException;

    /**
     *  Method to check student registration status
     * @param studentId
     * @return boolean indicating if the student's registration status
     */
    public boolean getRegistrationStatus(String studentId) throws SQLException;

    /**
     *  Method to check student registration status
     * @param studentId
     * @throws SQLException
     */
    public void setRegistrationStatus(String studentId) throws SQLException;

    /**
     * Method to view the list of courses registered by the student
     * @param studentId
     * @return List of courses
     * @throws SQLException
     */

    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;

    /**
     *  Method to check student payment status
     * @param studentId
     * @return boolean indicating if the student's payment status
     */    public boolean getPaymentStatus(String studentId) ;

}
