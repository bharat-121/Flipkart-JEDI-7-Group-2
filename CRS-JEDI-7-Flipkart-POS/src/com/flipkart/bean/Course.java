package com.flipkart.bean;

/**
 *
 * @author JEDI-07
 * Class to store Course information
 *
 */

public class Course {
    private String courseCode;
    private String courseName;
    private String instructorId;
    private int seats = 10;

    /**
     * Parameterized constructor
     * @param courseCode: course code
     * @param courseName: course name
     * @param instructorId: instructor user id
     * @param seats: seats available
     */

    public Course(String courseCode, String courseName, String instructorId,int seats) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.seats=seats;
    }

    /**
     * Parameterized constructor
     * @param courseCode
     * @param courseName
     * @param instructorId
     */
    public Course(String courseCode, String courseName, String instructorId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
    }
    /**
     * Default Constructor
     */
    public Course() {

    }



    /**
     * Method to get Course Code
     * @return Course Code
     */

    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set Course Code
     * @param courseCode
     */

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Method to get Course Name
     * @return Course Name
     */

    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set Course Name
     * @param courseName
     */

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get available seats
     * @return Seats available
     */

    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Method to get Instructor Id of professor teaching the course
     */
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    /**
     * Method to get available seats
     */

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
