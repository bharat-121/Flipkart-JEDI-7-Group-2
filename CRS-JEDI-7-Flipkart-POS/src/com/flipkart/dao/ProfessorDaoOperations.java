package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;


public class ProfessorDaoOperations implements ProfessorDaoInterface {

    private static ProfessorDaoOperations instance=null;

    /**
     * Default Constructor
     */
    private ProfessorDaoOperations()
    {

    }

    /**
     * Method to make ProfessorDaoOperation Singleton
     * @return
     */
    public static ProfessorDaoOperations getInstance()
    {
        if(instance==null)
        {
            instance=new ProfessorDaoOperations();
        }
        return instance;
    }


    @Override
    public List<Course> getCoursesByProfessor(String profId) {
        Connection connection=DBUtil.getConnection();
        List<Course> courseList=new ArrayList<Course>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);

            statement.setString(1, profId);

            ResultSet results=statement.executeQuery();
            while(results.next())
            {
                courseList.add(new Course(results.getString("courseCode"),results.getString("courseName"),results.getString("instructorId"),results.getInt("seats")));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return courseList;

    }

    @Override
    public List<EnrolledStudent> getEnrolledStudents(String profId) {
        Connection connection=DBUtil.getConnection();
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
            statement.setString(1, profId);

            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                //public EnrolledStudent(String courseCode, String courseName, int studentId)
                enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),results.getString("courseName"),results.getString("studentId")));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return enrolledStudents;
    }


    public Boolean addGrade(String studentId,String courseCode,String grade) {
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);

            statement.setString(1, grade);
            statement.setString(2, courseCode);
            statement.setString(3, studentId);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else
                return false;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public String getProfessorById(String profId)
    {
        String prof_Name = null;
        Connection connection=DBUtil.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROF_NAME);

            statement.setString(1, profId);
            ResultSet rs = statement.executeQuery();
            rs.next();

            prof_Name = rs.getString(1);

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return prof_Name;
    }
}