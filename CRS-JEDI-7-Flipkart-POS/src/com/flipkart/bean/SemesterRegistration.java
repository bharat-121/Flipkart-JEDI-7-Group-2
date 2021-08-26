package com.flipkart.bean;

/**
 * @author JEDI-07
 * Class to store Semester Registartion information
 */


public class SemesterRegistration {

    private String studentId;
    private int semester;
    private String dateOfRegistration;

    /**
     * Method to get studentId
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
     * Method to get dateOfRegistration
     * @return dateOfRegistration
     */
    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    /**
     * Method to set dateOfRegistration
     * @param dateOfRegistration
     */
    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @Override
    public String toString() {
        return "SemesterRegistration{" +
                "studentId='" + studentId + '\'' +
                ", semester=" + semester +
                ", dateOfRegistration='" + dateOfRegistration + '\'' +
                '}';
    }
}
