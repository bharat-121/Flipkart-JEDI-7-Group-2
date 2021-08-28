package com.flipkart.bean;

import com.flipkart.constants.Grade;

import java.util.HashMap;
import java.util.List;

/**
 * @author JEDI-07
 * Class to store Student Grade information
 */

public class GradeCard {
    private String courseCode;
    private String courseName;
    private String grade;

    public GradeCard(){};
    public GradeCard(String courseCode,String courseName,String grade)
    {
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.grade=grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "GradeCard{" +
                "courseCode'" + courseCode + '\'' +
                ", courseName=" + courseName +
                ", grade=" + grade +
                '}';
    }
}
