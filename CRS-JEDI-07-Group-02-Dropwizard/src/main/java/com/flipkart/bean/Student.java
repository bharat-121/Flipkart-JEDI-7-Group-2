package com.flipkart.bean;

/**
 *
 * @author JEDI-07
 * Student Class
 *
 */

public class Student extends User{
    private String department;
    private int semester;
    private boolean isApproved;

    /**
     * Paramaterized Constructor
     * @param department
     * @param semester
     * @param isApproved
     */

    public Student(String department, int semester, boolean isApproved) {
        this.department = department;
        this.semester = semester;
        this.isApproved = isApproved;
    }

    /**
     * Default Constructor
     */
    public Student() {
    }

    /**
     * Method to get Department
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     *  Method to set Department
     * @param department
     */

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *  Method to get semester
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
     *  Method to get Approval
     * @return isApproved
     */

    public boolean isApproved() {
        return isApproved;
    }

    /**
     *  Method to set Approval
     * @param approved
     */

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public String toString() {
        return "Student{" +
                "department='" + department + '\'' +
                ", semester=" + semester +
                ", isApproved=" + isApproved +
                '}';
    }
}
