package com.flipkart.business;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.Role;

import java.util.List;

public class StudentOperations implements StudentInterface {
    public StudentOperations() {
        super();
    }

    private static StudentOperations instance=null;

    public static StudentOperations getInstance()
    {
        if(instance==null)
        {

                instance=new StudentOperations();

        }
        return instance;
    }

    /**
     * Method to register Student
     * @param name
     * @param userID
     * @param password
     * @param semester
     * @param branch
     * @return studnetId
     */
    @Override
    public int register(String name, String userID, String password, int semester, String branch) {
        return 0;
    }

    /**
     * Method to check student approved by admin or not
     * @param studentId
     * @return boolean indicate if student is approved
     */
    @Override
    public boolean isApproved(int studentId) {

        return false;
    }

    /**
     * Method to view RegisteredCourses using studentId
     * @param studentId
     */
    @Override
    public void viewRegisteredCourses(String studentId) {

    }

    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    @Override
    public List<GradeCard> viewGradeCard(String studentId) {
        return null;
    }



}
