package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.exception.StudentNotRegisteredException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Student Operations
 *
 */

public interface StudentInterface {

    /**
     * Method to register Student
     * @param name
     * @param userID
     * @param password
     * @param semester
     * @return studnetId
     * @throws StudentNotRegisteredException
     */
    public String register(String name,String userID,String password,int semester,String department, String email , String phone , String role) throws StudentNotRegisteredException;

    /**
     * Method to check student approved by admin or not
     * @param studentId
     * @return boolean indicate if student is approved
     */
    public boolean isApproved(int studentId);

    /**
     * Method to view RegisteredCourses using studentId
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;

    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    public List<GradeCard> viewGradeCard(String studentId);

}
