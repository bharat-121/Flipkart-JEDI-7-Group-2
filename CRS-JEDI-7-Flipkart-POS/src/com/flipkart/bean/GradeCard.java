package com.flipkart.bean;

import java.util.List;

public class GradeCard {
    private String studentId;
    private int semester;
    private float cgpa;
    private List<RegisteredCourse> registeredCourses;

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

    public List<RegisteredCourse> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
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
