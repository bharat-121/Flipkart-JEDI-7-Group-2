package com.flipkart.business;

import com.flipkart.bean.GradeCard;

import java.util.List;

public interface StudentInterface {

    public int register(String name,String userID,String password,int semester,String branch);

    public boolean isApproved(int studentId);

    public void viewRegisteredCourses(String studentId);

    public List<GradeCard> viewGradeCard(String studentId);

}
