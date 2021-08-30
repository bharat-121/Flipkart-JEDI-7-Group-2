package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Professor Dao Operations
 *
 */

public interface ProfessorDaoInterface {
    /**
     * Method to get Courses by Professor Id using SQL Commands
     * @param userId, prof id of the professor
     * @return get the courses offered by the professor.
     */
    public List<Course> getCoursesByProfessor(String userId);

    /**
     * Method to view list of enrolled Students using SQL Commands
     * @param profId
     * @return
     */
    public List<EnrolledStudent> getEnrolledStudents(String profId);

    /**
     * Method to Grade a student using SQL Commands
     * @param studentId
     * @param courseCode
     * @param grade
     * @return
     */
    public Boolean addGrade(String studentId,String courseCode,String grade);
    /**
     * Method to Get professor name by id
     * @param profId
     * @return Professor Id in string
     */
    public String getProfessorById(String profId);
}