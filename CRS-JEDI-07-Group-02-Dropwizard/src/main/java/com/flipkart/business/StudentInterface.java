package com.flipkart.business;

import com.flipkart.bean.GradeCard;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.List;

/**
 *
 * @author JEDI-07
 * Interface for Student Operations
 *
 */

public interface StudentInterface {

    /**
     * Method to register Student
     * @param name
     * @param userID
     * @param password
     * @param department
     * @param email
     * @param phone
     * @param role
     * @throws StudentNotRegisteredException
     */
    public String register(String name,String userID,String password,String department, String email , String phone , String role) throws StudentNotRegisteredException;

    /**
     * Method to viewGradeCard using studentId
     * @param studentId
     * @return List of GradCard
     */
    public List<GradeCard> viewGradeCard(String studentId);

    public boolean payFees(String studentId);

}
