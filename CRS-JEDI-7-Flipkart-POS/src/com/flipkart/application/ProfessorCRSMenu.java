package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.exception.GradeNotAddedException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Colors.*;

/**
 *
 * @author JEDI-07
 * Class that display Professor Client Menu
 *
 */
public class ProfessorCRSMenu {
    ProfessorInterface professorInterface = ProfessorOperations.getInstance();
    private static Logger logger = Logger.getLogger(ProfessorCRSMenu.class);

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
            System.out.println(GREEN_BRIGHT+"*****************************");
            System.out.println(GREEN_BRIGHT+"**********Professor Menu*********");
            System.out.println(GREEN_BRIGHT+"*****************************");
            System.out.println(ANSI_CYAN+"1."+CYAN_BRIGHT+"   View Courses"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"2."+CYAN_BRIGHT+"   View Enrolled Students"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"3."+CYAN_BRIGHT+"   Add grade"+ANSI_RESET);
            System.out.println(ANSI_CYAN+"4."+CYAN_BRIGHT+"   Logout"+ANSI_RESET);
            System.out.println(GREEN_BRIGHT+"*****************************");

            //input user
            System.out.print(ANSI_RED+"Enter your choice :-"+ANSI_RESET);
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
                    System.out.println(RED_BRIGHT+"***** Wrong Choice *****"+ANSI_RESET);
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
        System.out.println(ANSI_RED+String.format("%20s %20s %20s", "COURSE CODE", "COURSE NAME", "Student ID")+ANSI_RESET);
        for (EnrolledStudent obj : enrolledStudents) {
            System.out.println(ANSI_CYAN+String.format("%20s %20s %20s", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId())+ANSI_RESET);
        }

        List<Course> coursesEnrolled = new ArrayList<Course>();
        coursesEnrolled = professorInterface.getCourses(profId);
        System.out.println(RED_BRIGHT+"---------- Add Grade ----------"+ANSI_RESET);
        System.out.print(ANSI_RED+"Enter student id  :- "+ANSI_RESET);
        studentId = sc.next();
        System.out.print(ANSI_RED+"Enter course code :- "+ANSI_RESET);
        courseCode = sc.next();
        System.out.print(ANSI_RED+"Enter grade      :- "+ANSI_RESET);
        grade = sc.next();
        try {
            professorInterface.addGrades(studentId, courseCode, grade);
        } catch (GradeNotAddedException e) {
            e.printStackTrace();
        }
        System.out.println(GREEN_BRIGHT+"Grade added successfully for " + studentId+ANSI_RESET);
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
            System.out.println(ANSI_RED+String.format("%20s %20s %20s", "COURSE CODE", "COURSE NAME", "STUDENT ID")+ANSI_RESET);
            for (EnrolledStudent obj : enrolledStudents) {
                System.out.println(ANSI_CYAN+String.format("%20s %20s %20s", obj.getCourseCode(), obj.getCourseName(), obj.getStudentId())+ ANSI_CYAN);
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
            System.out.println(ANSI_RED+"No courses found!!"+ANSI_RED);
        }
        else {
            System.out.println(ANSI_RED+String.format("%20s %20s","COURSE CODE","COURSE NAME" )+ANSI_RESET);
            for (Course obj : coursesEnrolled) {
                System.out.println(ANSI_CYAN+String.format("%20s %20s",obj.getCourseCode(), obj.getCourseName())+ANSI_RESET);
            }
        }
    }
}
