package com.flipkart.exception;

/**
 * Exception course is deleted from catalog
 * @author JEDI-07-Group2
 *
 */
public class CourseNotDeletedException extends Exception{
    private String courseCode;

    public CourseNotDeletedException(String courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * Getter function for course code
     * @return
     */
    public String getCourseCode()
    {
        return courseCode;
    }

    /**
     * Message thrown by exception
     */
    @Override
    public String getMessage()
    {
        return "Course with courseCode: " + courseCode + " not available in catalog.";
    }
}