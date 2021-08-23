package com.flipkart.bean;

public class RegisteredCourse {
    private String courseCode;
    private int semester;
    private String grade;
    private String studentId;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

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
