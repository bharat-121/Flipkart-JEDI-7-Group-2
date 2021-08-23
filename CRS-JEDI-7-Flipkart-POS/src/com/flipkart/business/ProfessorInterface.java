package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.util.List;

public interface ProfessorInterface {

    public void addGrades(String studentId, String CourseCode, String grade);

    public List<EnrolledStudent> viewEnrolledStudents(String profId);

    public List<Course> getCourses(String profId);

}
