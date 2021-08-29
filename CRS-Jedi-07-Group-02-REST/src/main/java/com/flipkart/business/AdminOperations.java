package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperations;
import com.flipkart.exception.*;
import com.flipkart.validator.AdminValidator;
import org.apache.log4j.Logger;
//import com.sun.glass.ui.Clipboard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JEDI-07
 * Implementations of Admin Operations
 */

public class AdminOperations implements AdminInterface {
    static AdminOperations instance = null;
    private static Logger logger = Logger.getLogger(AdminOperations.class);

    private AdminOperations() {
        super();
    }

    /**
     * Method to make AdminOperation Singleton
     */
    public static AdminInterface getInstance() {
        if (instance == null) {
            instance = new AdminOperations();
        }
        return instance;
    }

    AdminDaoInterface adminDaoOperation = AdminDaoOperations.getInstance();

    public List<Student> viewPendingAdmissions() {
        return adminDaoOperation.viewPendingAdmissions();
    }

    /**
     * Method to Delete Course from Course Catalog
     *
     * @param dropCourseCode
     * @param courseList     : Courses available in the catalog
     * @throws CourseNotFoundException
     */
    public void deleteCourse(String dropCourseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException {

        if (!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
            logger.error("courseCode: " + dropCourseCode + " not present in catalog!");
            throw new CourseNotFoundException(dropCourseCode);

        }

        try {
            adminDaoOperation.deleteCourse(dropCourseCode);
        } catch (CourseNotFoundException | CourseNotDeletedException e) {
            throw e;
        }

    }

    /**
     * Method to add Course to Course Catalog
     *
     * @param newCourse  : Course object storing details of a course
     * @param courseList : Courses available in catalog
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course newCourse, List<Course> courseList) throws CourseFoundException {

        if (!AdminValidator.isValidNewCourse(newCourse, courseList)) {
            logger.error("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
            throw new CourseFoundException(newCourse.getCourseCode());
        }

        try {
            adminDaoOperation.addCourse(newCourse);
        } catch (CourseFoundException e) {
            throw e;
        }

    }

    /**
     * Method to approve a Student
     *
     * @param studentId
     * @param studentList
     * @throws StudentNotFoundForApprovalException
     */
    @Override
    public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

        if (!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
            logger.error("studentId: " + studentId + " is already approvet/not-present!");
            throw new StudentNotFoundForApprovalException(studentId);
        }

        try {
            adminDaoOperation.approveStudent(studentId);
        } catch (StudentNotFoundForApprovalException e) {
            throw e;
        }
    }

    /**
     * Method to add Professor to DB
     *
     * @param professor : Professor Object storing details of a professor
     * @throws ProfessorNotAddedException
     * @throws UserIdAlreadyInUseException
     */
    @Override
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

        try {
            adminDaoOperation.addProfessor(professor);
        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            throw e;
        }

    }

    /**
     * Method to assign Course to a Professor
     *
     * @param courseCode
     * @param professorId
     * @throws CourseNotFoundException
     * @throws UserNotFoundException
     */
    @Override
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException {

        try {
            adminDaoOperation.assignCourse(courseCode, professorId);
        } catch (CourseNotFoundException | UserNotFoundException e) {
            throw e;
        }

    }

    /**
     * Method to get list of courses in catalog
     *
     * @return List of courses in catalog
     */
    @Override
    public List<Course> viewCourses() {

        return adminDaoOperation.viewCourses();

    }
}
