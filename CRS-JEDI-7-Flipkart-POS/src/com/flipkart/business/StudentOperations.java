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
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(StudentOperations.class){
                instance=new StudentOperations();
            }
        }
        return instance;
    }

    @Override
    public int register(String name, String userID, String password, int semester, String branch) {

        int studentId;
        //call the DAO class, and add the student record to the DB
//        Student newStudent=new Student(userID,name, Role.STUDENT,password,semester,branch);
//        studentId=studentDaoInterface.addStudent(newStudent);
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
    public List<GradeCard> viewGradeCard(String studentId) {
        return null;
    }



}
