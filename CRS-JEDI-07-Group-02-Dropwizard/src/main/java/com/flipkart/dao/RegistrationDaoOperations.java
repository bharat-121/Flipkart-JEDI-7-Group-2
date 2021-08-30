/**
 *
 */
package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.FeeAlreadyPaidException;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Class to implement Registration Dao Operations
 * This class communicates with the database.
 *
 */
public class RegistrationDaoOperations implements RegistrationDaoInterface{


    private static RegistrationDaoOperations instance=null;
    private PreparedStatement stmt = null;
    private static Logger logger = Logger.getLogger(RegistrationDaoOperations.class);

    /**
     * Default Constructor
     */
    private RegistrationDaoOperations()
    {}
    /**
     * Method to make RegistrationDaoOperation Singleton
     * @return
     */
    public static RegistrationDaoOperations getInstance()
    {
        if(instance==null)
        {
            instance=new RegistrationDaoOperations();
        }
        return instance;
    }

    /**
     * Method to add course in database
     * @param courseCode
     * @param studentId
     * @return boolean indicating if the course is added successfully
     * @throws SQLException
     */

    @Override
    public boolean addCourse(String courseCode, String studentId) throws SQLException{

        Connection conn = DBUtil.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);

            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
            stmt.setString(1, courseCode);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }
        return false;

    }


    /**
     * Number of registered courses for a student
     * @param studentId
     * @return Number of registered courses for a student
     * @throws SQLException
     */
    @Override
    public int numOfRegisteredCourses(String studentId) throws SQLException{

        Connection conn = DBUtil.getConnection();

        int count = 0;
        try {

            stmt = conn.prepareStatement(SQLQueriesConstants.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;

        }
        catch (SQLException se)
        {

            logger.error(se.getMessage());

        }
        catch (Exception e)
        {

            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return count;
    }


    /**
     * Check if seat is available for that particular course
     * @param courseCode
     * @return status of seat availablity
     * @throws SQLException
     */
    @Override
    public boolean seatAvailable(String courseCode) throws SQLException {

        Connection conn = DBUtil.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_SEATS);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return (rs.getInt("seats") > 0);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return true;


    }



    /**
     * Method checks if the student is registered for that course
     * @param courseCode
     * @param studentId
     * @return Students registration status
     * @throws SQLException
     */
    @Override
    public boolean isRegistered(String courseCode, String studentId) throws SQLException{

        Connection conn = DBUtil.getConnection();

        boolean check = false;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.IS_REGISTERED);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                check = true;
            }
        }
        catch(Exception e)
        {
            logger.error(e.getClass());
            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return check;

    }

    /**
     * Drop Course selected by student
     * @param courseCode : code for selected course
     * @param studentId
     * @return status of drop course operation
     * @throws SQLException
     */
    @Override
    public boolean dropCourse(String courseCode, String studentId) throws SQLException {

        Connection conn = DBUtil.getConnection();


        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.DROP_COURSE_QUERY);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            stmt.execute();

            stmt = conn.prepareStatement(SQLQueriesConstants.INCREMENT_SEAT_QUERY);
            stmt.setString(1, courseCode);
            stmt.execute();

            stmt.close();

            return true;
        }
        catch(Exception e)
        {
            logger.error("Exception found" + e.getMessage());
        }
        finally
        {

            stmt.close();
            conn.close();
        }


        return false;

    }

    /**
     * Method to retrieve fee for the selected courses from the database and calculate total fee
     * @param studentId
     * @return Fee Student has to pay
     * @throws SQLException
     */

    @Override
    public double calculateFee(String studentId) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        double fee = 0;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.CALCULATE_FEES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            fee = rs.getDouble(1);
        }
        catch(SQLException e)
        {
            logger.error(e.getErrorCode());
            logger.error(e.getMessage());
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return fee;
    }


    /**
     * Method to get the list of courses available from course catalog
     * @param studentId
     * @return list of courses
     * @throws SQLException
     */
    @Override
    public List<Course> viewAvailableCourses(String studentId) throws SQLException {

        List<Course> availableCourseList = new ArrayList<>();
        Connection conn = DBUtil.getConnection();

        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_AVAILABLE_COURSES);
            stmt.setString(1, studentId);
            stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("instructorId"), rs.getInt("seats")));

            }


        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return availableCourseList;

    }

    /**
     * Method to get the list of courses registered by the student
     * @param studentId
     * @return list of courses registered by student
     * @throws SQLException
     */
    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {

        Connection conn = DBUtil.getConnection();
        List<Course> registeredCourseList = new ArrayList<>();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);
            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("instructorId"), rs.getInt("seats")));

            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());

        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return registeredCourseList;
    }

    /**
     * Method to retrieve Student's registration status
     * @param studentId
     * @return Student's registration status
     * @throws SQLException
     */
    @Override
    public boolean getRegistrationStatus(String studentId) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        boolean status = false;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            status = rs.getBoolean(1);

        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());

        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return status;
    }
    /**
     * Method to set Student's registration status
     * @param studentId
     * @throws SQLException
     */
    @Override
    public void setRegistrationStatus(String studentId) throws SQLException
    {
        Connection conn = DBUtil.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.SET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());

        }
        finally
        {
            stmt.close();
            conn.close();
        }

    }

    /**
     * Method to set Student's payment status
     * @param studentId
     * @throws FeeAlreadyPaidException
     */
    @Override
    public boolean getPaymentStatus(String studentId) throws FeeAlreadyPaidException {
        Connection conn = DBUtil.getConnection();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_PAYMENT_STATUS);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            boolean paymentStatus = false;
            if (rs.next()) {
                paymentStatus = rs.getBoolean("paymentDone");
            }
            if (paymentStatus == true) {
                throw new FeeAlreadyPaidException(studentId);
            }
        }
        catch (SQLException e)
        {
            logger.error(e.getMessage());
        }
        catch (FeeAlreadyPaidException e){
            throw e;
        }
        return false;
    }
    @Override
    public int checkCourseAvailability(String studentId,String courseCode) throws SQLException{

        Connection conn = DBUtil.getConnection();

        int count = 0;
        try {

            stmt = conn.prepareStatement(SQLQueriesConstants.CHECK_COURSE_AVAILABILITY);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(courseCode.equals(rs.getString("courseCode")))
                    return 1;
                count++;
            }

            if(count >= 6)
                return 0;


        }
        catch (SQLException se)
        {

            logger.error(se.getMessage());

        }
        catch (Exception e)
        {

            logger.error(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return 2;
    }
}