/**
 *
 */
package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.constants.Grade;
import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;

public class RegistrationDaoOperations implements RegistrationDaoInterface{


    private static RegistrationDaoOperations instance=null;
    private PreparedStatement stmt = null;

    /**
     * Default Constructor
     */
    private RegistrationDaoOperations()
    {}

    public static RegistrationDaoOperations getInstance()
    {
        if(instance==null)
        {
            instance=new RegistrationDaoOperations();
        }
        return instance;
    }



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
            System.out.println(e.getMessage());
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

            System.out.println(se.getMessage());

        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
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
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return check;

    }


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
            System.out.println("Exception found" + e.getMessage());
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
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
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
    public List<Course> viewCourses(String studentId) throws SQLException {

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
                        rs.getString("professorId"), rs.getInt("seats")));

            }


        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
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
                        rs.getString("professorId"), rs.getInt("seats")));

            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

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
            System.out.println(e.getMessage());

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
            System.out.println(e.getMessage());

        }
        finally
        {
            stmt.close();
            conn.close();
        }

    }
}