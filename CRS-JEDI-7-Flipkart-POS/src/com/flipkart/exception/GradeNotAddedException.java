package com.flipkart.exception;
/**
 * Exception to check if student has been alloted grade by professor
 * @author JEDI-07-Group2
 *
 */
public class GradeNotAddedException extends Exception{

    private String studentId;

    /**
     * Constructor
     * @param studentId
     */
    public GradeNotAddedException(String studentId)
    {
        this.studentId=studentId;
    }

    /**
     * Getter function for studentId
     * @return
     */
    public String getStudentId()
    {
        return studentId;
    }

    @Override
    public String getMessage() {
        return "Grade of studentID " + studentId + "not added";
    }
}