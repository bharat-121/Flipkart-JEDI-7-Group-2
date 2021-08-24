package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperations;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.constants.NotificationType;
import com.flipkart.constants.Role;

import java.util.List;
import java.util.Scanner;

public class AdminCRSMenu {
    AdminInterface adminOperation = AdminOperations.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface= NotificationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(){
        while(CRSApplication.loggedin) {
            System.out.println("*****************************");
            System.out.println("**********Admin Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Course");
            System.out.println("2. Add Course");
            System.out.println("3. Delete Course ");
            System.out.println("4. Approve Students");
            System.out.println("5. Add Professor");
            System.out.println("6. Logout");
            System.out.println("*****************************");

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
                    CRSApplication.loggedin = false;
                    return;

                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        Professor professor = new Professor();

        System.out.println("Enter Professor Name:");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.println("Enter Designation:");
        String designation = scanner.next();
        professor.setDesignation(designation);

        System.out.println("Enter User Id:");
        String userId = scanner.next();
        professor.setUserID(userId);

        System.out.println("Enter Password:");
        String password = scanner.next();
        professor.setPassword(password);

        professor.setRole("PROFESSOR");
        adminOperation.addProfessor(professor);
    }

    /**
     * Method to view Students who are yet to be approved
     * @return List of Students whose admissions are pending
     */
    private List<Student> viewPendingAdmissions() {

        List<Student> pendingStudentsList= AdminOperations.viewPendingAdmissions();
        if(pendingStudentsList.size() == 0) {
            return pendingStudentsList;
        }
        System.out.println(String.format("%20s | %20s", "UserId", "Name"));
        for(Student student : pendingStudentsList) {
            System.out.println(String.format("%20s | %20s", student.getUserID(), student.getName()));
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

        System.out.println("Enter Student's ID:");
        String studentUserIdApproval = scanner.next();
        adminOperation.approveStudent(studentUserIdApproval, studentList);
        //send notification from system
        notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null,0);
    }

    /**
     * Method to delete Course from catalogue
     */
    private void deleteCourse() {
        List<Course> courseList = viewCoursesInCatalogue();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();
        adminOperation.deleteCourse(courseCode, courseList);
    }

    /**
     * Method to add Course to catalogue
     */
    private void addCourseToCatalogue() {
        List<Course> courseList = viewCoursesInCatalogue();

        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();

        System.out.println("Enter Instructor ID:");
        String instructorID = scanner.next();

        Course course = new Course(courseCode, courseName,instructorID);
        adminOperation.addCourse(course, courseList);
    }

    /**
     * Method to display courses in catalogue
     * @return List of courses in catalogue
     */
    private List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses();
        if(courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        System.out.println(String.format("%20s | %20s | %20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
        for(Course course : courseList) {
            System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
        }
        return courseList;
    }
}
