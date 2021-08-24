package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorOperations implements ProfessorInterface {
    public ProfessorOperations() {
        super();
    }

    static ProfessorOperations instance = null;

    public static ProfessorInterface getInstance() {
        if (instance == null) {
            instance = new ProfessorOperations();
        }
        return instance;
    }

    @Override
    public void addGrades(String studentId, String CourseCode, String grade) {

        System.out.println("Successfully Added Grades");
        System.out.println("StudentId- " + studentId);
        System.out.println("Course Code - " + CourseCode);
        System.out.println("Grade - " + grade);
    }

    @Override
    public List<EnrolledStudent> viewEnrolledStudents(String profId) {
        List<EnrolledStudent> enrolledStudents = new ArrayList<>();
        enrolledStudents.add(new EnrolledStudent("S1", "MAIR101", "Maths"));
        enrolledStudents.add(new EnrolledStudent("S2", "MAIR102", "Science"));
        enrolledStudents.add(new EnrolledStudent("S3", "MAIR103", "Computer"));
        enrolledStudents.add(new EnrolledStudent("S4", "MAIR104", "Java"));
        enrolledStudents.add(new EnrolledStudent("S5", "MAIR105", "C++"));

        List<EnrolledStudent> output = new ArrayList<>();

        for (EnrolledStudent enrolledStudent: enrolledStudents) {
            if(true){
                output.add(enrolledStudent);
            }
        }
        return output;
    }

    @Override
    public List<Course> getCourses(String profId) {

        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("MAIR101", "Maths", "P1"));
        courseList.add(new Course("MAIR102", "Science", "P1"));
        courseList.add(new Course("MAIR103", "Computer", "P2"));
        courseList.add(new Course("MAIR104", "Java", "P2"));
        courseList.add(new Course("MAIR105", "C++", "P3"));

        List<Course> output = new ArrayList<>();

        for (Course course: courseList) {
            if(course.getInstructorId().equals(profId)){
                output.add(course);
            }
        }
        return output;
    }

    @Override
    public Professor getProfessorById(String instructorId) {
        return null;
    }
}
