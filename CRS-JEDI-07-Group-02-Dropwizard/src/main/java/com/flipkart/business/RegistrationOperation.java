package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperations;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-07
 * The Registration Operation provides the business logic for student registration.
 *
 */

public class RegistrationOperation implements RegistartionInterface{

    static RegistrationOperation instance = null;
    private static Logger logger = Logger.getLogger(RegistrationOperation.class);


    /**
     * Method to make Registration Operation Singleton
     *
     * @return
     */

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
     * @param availableCourseList
     * @return boolean indicating if the course is added successfully
     * @throws CourseNotFoundException
     * @throws SeatNotAvailableException
     * @throws CourseLimitExceedException
     * @throws SQLException
     */
    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException{

        if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
        {
            throw new CourseLimitExceedException(6);
        }
        else if (registrationDaoInterface.isRegistered(courseCode, studentId))
        {
            return false;
        }
        else if (!registrationDaoInterface.seatAvailable(courseCode))
        {
            throw new SeatNotAvailableException(courseCode);
        }
        else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
        {
            throw new CourseNotFoundException(courseCode);
        }

        return registrationDaoInterface.addCourse(courseCode, studentId);
    }

    /**
     *  Method to drop Course selected by student
     * @param courseCode
     * @param studentId
     * @param registeredCourseList
     * @return boolean indicating if the course is dropped successfully
     * @throws CourseNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws CourseNotFoundException, SQLException{

        if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList))
        {
            throw new CourseNotFoundException(courseCode);
        }

        return registrationDaoInterface.dropCourse(courseCode, studentId);
    }


    /**
     *  Method to view the list of available courses
     * @param studentId
     * @return List of courses
     * @throws SQLException
     */

    @Override
    public List<Course> viewAvailableCourses(String  studentId){

        try {
            return registrationDaoInterface.viewAvailableCourses(studentId);
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            return  null;
        }
    }

    /** Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId
     * @return Fee Student has to pay
     * @throws SQLException
     */
    @Override
    public double calculateFee(String studentId) throws SQLException {

        return registrationDaoInterface.calculateFee(studentId);
    }


    /**
     *  Method to check student registration status
     * @param studentId
     * @return boolean indicating if the student's registration status
     * @throws SQLException
     */
    @Override
    public boolean getRegistrationStatus(String studentId) throws SQLException {
        return registrationDaoInterface.getRegistrationStatus(studentId);
    }

    /**
     * Method to set student registration status
     * @param studentId
     * @throws SQLException
     */
    @Override
    public void setRegistrationStatus(String studentId) throws SQLException {
        registrationDaoInterface.setRegistrationStatus(studentId);
    }


    /**
     * Method to view the list of courses registered by the student
     * @param studentId
     * @return List of courses
     * @throws SQLException
     */

    @Override
    public List<Course> viewRegisteredCourses(String studentId){
        try {
            return registrationDaoInterface.viewRegisteredCourses(studentId);
        }
        catch (SQLException e){
            logger.error(e.getMessage());
            return  null;
        }
    }

    @Override
    public boolean getPaymentStatus(String studentId) {
        try {
            return registrationDaoInterface.getPaymentStatus(studentId);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return true;
    }
    @Override
    public boolean checkCourse(String courseCode,String studentId,List<Course> availableCourseList) throws CourseLimitExceedException, CourseAlreadyRegisteredException, SeatNotAvailableException, CourseNotFoundException
    {

        try {
            int response = registrationDaoInterface.checkCourseAvailability(studentId, courseCode);

            if (response == 0){
                throw new CourseLimitExceedException(6);
            }
            else if (response == 1) {
                throw new CourseAlreadyRegisteredException(courseCode);
            }
            else if (!registrationDaoInterface.seatAvailable(courseCode)) {
                throw new SeatNotAvailableException(courseCode);
            }
            else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList)){
                throw new CourseNotFoundException(courseCode);
            }

            return true;

        }
        catch (SQLException e) {
            logger.info(e.getMessage());

        }

        return false;

    }
}

