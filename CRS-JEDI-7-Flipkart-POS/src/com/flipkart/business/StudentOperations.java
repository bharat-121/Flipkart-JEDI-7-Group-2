package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperations;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.StudentNotRegisteredException;

import java.sql.SQLException;
import java.util.List;

public class StudentOperations implements StudentInterface {


    private static StudentOperations instance=null;
    StudentDaoInterface studentDaoInterface= StudentDaoOperation.getInstance();
    RegistrationDaoInterface registrationDaoInterface= RegistrationDaoOperations.getInstance();

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


    @Override
    public String register(String name,String userID,String password,int semester,String department, String email , String phone , String role) throws StudentNotRegisteredException{

        String studentId = null;
        try
        {
            //call the DAO class, and add the student record to the DB
            Student newStudent=new Student();
            newStudent.setApproved(false);
            newStudent.setDepartment(department);
            newStudent.setSemester(semester);
            newStudent.setEmail(email);
            newStudent.setName(name);
            newStudent.setPassword(password);
            newStudent.setRole(role);
            newStudent.setUserID(userID);
            newStudent.setPhone(phone);

             studentId=studentDaoInterface.register(newStudent);

        }
        catch(StudentNotRegisteredException ex)
        {
            throw ex;
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
     * @return
     */
    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewRegisteredCourses(studentId);
    }

    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    @Override
    public void viewGradeCard(String studentId) {
        studentDaoInterface.viewGradeCard(studentId);
    }



}
