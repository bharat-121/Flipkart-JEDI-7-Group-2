package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JEDI-07
 * Class to implement Student Dao Operations
 *
 */
public class StudentDaoOperation implements  StudentDaoInterface{
    private static StudentDaoOperation instance=null;
    /**
     * Method to make StudentDaoOperation Singleton
     * @return
     */
    public static StudentDaoOperation getInstance()
    {
        if(instance==null)
        {

                instance=new StudentDaoOperation();

        }
        return instance;
    }

    /**
     * Method to add student to database
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     * @throws StudentNotRegisteredException
     */
    @Override
    public String register(Student student)  throws StudentNotRegisteredException {
        Connection connection=DBUtil.getConnection();
        String studentId="";
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
            preparedStatement.setString(1, student.getUserID());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4,student.getEmail());
            preparedStatement.setString(5, student.getRole());
            preparedStatement.setString(6,student.getPhone());

            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected==1)
            {
                //add the student record
                PreparedStatement preparedStatementStudent;
                preparedStatementStudent=connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
                preparedStatementStudent.setString(1,student.getUserID());
                preparedStatementStudent.setInt(2, student.getSemester());
                preparedStatementStudent.setString(3, student.getDepartment());
                preparedStatementStudent.setBoolean(4, false);
                preparedStatementStudent.executeUpdate();
                ResultSet results=preparedStatementStudent.getGeneratedKeys();
                if(results.next())
                    studentId=results.getString(1);
            }


        }
        catch(Exception ex)
        {
            throw new StudentNotRegisteredException(student.getName());
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage()+"SQL error");
                e.printStackTrace();
            }
        }
        return studentId;
    }


    /**
     * Method to check if Student is approved
     * @param studentId
     * @return boolean indicating if student is approved
     */

    @Override
    public boolean isApproved(int studentId) {

        String query=SQLQueriesConstants.IS_APPROVED;
        Connection connection=DBUtil.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                return rs.getBoolean("isApproved");
            }

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**]
     * Method to viewRegisteredCourses
     * @param studentId
     */
    @Override
    public void viewRegisteredCourses(String studentId){
        Connection conn =DBUtil.getConnection();
        PreparedStatement stmt;
        List<Course>  registeredCourseList=new ArrayList<>();

        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);
            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("instructorId"), rs.getInt("seats")));

            }

            System.out.println(registeredCourseList);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to view Grade Card
     * @param studentId
     * @return
     */
    @Override
    public List<GradeCard> viewGradeCard(String studentId){
        Connection conn = DBUtil.getConnection();
        List<GradeCard> grade_List = new ArrayList<>();
        PreparedStatement stmt;

        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_GRADE);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                String courseCode = rs.getString("courseCode");
                String courseName = rs.getString("courseName");
                String grade = rs.getString("grade");
                GradeCard obj = new GradeCard(courseCode, courseName,grade);
                grade_List.add(obj);
            }

            System.out.println(grade_List);
        }
       catch(Exception e){
           System.out.println(e.getMessage());
       }

        return grade_List;
    }
}
