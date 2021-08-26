package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.List;

public interface StudentDaoInterface {


    public String register(Student student) throws StudentNotRegisteredException;


    public boolean isApproved(int studentId);

    public void viewRegisteredCourses(String studentId);
    public void viewGradeCard(String studentId);
}
