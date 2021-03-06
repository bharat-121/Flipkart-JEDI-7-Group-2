package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;

/**
 * @author JEDI-07
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {

    /**
     * Delete Course using SQL commands
     * @param courseCode
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void deleteCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Add Course using SQL commands
     * @param course
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;
    /**
     * Approve Student using SQL commands
     * @param studentId
     * @throws StudentNotFoundForApprovalException
     */
    public void approveStudent(String studentId) throws StudentNotFoundForApprovalException;

    /**
     * Add professor using SQL commands
     * @param professor
     * @throws ProfessorNotAddedException
     * @throws UserIdAlreadyInUseException
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;;

    /**
     * Method to add user using SQL commands
     * @param user
     * @throws UserNotAddedException
     * @throws UserIdAlreadyInUseException
     */
    public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException;

    /**
     * Assign courses to Professor using SQL commands
     * @param courseCode
     * @param professorId
     * @throws CourseNotFoundException
     * @throws UserNotFoundException
     */
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;

    /**
     * Fetch Students yet to approved using SQL commands
     * @return List of Students yet to approved
     */
    public List<Student> viewPendingAdmissions();

    /**
     * View courses in the catalog
     * @return List of courses in the catalog
     */
    public List<Course> viewCourses();

    /**
     * View professor in the institute
     * @return List of the professors in the institute
     */
    public List<Professor> viewProfessors();
}