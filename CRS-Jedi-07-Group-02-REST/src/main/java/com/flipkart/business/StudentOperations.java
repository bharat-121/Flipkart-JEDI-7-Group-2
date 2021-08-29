package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperations;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.FeeAlreadyPaidException;
import com.flipkart.exception.StudentNotRegisteredException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JEDI-07
 * Implementations of Student Operations
 *
 */

public class StudentOperations implements StudentInterface {


    private static StudentOperations instance=null;
    StudentDaoInterface studentDaoInterface= StudentDaoOperation.getInstance();
    private static Logger logger = Logger.getLogger(StudentOperations.class);

    public StudentOperations() {
        super();
    }

    /**
     * Method to make StudentOperation Singleton
     * @return
     */

    public static StudentOperations getInstance()
    {
        if(instance==null)
        {

                instance=new StudentOperations();

        }
        return instance;
    }

    /**
     * Method to register a student, although student can't login until it's approved by admin
     * @param name
     * @param userID
     * @param password
     * @param department
     * @param email
     * @param phone
     * @param role
     * @return
     * @throws StudentNotRegisteredException
     */

    @Override
    public String register(String name,String userID,String password,String department, String email , String phone , String role) throws StudentNotRegisteredException {

        String studentId = null;
        try {
            //call the DAO class, and add the student record to the DB
            Student newStudent = new Student();
            newStudent.setApproved(false);
            newStudent.setDepartment(department);
            newStudent.setEmail(email);
            newStudent.setName(name);
            newStudent.setPassword(password);
            newStudent.setRole(role);
            newStudent.setUserID(userID);
            newStudent.setPhone(phone);

            studentId = studentDaoInterface.register(newStudent);

        } catch (StudentNotRegisteredException ex) {
            throw ex;
        }
        return studentId;

    }
    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    @Override
    public List<GradeCard> viewGradeCard(String studentId) {
        return studentDaoInterface.viewGradeCard(studentId);
    }

    @Override
    public boolean payFees(String studentId) {
        try {
            return studentDaoInterface.payFees(studentId);
        }
        catch (FeeAlreadyPaidException ex){
            logger.error(ex.getMessage());
        }
        return false;
    }


}
