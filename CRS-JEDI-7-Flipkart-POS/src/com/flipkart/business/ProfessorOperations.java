package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperations;

import java.util.List;

public class ProfessorOperations implements ProfessorInterface {
    public ProfessorOperations() {
        super();
    }

    static ProfessorOperations instance = null;

    ProfessorDaoInterface professorDAOInterface= ProfessorDaoOperations.getInstance();

    public static ProfessorInterface getInstance() {
        if (instance == null) {
            instance = new ProfessorOperations();
        }
        return instance;
    }

    @Override
    public Boolean addGrades(String studentId, String courseCode, String grade) {

//        try
//        {
            return professorDAOInterface.addGrade(studentId, courseCode, grade);
//        }
//        catch(Exception ex)
//        {
//            throw new GradeNotAddedException(studentId);
//        }

    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        List<EnrolledStudent> enrolledStudents =null ;
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

    @Override
    public List<Course> getCourses(String profId) {

        List<Course> coursesOffered=null;
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

    @Override
    public String getProfessorById(String instructorId) {
        return professorDAOInterface.getProfessorById(instructorId);
    }
}
