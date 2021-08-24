package com.flipkart.bean;

public class Course {
    private String courseCode;
    private String courseName;
    private String instructorId;
    private final int seats = 10;

    public Course(String courseCode, String courseName, String instructorId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
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

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", seats=" + seats +
                '}';
    }
}
