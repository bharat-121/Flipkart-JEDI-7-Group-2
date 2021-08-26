package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;

import java.util.List;

public interface AdminInterface {

    public void deleteCourse(String courseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course, List<Course> courseList);

    public void approveStudent(String studentId, List<Student> studentList);

    public void addProfessor(Professor professor);

    public void assignCourse(String courseCode, String professorId);

    public List<Course> viewCourses();

    public List<Student> viewPendingAdmissions();
//
//    public GradeCard generateGradeCard(String studentId);



}
