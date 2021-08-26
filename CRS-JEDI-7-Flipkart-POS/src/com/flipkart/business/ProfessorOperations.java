package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperations;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Implementations of Professor Operations
 *
 */

public class ProfessorOperations implements ProfessorInterface {
    public ProfessorOperations() {
        super();
    }

    static ProfessorOperations instance = null;

    ProfessorDaoInterface professorDAOInterface= ProfessorDaoOperations.getInstance();

    /**
     * Method to make ProfessorOperation Singleton
     * @return instance
     */

    public static ProfessorInterface getInstance() {
        if (instance == null) {
            instance = new ProfessorOperations();
        }
        return instance;
    }

    /**
     * Method to grade a Student
     * @param studentId
     * @param courseCode
     * @param grade
     * @return boolean indicating if grade is added or not
     * @throws GradeNotAddedException
     */

    @Override
    public Boolean addGrades(String studentId, String courseCode, String grade) throws GradeNotAddedException {

        try
        {
            return professorDAOInterface.addGrade(studentId, courseCode, grade);
        }
        catch(Exception ex)
        {
            throw new GradeNotAddedException(studentId);
        }

    }


    /**
     * Method to view all the enrolled students
     * @param profId: professor id
     * @return List of enrolled students
     */

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException {
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try
        {
            enrolledStudents=professorDAOInterface.getEnrolledStudents(profId);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return enrolledStudents;
    }

    /**
     * Method to get list of all course a professor is teaching
     * @param profId: professor id
     * @return List of courses the professor is teaching
     */

    @Override
    public List<Course> getCourses(String profId) {

        List<Course> coursesOffered=new ArrayList<Course>();
        try
        {
            coursesOffered=professorDAOInterface.getCoursesByProfessor(profId);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return coursesOffered;
    }

    /**
     * Method to get the professor name with ID
     * @param instructorId
     * @return Professor name
     */

    @Override
    public String getProfessorById(String instructorId) {
        return professorDAOInterface.getProfessorById(instructorId);
    }
}
