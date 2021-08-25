package com.flipkart.business;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.Role;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperations;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;

import java.util.List;

public class StudentOperations implements StudentInterface {


    private static StudentOperations instance=null;
    StudentDaoInterface studentDaoInterface= StudentDaoOperation.getInstance();
    //RegistartionInterface registartionDaoInterface= RegistrationDaoOperations.getInstance();

    public StudentOperations() {
        super();
    }
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
    public String register(String name, String userID, String password, int semester, String branch) {
        String studentId="";
        try
        {
            //call the DAO class, and add the student record to the DB
           // Student newStudent=new Student(userID,name,Role.STUDENT,password,branch);

            //studentId=studentDaoInterface.register(newStudent);

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return studentId;

    }

    /**
     * Method to check student approved by admin or not
     * @param studentId
     * @return boolean indicate if student is approved
     */
    @Override
    public boolean isApproved(int studentId) {

        return studentDaoInterface.isApproved(studentId);
    }

    /**
     * Method to view RegisteredCourses using studentId
     * @param studentId
     */
    @Override
    public void viewRegisteredCourses(String studentId) {
        //return registrationDaoInterface.viewRegisteredCourses(studentId);
    }

    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    @Override
    public List<GradeCard> viewGradeCard(String studentId) {
        //return registrationDaoInterface.viewGradeCard(studentId);
        return null;
    }



}
