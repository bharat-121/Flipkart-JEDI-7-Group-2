package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.*;
import com.flipkart.constants.Grade;
import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Colors.*;

/**
 * @author JEDI-07
 * The class displays the Student Client Menu
 */

public class StudentCRSMenu {
    Scanner sc = new Scanner(System.in);
    RegistartionInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperations.getInstance();
    StudentInterface studentInterface = StudentOperations.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    private boolean is_registered = false;
    private static Logger logger = Logger.getLogger(StudentCRSMenu.class);

    /**
     * Method to generate Student Menu for course registration, addition, removal and fee payment
     *
     * @param studentId student id
     */
    public void createMenu(String studentId) throws Exception {

        is_registered = getRegistrationStatus(studentId);
        while (CRSApplication.loggedIn) {
            System.out.println(GREEN_BRIGHT + "*******************************" + ANSI_RESET);
            System.out.println(GREEN_BRIGHT + "**********Student Menu*********" + ANSI_RESET);
            System.out.println(GREEN_BRIGHT + "*******************************" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "1." + CYAN_BRIGHT + "   Course Registration" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "2." + CYAN_BRIGHT + "   Add Course" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "3." + CYAN_BRIGHT + "   Drop Course" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "4." + CYAN_BRIGHT + "   View Available Courses" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "5." + CYAN_BRIGHT + "   View Registered Courses" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "6." + CYAN_BRIGHT + "   View grade card" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "7." + CYAN_BRIGHT + "   Make Payment" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "8." + CYAN_BRIGHT + "   Logout" + ANSI_RESET);
            System.out.println(GREEN_BRIGHT + "*******************************" + ANSI_RESET);

            System.out.print(ANSI_RED + "Enter your choice :-" + ANSI_RESET);

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerCourses(studentId);
                    break;

                case 2:
                    addCourse(studentId);
                    break;

                case 3:

                    dropCourse(studentId);
                    break;

                case 4:
                    viewCourse(studentId);
                    break;

                case 5:
                    viewRegisteredCourse(studentId);
                    break;

                case 6:
                    viewGradeCard(studentId);
                    break;

                case 7:
                    make_payment(studentId);
                    break;

                case 8:
                    CRSApplication.loggedIn = false;
                    return;

                default:
                    System.out.println(RED_BRIGHT + "***** Wrong Choice *****" + ANSI_RESET);
            }
        }
    }


    /**
     * Select course for registration
     *
     * @param studentId
     */
    private void registerCourses(String studentId) throws Exception {
        if (is_registered) {
            System.out.println(GREEN_BRIGHT + "Registration is already completed" + ANSI_RESET);
            return;
        }
        List<Course> courseList = viewCourse(studentId);
        int count = 0;

        while (count < 6) {

            System.out.print(ANSI_RED + "Enter Course Code No :- " + ANSI_RESET);
            String courseCode = sc.next();

            if (registrationInterface.addCourse(courseCode, studentId, courseList)) {
                System.out.println(GREEN_BRIGHT + "Course " + courseCode + " registered sucessfully." + ANSI_RESET);
                count++;
            } else {
                System.out.println(ANSI_CYAN + "You have already registered for Course :- " + courseCode + ANSI_RESET);
            }

        }
        System.out.println(GREEN_BRIGHT + "Registration Successful" + ANSI_RESET);
        is_registered = true;
        registrationInterface.setRegistrationStatus(studentId);


    }

    /**
     * Add course for registration
     *
     * @param studentId
     */
    private void addCourse(String studentId) throws Exception {
        if (is_registered) {
            List<Course> availableCourseList = viewCourse(studentId);

            if (availableCourseList == null)
                return;

            System.out.print(ANSI_RED + "Enter Course Code :- " + ANSI_RESET);
            String courseCode = sc.next();
            if (registrationInterface.addCourse(courseCode, studentId, availableCourseList)) {
                System.out.println(GREEN_BRIGHT + " You have successfully registered for Course : " + courseCode + ANSI_RESET);
            } else {
                System.out.println(ANSI_CYAN + " You have already registered for Course : " + courseCode + ANSI_RESET);
            }

        } else {
            System.out.println(RED_BRIGHT + "Please complete registration" + ANSI_RESET);
        }


    }

    /**
     * Method to check if student is already registered
     *
     * @param studentId
     * @return Registration Status
     */
    private boolean getRegistrationStatus(String studentId) throws SQLException {
        return registrationInterface.getRegistrationStatus(studentId);

    }

    /**
     * Drop Course
     *
     * @param studentId
     */
    private void dropCourse(String studentId) throws Exception {
        if (is_registered) {
            List<Course> registeredCourseList = viewRegisteredCourse(studentId);

            if (registeredCourseList == null)
                return;

            System.out.print(ANSI_RED + "Enter the Course Code :- " + ANSI_RESET);
            String courseCode = sc.next();

            registrationInterface.dropCourse(courseCode, studentId, registeredCourseList);
            System.out.println(ANSI_CYAN + "You have successfully dropped Course : " + courseCode + ANSI_RESET);


        } else {
            System.out.println(RED_BRIGHT + "Please complete registration" + ANSI_RESET);
        }

    }

    /**
     * View all available Courses
     *
     * @param studentId
     * @return List of available Courses
     */
    private List<Course> viewCourse(String studentId) throws Exception {
        List<Course> course_available = null;
        course_available = registrationInterface.viewAvailableCourses(studentId);


        if (course_available.isEmpty()) {
            System.out.println(RED_BRIGHT + "NO COURSE AVAILABLE" + ANSI_RESET);
            return null;
        }


        System.out.println(ANSI_RED + String.format("%-20s %-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR NAME", "SEATS") + ANSI_RESET);
        for (Course obj : course_available) {
            System.out.println(ANSI_CYAN + String.format("%-20s %-20s %-20s %-20s", obj.getCourseCode(), obj.getCourseName(), professorInterface.getProfessorById(obj.getInstructorId()), obj.getSeats()) + ANSI_CYAN);
        }

        return course_available;

    }

    /**
     * View Registered Courses
     *
     * @param studentId
     * @return List of Registered Courses
     */
    private List<Course> viewRegisteredCourse(String studentId) throws SQLException {
        List<Course> course_registered = null;
        course_registered = registrationInterface.viewRegisteredCourses(studentId);

        if (course_registered.isEmpty()) {
            System.out.println(RED_BRIGHT + "You haven't registered for any course" + ANSI_RESET);
            return null;
        }

        System.out.println(ANSI_RED + String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR NAME") + ANSI_RESET);

        for (Course obj : course_registered) {
            System.out.println(ANSI_CYAN + String.format("%-20s %-20s %-20s ", obj.getCourseCode(), obj.getCourseName(), professorInterface.getProfessorById(obj.getInstructorId())) + ANSI_RESET);
        }

        return course_registered;
    }

    /**
     * View grade card for particular student
     *
     * @param studentId
     */
    private void viewGradeCard(String studentId) {

        List<GradeCard> gradeCards = studentInterface.viewGradeCard(studentId);

        if(gradeCards==null || gradeCards.isEmpty())
        {
            System.out.println(RED_BRIGHT+"You have not registered for any course yet"+ANSI_RESET);
        }
        else
        {
            System.out.println(String.format(ANSI_RED+"%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"+ANSI_RESET));
            for(GradeCard gradeCard : gradeCards){
                System.out.println(String.format(ANSI_CYAN+"%-20s %-20s %-20s",gradeCard.getCourseCode(),gradeCard.getCourseName(),gradeCard.getGrade()+ANSI_RESET));
            }
        }
    }

    /**
     * Make Payment for selected courses. Student is provided with an option to pay the fees and select the mode of payment.
     *
     * @param studentId
     */
    private void make_payment(String studentId) throws SQLException {


        double fee = 0.0;

        fee = registrationInterface.calculateFee(studentId);


        if (fee == 0.0) {
            System.out.println(RED_BRIGHT + "You have not  registered for any courses yet" + ANSI_RESET);
        } else {

            System.out.println(ANSI_CYAN + "Your total fee                   :-" + fee + ANSI_RESET);
            boolean paymentStatus = registrationInterface.getPaymentStatus(studentId);
            if(paymentStatus == true){
                System.out.println(RED_BRIGHT+"Fees has already been paid"+ANSI_RESET);
                return ;
            }
            System.out.print(ANSI_RED + "Want to continue Fee Payment(y/n):-" + ANSI_RESET);
            String ch = sc.next();
            if (ch.equals("y")) {
                System.out.println(CYAN_BRIGHT + "Select Mode of Payment           :-" + ANSI_RESET);

                int index = 1;
                for (ModeOfPayment mode : ModeOfPayment.values()) {
                    System.out.println(CYAN_BRIGHT + index + " " + mode + ANSI_RESET);
                    index = index + 1;
                }

                ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());



                if (mode == null)
                    System.out.println(RED_BRIGHT + "Invalid Input" + ANSI_RESET);
                else {
                    boolean payment = studentInterface.payFees(studentId);
                    if(payment == true) {
                        int notificationId = notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee);
                        if (notificationId != 0) {
                            System.out.println(GREEN_BRIGHT + "Notification Sent Successfully with Id : " + notificationId + ANSI_RESET);
                        }
                    }
                    else
                    {
                        System.out.println(RED_BRIGHT+"Payment Unsuccessful"+ANSI_RESET);
                    }
                }
            }
        }
    }
}