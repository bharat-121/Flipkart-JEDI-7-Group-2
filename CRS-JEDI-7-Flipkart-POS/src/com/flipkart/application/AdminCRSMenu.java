package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperations;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.exception.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

import static com.flipkart.constants.Colors.*;

/**
 *
 * @author JEDI-07
 * Class that display Admin Client Menu
 *
 */
public class AdminCRSMenu {
    AdminInterface adminOperation = AdminOperations.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface= NotificationOperation.getInstance();
    private static Logger logger = Logger.getLogger(AdminCRSMenu.class);

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(){
        while(CRSApplication.loggedIn) {
           /* System.out.println("*****************************");
            System.out.println("**********Admin Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Course");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course ");
            System.out.println("4. Approve Students");
            System.out.println("5. Add Professor");
            System.out.println("6. Logout");
            System.out.println("*****************************");*/

            System.out.println(GREEN_BRIGHT+"*****************************");
            System.out.println(GREEN_BRIGHT+"**********Admin Menu*********");
            System.out.println(GREEN_BRIGHT+"*****************************");
            System.out.println(ANSI_CYAN+"1."+CYAN_BRIGHT+"   View Courses in Catalog");
            System.out.println(ANSI_CYAN+"2."+CYAN_BRIGHT+"   Add Course to Catalog");
            System.out.println(ANSI_CYAN+"3."+CYAN_BRIGHT+"   Delete Course from Catalog");
            System.out.println(ANSI_CYAN+"4."+CYAN_BRIGHT+"   Approve Students");
            System.out.println(ANSI_CYAN+"5."+CYAN_BRIGHT+"   Add Professor");
            System.out.println(ANSI_CYAN+"6."+CYAN_BRIGHT+"   Logout");
            System.out.println(GREEN_BRIGHT+"***************************");
            System.out.print(ANSI_RED+"Enter your choice :-"+ANSI_RESET);
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    viewCoursesInCatalogue();
                    break;

                case 2:
                    addCourseToCatalogue();
                    break;

                case 3:
                    deleteCourse();
                    break;

                case 4:
                    approveStudent();
                    break;

                case 5:
                    addProfessor();
                    break;

                case 6:
                    CRSApplication.loggedIn = false;
                    return;

                default:
                    System.out.println(RED_BRIGHT+"***** Wrong Choice *****"+ANSI_RESET);
            }
        }
    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        Professor professor = new Professor();

        System.out.print(ANSI_RED+"Enter Professor Name:-"+ANSI_RESET);
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.print(ANSI_RED+"Enter Department    :-"+ANSI_RESET);
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.print(ANSI_RED+"Enter Designation   :-"+ANSI_RESET);
        String designation = scanner.next();
        professor.setDesignation(designation);

        System.out.print(ANSI_RED+"Enter Contact no    :-"+ANSI_RESET);
        String contact = scanner.next();
        professor.setPhone(contact);

        System.out.print(ANSI_RED+"Enter email         :-"+ANSI_RESET);
        String email = scanner.next();
        professor.setEmail(email);

        System.out.print(ANSI_RED+"Enter User Id        :-"+ANSI_RESET);
        String userId = scanner.next();
        professor.setUserID(userId);

        System.out.print(ANSI_RED+"Enter Password      :-"+ANSI_RESET);
        String password = scanner.next();
        professor.setPassword(password);

        professor.setRole("PROFESSOR");
        try {
            adminOperation.addProfessor(professor);
        } catch (ProfessorNotAddedException e) {
            e.printStackTrace();
        } catch (UserIdAlreadyInUseException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to view Students who are yet to be approved
     * @return List of Students whose admissions are pending
     */
    private List<Student> viewPendingAdmissions() {

        List<Student> pendingStudentsList= adminOperation.viewPendingAdmissions();
        if(pendingStudentsList.size() == 0) {
            return pendingStudentsList;
        }
        System.out.println(ANSI_RED+String.format("%20s | %20s", "UserId", "Name")+ANSI_RESET);
        for(Student student : pendingStudentsList) {
            System.out.println(ANSI_CYAN+String.format("%20s | %20s", student.getUserID(), student.getName())+ANSI_RESET);
        }
        return pendingStudentsList;
    }


    /**
     * Method to approve a Student using Student's ID
     */
    private void approveStudent() {

        List<Student> studentList = viewPendingAdmissions();
        if(studentList.size() == 0) {
            return;
        }

        System.out.print(ANSI_RED+"Enter Student's ID:-"+ANSI_RESET);
        String studentUserIdApproval = scanner.next();
        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
        } catch (StudentNotFoundForApprovalException e) {
            e.printStackTrace();
        }
        //send notification from system
        //notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null,0);
    }

    /**
     * Method to delete Course from catalogue
     */
    private void deleteCourse() {
        List<Course> courseList = viewCoursesInCatalogue();
        System.out.print(ANSI_RED+"Enter Course Code:-"+ANSI_RESET);
        String courseCode = scanner.next();
        try {
            adminOperation.deleteCourse(courseCode, courseList);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        } catch (CourseNotDeletedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add Course to catalogue
     */
    private void addCourseToCatalogue() {
        List<Course> courseList = viewCoursesInCatalogue();

        scanner.nextLine();
        System.out.print(ANSI_RED+"Enter Course Code  :-"+ANSI_RESET);
        String courseCode = scanner.nextLine();

        System.out.print(ANSI_RED+"Enter Course Name  :-"+ANSI_RESET);
        String courseName = scanner.next();

        System.out.print(ANSI_RED+"Enter Instructor ID:-"+ANSI_RESET);
        String instructorID = scanner.next();

        Course course = new Course(courseCode, courseName,instructorID);
        try {
            adminOperation.addCourse(course, courseList);
        } catch (CourseFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to display courses in catalogue
     * @return List of courses in catalogue
     */
    private List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses();
        if(courseList.size() == 0) {
            System.out.println(RED_BRIGHT+"---- No course in the catalogue!!----"+ANSI_RESET);
            return courseList;
        }
        System.out.println(ANSI_RED+String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR")+ANSI_RESET);
        for(Course course : courseList) {
            System.out.println(ANSI_CYAN+String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId())+ANSI_RESET);
        }
        return courseList;
    }
}
