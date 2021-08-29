package com.flipkart.exception;

/**
 * Exception to check if student is not registered
 * @author JEDI-07-Group2
 *
 */
public class StudentNotRegisteredException extends Exception{
    private String studentName;

    public StudentNotRegisteredException(String studentName)
    {
        this.studentName=studentName;
    }

    /**
     * getter function for studentName
     * @return studentName
     */
    public String getStudentName()
    {
        return studentName;
    }

    @Override
    public String getMessage() {
        return studentName + "not registered";
    }
}