package com.flipkart.bean;

/**
 *
 * @author JEDI-07
 * Class for storing details of course Student has taken
 *
 */

public class EnrolledStudent {
    private String studentId;
    private String courseCode;
    private String courseName;

    /**
     * Parameterized constructor
     * @param courseCode
     * @param courseName
     * @param studentId
     */

    public EnrolledStudent(String studentId, String courseCode, String courseName) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    /**
     * Method to get Student Id of student enrolled in the course
     * @return Student Id: student id
     */

    public String getStudentId() {
        return studentId;
    }

    /**
     * Method to set Student Id of student enrolled in the course
     * @param studentId
     */

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get Course Code
     * @return Course Name: course code
     */

    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set Course Code
     * @param courseCode: course code
     */

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Method to get Course Name
     * @return Course Name: course name
     */

    public String getCourseName() {
        return courseName;
    }


    /**
     * Method to set Course Name
     * @param courseName: course name
     */

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "EnrolledStudent{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }
}
