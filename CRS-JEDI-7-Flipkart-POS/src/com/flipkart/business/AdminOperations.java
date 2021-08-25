package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.sun.glass.ui.Clipboard;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations implements AdminInterface {
    static AdminOperations instance = null;

    private static List<Course> courseList;

    private AdminOperations() {
        super();
    }

    public static AdminInterface getInstance() {
        if(instance==null){
            instance= new AdminOperations();
            courseList = new ArrayList<>();
            courseList.add(new Course("MAIR101", "Maths", "P1"));
            courseList.add(new Course("MAIR102", "Science", "P1"));
            courseList.add(new Course("MAIR103", "Computer", "P2"));
            courseList.add(new Course("MAIR104", "Java", "P2"));
            courseList.add(new Course("MAIR105", "C++", "P3"));
        }
        return instance;
    }

    public static List<Student> viewPendingAdmissions() {
        return null;
    }

    //delete course from courseList using courseCode
    @Override
    public void deleteCourse(String courseCode, List<Course> courseList) {
        for (Course course: courseList) {
            if (course.getCourseCode().equals(courseCode)){
                courseList.remove(course);
                break;
            }
        }
    }

    // add new course into courseList
    @Override
    public void addCourse(Course course, List<Course> courseList) {
        courseList.add(course);
    }


    @Override
    public void approveStudent(String studentId, List<Student> studentList) {

    }

    @Override
    public void addProfessor(Professor professor) {
        System.out.println("Professor Sucessfully Added");
    }

    @Override
    public void assignCourse(String courseCode, String professorId) {

    }

    @Override
    public List<Course> viewCourses() {
        return courseList;
    }

    @Override
    public GradeCard generateGradeCard(String studentId) {
        return null;
    }
}
