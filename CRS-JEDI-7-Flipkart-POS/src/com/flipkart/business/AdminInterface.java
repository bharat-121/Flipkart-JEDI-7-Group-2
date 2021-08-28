package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.List;

/**
 * @author JEDI-07
 * Interface for Admin Operations
 */

public interface AdminInterface {
    /**
     * Method to Delete Course from Course Catalog
     *
     * @param courseCode
     * @param courseList
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void deleteCourse(String courseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Method to add Course to Course Catalog
     *
     * @param course     : Course object storing details of a course
     * @param courseList : Courses available in the catalog
     */
    public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

    /**
     * Method to approve a Student
     *
     * @param studentId
     * @param studentList
     * @throws StudentNotFoundForApprovalException
     */

    public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;


    /**
     * Method to add Professor to DB
     *
     * @param professor : Professor Object storing details of a professor
     * @throws ProfessorNotAddedException
     * @throws UserIdAlreadyInUseException
     */

    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    /**
     * Method to assign Course to a Professor
     *
     * @param courseCode
     * @param professorId
     * @throws CourseNotFoundException
     * @throws UserNotFoundException
     */

    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;


    /**
     * Method to get list of courses in catalog
     *
     * @return List of courses in catalog
     */

    public List<Course> viewCourses();

    /**
     * Method to view Students yet to be approved by Admin
     *
     * @return List of Students with pending admissions
     */

    public List<Student> viewPendingAdmissions();

}
