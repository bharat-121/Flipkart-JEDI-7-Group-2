package com.flipkart.dao;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;
import com.flipkart.constants.Grade;
import com.flipkart.exception.FeeAlreadyPaidException;
import com.flipkart.exception.StudentNotRegisteredException;

import java.util.List;

/**
 * @author JEDI-07
 * Interface for Student Operations
 */

public interface StudentDaoInterface {

    /**
     * Method to add student to database
     *
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     * @throws StudentNotRegisteredException
     */
    public String register(Student student) throws StudentNotRegisteredException;

    /**
     * Method to view grade card
     * @param studentId: studentId
     * @return void
     */
    public List<GradeCard> viewGradeCard(String studentId);

    /**
     * Method to pay the fee
     * @param studentId: studentId
     * @return boolean
     */
    public boolean payFees(String studentId) throws FeeAlreadyPaidException;
}
