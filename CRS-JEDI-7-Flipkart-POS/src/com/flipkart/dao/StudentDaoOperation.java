package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDaoOperation implements  StudentDaoInterface{
    private static StudentDaoOperation instance=null;

    public static StudentDaoOperation getInstance()
    {
        if(instance==null)
        {

                instance=new StudentDaoOperation();

        }
        return instance;
    }


    @Override
    public String register(Student student){
        Connection connection= DBUtil.getConnection();
        String  studentId;

            //dummy query
            String query="insert into User values(?,?,?,?,?,?)";
            //open db connection
        PreparedStatement preparedStatement= null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getUserID());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getName());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getRole());
            preparedStatement.setString(6, student.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student.getUserID();
    }




    @Override
    public boolean isApproved(int studentId) {
        String query="";
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

    @Override
    public void viewRegisteredCourses(String studentId){
        String query="Select * from table where studentId=?";
        Connection conn =DBUtil.getConnection();
        PreparedStatement stmt;
        List<Course>  registeredCourseList=new ArrayList<>();


        try
        {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("instructorId"), rs.getInt("seats")));

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<GradeCard> viewGradeCard(String studentId){
// create variable course code ,course name , grade in Grade Card
        Connection conn = DBUtil.getConnection();
        List<GradeCard> grade_List = new ArrayList<>();
        PreparedStatement stmt;
        String query="";

        try
        {
            stmt = conn.prepareStatement(query);
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
        }
       catch(Exception e){
           System.out.println(e.getMessage());
       }

        return grade_List;
    }
}
