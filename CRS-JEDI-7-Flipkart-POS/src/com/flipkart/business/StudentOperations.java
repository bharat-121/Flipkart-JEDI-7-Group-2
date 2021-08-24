package com.flipkart.business;

import com.flipkart.bean.GradeCard;

public class StudentOperations implements StudentInterface {
    public StudentOperations() {
        super();
    }

    @Override
    public int register(String name, String userID, String password, int semester, String branch) {
        return 0;
    }

    @Override
    public boolean isApproved(int studentId) {
        return false;
    }

    @Override
    public void viewRegisteredCourses(String studentId) {

    }

    @Override
    public GradeCard viewGradeCard(String studentId) {
        return null;
    }
}
