package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorInterface {

    public Boolean addGrades(String studentId, String CourseCode, String grade) throws GradeNotAddedException;;

    public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException;

    public List<Course> getCourses(String profId);

    public String  getProfessorById(String instructorId);
}
