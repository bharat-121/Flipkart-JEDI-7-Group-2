package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Professor Operations
 *
 */

public interface ProfessorInterface {


    /**
     * Method to grade a Student
     * @param studentId
     * @param CourseCode
     * @param grade
     * @return boolean indicating if grade is added or not
     * @throws GradeNotAddedException
     */

    public Boolean addGrades(String studentId, String CourseCode, String grade) throws GradeNotAddedException;

    /**
     * Method to view all the enrolled students
     * @param profId: professor id
     * @return List of enrolled students
     */

    public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException;

    /**
     * Method to get list of all course a professor is teaching
     * @param profId: professor id
     * @return List of courses the professor is teaching
     */

    public List<Course> getCourses(String profId);

    /**
     * Method to get the professor name with ID
     * @param instructorId
     * @return profId
     */

    public String  getProfessorById(String instructorId);
}
