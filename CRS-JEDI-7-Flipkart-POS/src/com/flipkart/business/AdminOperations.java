package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public class AdminOperations implements AdminInterface {

    public AdminOperations() {
        super();
    }

    //delete course from courseList using courseCode
    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {

    }

    // add new course into courseList
    @Override
    public void addCourse(Course course, List<Course> courseList) {

    }


    @Override
    public void approveStudent(String studentId, List<Student> studentList) {

    }

    @Override
    public void addProfessor(Professor professor, List<Professor> professorList) {

    }

    @Override
    public void assignCourse(String courseCode, String professorId) {

    }

    @Override
    public List<Course> viewCourses() {
        return null;
    }

    @Override
    public GradeCard generateGradeCard(String studentId) {
        return null;
    }
}
