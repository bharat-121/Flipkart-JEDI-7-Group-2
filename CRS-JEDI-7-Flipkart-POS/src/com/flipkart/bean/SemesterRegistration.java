package com.flipkart.bean;

public class SemesterRegistration {

    private String studentId;
    private int semester;
    private String dateOfRegistration;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

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
