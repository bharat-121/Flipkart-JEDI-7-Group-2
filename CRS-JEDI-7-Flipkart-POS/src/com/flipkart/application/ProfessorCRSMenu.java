package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author JEDI-07
 * Class that display Professor Client Menu
 *
 */
public class ProfessorCRSMenu {
    ProfessorInterface professorInterface = ProfessorOperations.getInstance();

    /**
     * Method to create Professor menu
     * @param profId: professor id obtained after logging into the system
     * returns displays all the options for the professor, and provides navigation
     */

    public void createMenu(String profId) {
        //Display the options available for the PRofessor
        Scanner sc = new Scanner(System.in);

        int input;
        while (CRSApplication.loggedIn) {
            System.out.println("**********Professor Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Logout");
            System.out.println("*****************************");

            //input user
            input = sc.nextInt();
            switch (input) {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    break;
                case 2:
                    //view all the enrolled students for the course
                    viewEnrolledStudents(profId);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    //logout from the system
                    CRSApplication.loggedIn = false;
                    return;
                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }


    }
    /**
     * Method to help Professor grade a student
     * @param profId
     */
    private void addGrade(String profId) {
        Scanner sc = new Scanner(System.in);

        String studentId;
        String courseCode, grade;

        List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
        try {
            enrolledStudents = professorInterface.viewEnrolledStudents(profId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("%20s %20s %20s", "COURSE CODE", "COURSE NAME", "Student ID"));
        for (EnrolledStudent obj : enrolledStudents) {
            System.out.println(String.format("%20s %20s %20s", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId()));
        }

        List<Course> coursesEnrolled = new ArrayList<Course>();
        coursesEnrolled = professorInterface.getCourses(profId);
        System.out.println("----------------Add Grade--------------");
        System.out.println("Enter student id");
        studentId = sc.next();
        System.out.println("Enter course code");
        courseCode = sc.next();
        System.out.println("Enter grade");
        grade = sc.next();
        try {
            professorInterface.addGrades(studentId, courseCode, grade);
        } catch (GradeNotAddedException e) {
            e.printStackTrace();
        }
        System.out.println("Grade added successfully for " + studentId);
    }
    /**
     * Method to view enrolled Students in courses
     * @param profId
     */
    private void viewEnrolledStudents(String profId) {

        List<EnrolledStudent> enrolledStudents = null;
        try {
            enrolledStudents = professorInterface.viewEnrolledStudents(profId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (enrolledStudents != null && enrolledStudents.size() == 0) {
            System.out.println("No students enrolled");
        } else {
            System.out.println(String.format("%20s %20s %20s", "COURSE CODE", "COURSE NAME", "STUDENT ID"));
            for (EnrolledStudent obj : enrolledStudents) {
                System.out.println(String.format("%20s %20s %20s", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId()));
            }
        }
    }
    /**
     * Method to get list of all Courses Professor has to teach
     * @param profId
     */
    private void getCourses(String profId) {
        List<Course> coursesEnrolled = professorInterface.getCourses(profId);
        if(coursesEnrolled!=null && coursesEnrolled.size()==0){
            System.out.println("No courses found!!");
        }
        else {
            System.out.println(String.format("%20s %20s", "COURSE CODE", "COURSE NAME"));
            for (Course obj : coursesEnrolled) {
                System.out.println(String.format("%20s %20s", obj.getCourseCode(), obj.getCourseName()));
            }
        }
    }
}
