package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public class RegistrationOperation implements RegistartionInterface{
    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    @Override
    public List<Course> viewCourses(int studentId) {
        return null;
    }

    @Override
    public double calculateFee(int studentId) {
        return 0;
    }
}
