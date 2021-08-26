package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperations;
import com.flipkart.exception.*;
import com.flipkart.validator.AdminValidator;
//import com.sun.glass.ui.Clipboard;

import java.util.ArrayList;
import java.util.List;

public class AdminOperations implements AdminInterface {
    static AdminOperations instance = null;

    private AdminOperations() {
        super();
    }

    public static AdminInterface getInstance() {
        if(instance==null){
            instance= new AdminOperations();
        }
        return instance;
    }
    AdminDaoInterface adminDaoOperation = AdminDaoOperations.getInstance();

    public List<Student> viewPendingAdmissions() {
        return adminDaoOperation.viewPendingAdmissions();
    }

    //delete course from courseList using courseCode
    public void deleteCourse(String dropCourseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException {

        if(!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
            System.out.println("courseCode: " + dropCourseCode + " not present in catalog!");
            throw new CourseNotFoundException(dropCourseCode);

        }

        try {
            adminDaoOperation.deleteCourse(dropCourseCode);
        }
        catch(CourseNotFoundException | CourseNotDeletedException e) {
            throw e;
        }

    }

    /**
     * Method to add Course to Course Catalog
     * @param courseList : Courses available in catalog
     */
    @Override
    public void addCourse(Course newCourse, List<Course> courseList) throws CourseFoundException{

        if(!AdminValidator.isValidNewCourse(newCourse, courseList)) {
            System.out.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
            throw new CourseFoundException(newCourse.getCourseCode());
        }

        try {
            adminDaoOperation.addCourse(newCourse);
        }
        catch(CourseFoundException e) {
            throw e;
        }

    }

    /**
     * Method to approve a Student
     * @param studentId
     * @param studentList
     */
    @Override
    public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

        if(!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
            System.out.println("studentId: " + studentId + " is already approvet/not-present!");
            throw new StudentNotFoundForApprovalException(studentId);
        }

        try {
            adminDaoOperation.approveStudent(studentId);
        }
        catch(StudentNotFoundForApprovalException e) {
            throw e;
        }
    }

    /**
     * Method to add Professor to DB
     * @param professor : Professor Object storing details of a professor
     */
    @Override
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

        try {
            adminDaoOperation.addProfessor(professor);
        }
        catch(ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            throw e;
        }

    }

    /**
     * Method to assign Course to a Professor
     * @param courseCode
     * @param professorId
     */
    @Override
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{

        try {
            adminDaoOperation.assignCourse(courseCode, professorId);
        }
        catch(CourseNotFoundException | UserNotFoundException e) {
            throw e;
        }

    }

    /**
     * Method to get list of courses in catalog
     * @return List of courses in catalog
     */
    @Override
    public List<Course> viewCourses() {

        return adminDaoOperation.viewCourses();

    }
}
