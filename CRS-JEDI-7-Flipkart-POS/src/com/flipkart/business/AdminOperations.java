package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations implements AdminInterface {
    static AdminOperations instance = null;
    public AdminOperations() {
        super();
    }

    public static AdminInterface getInstance() {
        if(instance==null){
            instance= new AdminOperations();
        }
        return instance;
    }

    public static List<Student> viewPendingAdmissions() {
        return null;
    }

    //delete course from courseList using courseCode
    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {
//        courseList.remove(new String(courseCode));
    }

    // add new course into courseList
    @Override
    public void addCourse(Course course, List<Course> courseList) {

    }


    @Override
    public void approveStudent(String studentId, List<Student> studentList) {

    }

    @Override
    public void addProfessor(Professor professor) {

    }

    @Override
    public void assignCourse(String courseCode, String professorId) {

    }

    @Override
    public List<Course> viewCourses() {
        return new ArrayList<Course>();
    }

    @Override
    public GradeCard generateGradeCard(String studentId) {
        return null;
    }
}
