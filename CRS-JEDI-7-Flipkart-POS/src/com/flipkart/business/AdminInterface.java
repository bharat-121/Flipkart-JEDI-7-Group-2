package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;

import java.util.List;

public interface AdminInterface {

    public void deleteCourse(String courseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course, List<Course> courseList)throws CourseFoundException;

    public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;

    public List<Course> viewCourses();

    public List<Student> viewPendingAdmissions();
//
//    public GradeCard generateGradeCard(String studentId);



}
