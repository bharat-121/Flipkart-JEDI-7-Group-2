package com.flipkart.bean;

import java.util.List;

public class Student extends User{
    private String department;
    private int semester;
    private List<Course> registeredCourse;

    public Student(String department, int semester, List<Course> registeredCourse) {
        this.department = department;
        this.semester = semester;
        this.registeredCourse = registeredCourse;
    }

    public Student() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<Course> getRegisteredCourse() {
        return registeredCourse;
    }

    public void setRegisteredCourse(List<Course> registeredCourse) {
        this.registeredCourse = registeredCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "department='" + department + '\'' +
                ", semester=" + semester +
                ", registeredCourse=" + registeredCourse +
                '}';
    }
}
