package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;

public interface RegistrationDaoInterface {


    public boolean addCourse(String courseCode, String studentId) throws SQLException;

    public boolean dropCourse(String courseCode, String studentId) throws SQLException;

    public List<Course> viewCourses(String studentId) throws SQLException;

    List<Course> viewRegisteredCourses(String studentId) throws SQLException;


    public double calculateFee(String studentId) throws SQLException;

    public boolean seatAvailable(String courseCode) throws SQLException;


    public int numOfRegisteredCourses(String studentId) throws SQLException;

    public boolean isRegistered(String courseCode, String studentId) throws SQLException;
    public boolean getRegistrationStatus(String studentId) throws SQLException;

    public void setRegistrationStatus(String studentId) throws SQLException;


}
