package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Student Operations
 *
 */

public interface StudentDaoInterface {

    /**
     * Method to add student to database
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     * @throws StudentNotRegisteredException
     */
    public String register(Student student) throws StudentNotRegisteredException;

    /**
     * Method to check if Student is approved
     * @param studentId
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(int studentId);

    public void viewRegisteredCourses(String studentId);
    public List<GradeCard> viewGradeCard(String studentId);
}
