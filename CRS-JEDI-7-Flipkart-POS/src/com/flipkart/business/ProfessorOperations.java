package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.util.List;

public class ProfessorOperations implements ProfessorInterface {
    public ProfessorOperations() {
        super();
    }

    @Override
    public void addGrades(String studentId, String CourseCode, String grade) {

    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        return null;
    }

    @Override
    public List<Course> getCourses(String profId) {
        return null;
    }
}
