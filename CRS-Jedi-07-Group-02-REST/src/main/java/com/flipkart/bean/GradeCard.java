package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Class to store Student Grade information
 *
 */

public class GradeCard {
    private String studentId;
    private int semester;
    private float cgpa;
    private HashMap<RegisteredCourse, Grade> registeredCourses;

    /**
     * Parameterized Constructor
     * @param courseCode: course code
     * @param courseName: course name
     * @param grade: grade
     */

    public GradeCard(String courseCode, String courseName, String grade) {

    }

    /**
     * Mrthod to get student ID
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Method to set studentId
     * @param studentId
     */

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get semester
     * @return semester
     */

    public int getSemester() {
        return semester;
    }

    /**
     * Method to set semester
     * @param semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Method to get cgpa
     * @return cgpa
     */

    public float getCgpa() {
        return cgpa;
    }

    /**
     * Method to set cgpa
     * @param cgpa
     */

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    /**
     * Method to get registered courses
     * @return registeredCourses
     */

    public HashMap<RegisteredCourse, Grade> getRegisteredCourses() {
        return registeredCourses;
    }

    /**
     * Method to set registered courses
     * @param registeredCourses
     */

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
