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

public class ProfessorCRSMenu {
    ProfessorInterface professorInterface = ProfessorOperations.getInstance();

    public void createMenu(String profId) {
        //Display the options available for the PRofessor
        Scanner sc = new Scanner(System.in);

        int input;
        while (CRSApplication.loggedin) {
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
                    CRSApplication.loggedin = false;
                    return;
                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }


    }

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

    private void viewEnrolledStudents(String profId) {
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try {
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(EnrolledStudent obj: enrolledStudents)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
            }
    }

    private void getCourses(String profId) {
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s","COURSE CODE","COURSE NAME" ));
        for(Course obj: coursesEnrolled)
        {
            System.out.println(String.format("%20s %20s",obj.getCourseCode(), obj.getCourseName()));
        }
    }
}
