package com.flipkart.business;

import com.flipkart.bean.Course;

import java.util.List;

public class RegistrationOperation implements RegistartionInterface{

    static RegistrationOperation instance = null;

    public static RegistartionInterface getInstance() {
        if (instance == null) {
            instance = new RegistrationOperation();
        }
        return instance;
    }


    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    @Override
    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) {
        return false;
    }

    @Override
    public List<Course> viewCourses(String  studentId) {
        return null;
    }

    @Override
    public double calculateFee(String studentId) {
        return 0;
    }


    @Override
    public boolean getRegistrationStatus(String studentId) {
        return false;
    }

    @Override
    public void setRegistrationStatus(String studentId) {

    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) {
        return null;
    }

}
