package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public interface RegistartionInterface {
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList);

    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList);

    public List<Course> viewCourses(String studentId);

    public double calculateFee(String  studentId);

    boolean getRegistrationStatus(String studentId);

    void setRegistrationStatus(String studentId);

    List<Course> viewRegisteredCourses(String studentId);
}
