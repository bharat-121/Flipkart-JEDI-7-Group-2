package com.flipkart.bean;

/**
 * @author JEDI-07
 * Class to store Registered Courses information
 */

public class RegisteredCourse {
    private String courseCode;
    private int semester;
    private String grade;
    private String studentId;

    /**
     * Method to get Course Code
     * @return courseCode
     */

    public String getCourseCode() {
        return courseCode;
    }

    /**
     *  Method to set Course Code
     * @param courseCode
     */

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     *  Method to get Semester
     * @return semester
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Method to set Semester
     * @param semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Method to get Grade
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Method to set grade
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Method to get studentid
     * @return studentId
     */

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "RegisteredCourse{" +
                "courseCode='" + courseCode + '\'' +
                ", semester=" + semester +
                ", grade='" + grade + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


}
