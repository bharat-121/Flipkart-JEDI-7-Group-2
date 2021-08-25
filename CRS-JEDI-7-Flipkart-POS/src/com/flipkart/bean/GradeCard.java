package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.HashMap;
import java.util.List;

public class GradeCard {
    private String studentId;
    private int semester;
    private float cgpa;
    private HashMap<RegisteredCourse, Grade> registeredCourses;

    public GradeCard(String courseCode, String courseName, String grade) {

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public HashMap<RegisteredCourse, Grade> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(HashMap<RegisteredCourse, Grade> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    @Override
    public String toString() {
        return "GradeCard{" +
                "studentId='" + studentId + '\'' +
                ", semester=" + semester +
                ", cgpa=" + cgpa +
                ", registeredCourses=" + registeredCourses +
                '}';
    }
}
