package com.flipkart.application;

import com.flipkart.business.*;
import com.flipkart.constants.Role;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CRSApplication {
    static boolean loggedIn = false;
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface= NotificationOperation.getInstance();

    public static void main(String[] args) throws Exception {

        // application starts here
        System.out.println("Welcome to the Course Registration System");
        CRSApplication crsApplication = new CRSApplication();

        Scanner sc = new Scanner(System.in);

        try {

			int input = 0;
            //until user do not exit the application
            do {
                mainMenu();
                System.out.println("Enter your choice : ");
                input = sc.nextInt();
                switch (input) {
                    case 1:
                        //login
                        crsApplication.loginUser();
                        break;
                    case 2:
                        crsApplication.changePassword();
                        break;
                    case 3:
                        //student registration
                        crsApplication.registerStudent();
                        break;
                    case 4 :
                        System.out.println("Thank you ! Bye ! ");
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
            }while (input != 4);
        } catch (Exception ex) {
            System.out.println("Error occured " + ex);
        } finally {
            sc.close();
        }

    }

    public static void mainMenu() {
        System.out.println("---------Menu-----------");
        System.out.println("1. Login");
        System.out.println("2. Change Password");
        System.out.println("3. Student Registration");
        System.out.println("4. Exit");
    }


    public void loginUser() throws Exception {
        Scanner sc = new Scanner(System.in);

        String userId, password;

        System.out.println("-----------------Login------------------");
        System.out.println("UserId:");
        userId = sc.next();
        System.out.println("Password:");
        password = sc.next();
        loggedIn = userInterface.verifyCredentials(userId, password);
        //2 cases
        //true->role->student->approved
        if (loggedIn) {
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            LocalDateTime myDateObj = LocalDateTime.now();

            String formattedDate = myDateObj.format(myFormatObj);

            //logger.info("Welcome "+userId);
            String role = userInterface.getRole(userId);
            Role userRole = Role.stringToName(role);
            switch (userRole) {
                case ADMIN:
                    System.out.println(formattedDate + " Login Successful");
                    AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                    adminCRSMenu.createMenu();
                    break;
                case PROFESSOR:
                    System.out.println(formattedDate + " Login Successful");
                    ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                    professorMenu.createMenu(userId);
                    break;
                case STUDENT:
                    System.out.println(formattedDate + " Login Successful");
                    StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                    studentCRSMenu.createMenu(userId);
                    break;
            }
        } else {
            System.out.println("Invalid Credentials!");
        }

    }

    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {

        Scanner sc = new Scanner(System.in);

        String userId, name, password, email, role, phone, department;
        int semester;
        role = "STUDENT";
        try {
            //input all the student details
            System.out.println("---------------Student Registration-------------");
            System.out.println("User ID:");
            userId = sc.next();

            System.out.println("Name:");
            name = sc.next();
            System.out.println("Email:");
            email = sc.next();
            System.out.println("Password:");
            password = sc.next();

            System.out.println("Phone:");
            phone = sc.next();
            System.out.println("Semester:");
            semester = sc.nextInt();
            sc.nextLine();
            System.out.println("Department:");
            department = sc.next();

            StudentInterface studentInterface = new StudentOperations();
            String newStudentId = studentInterface.register(name, userId, password, semester, department, email, phone, role);

            if(newStudentId != null) {
               // notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, ModeOfPayment.CREDIT_CARD, 1000);
                System.out.println("Student Sucessfully Registered");
            }
            else{
                System.out.println("Some Error occured!! try again");
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong! " + ex.getMessage());
        }
    }

    /**
     * Method to update password of User
     */
    public void changePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword;
        try {
            System.out.println("------------------Change Password--------------------");
            System.out.println("UserId");
            userId = sc.next();
            System.out.println("New Password:");
            newPassword = sc.next();
            boolean isUpdated = userInterface.updatePassword(userId, newPassword);
            if (isUpdated)
                System.out.println("Password updated successfully!");

            else
                System.out.println("Something went wrong, please try again!");
        } catch (Exception ex) {
            System.out.println("Error Occured " + ex.getMessage());
        }
    }
}
