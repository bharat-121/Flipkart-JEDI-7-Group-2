package com.flipkart.bean;

public class Student extends User{
    private String department;
    private int semester;
    private boolean isApproved;

    public Student(String department, int semester, boolean isApproved) {
        this.department = department;
        this.semester = semester;
        this.isApproved = isApproved;
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

    public boolean isApproved() {
        return isApproved;
    }

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
