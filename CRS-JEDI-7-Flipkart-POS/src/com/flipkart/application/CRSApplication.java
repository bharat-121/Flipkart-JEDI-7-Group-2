package com.flipkart.application;

import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constants.Role;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CRSApplication {
    static boolean loggedin = false;
    UserInterface userInterface = UserOperation.getInstance();

    public static void main(String[] args) throws Exception {

        // application starts here
        System.out.println("Welcome to the Course Registration System");
        mainMenu();
        CRSApplication crsApplication = new CRSApplication();

        Scanner sc = new Scanner(System.in);

        try {

			int input = sc.nextInt();
            //until user do not exit the application
            while (input != 4) {
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
                    default:
                        System.out.println("Invalid Input");
                }
            }
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
        loggedin = userInterface.verifyCredentials(userId, password);
        //2 cases
        //true->role->student->approved
        if (loggedin) {
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
