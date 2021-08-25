package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;

import java.util.List;

public interface StudentDaoInterface {


    public String register(Student student) ;


    public boolean isApproved(int studentId);

    public void viewRegisteredCourses(String studentId);
    public List<GradeCard> viewGradeCard(String studentId);
}
