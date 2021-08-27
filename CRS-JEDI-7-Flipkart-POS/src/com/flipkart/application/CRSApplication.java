package com.flipkart.application;

import com.flipkart.business.*;
import com.flipkart.constants.Role;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.apache.log4j.Logger;

import static com.flipkart.constants.Colors.*;

/**
 *
 * @author JEDI-07
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 *
 */

public class CRSApplication {
    static boolean loggedIn = false;
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface= NotificationOperation.getInstance();

    public static void main(String[] args) throws Exception {


        // application starts here

        System.out.println(RED_BRIGHT+"*****  Welcome to the Course Registration System  *****"+ANSI_RESET);

        CRSApplication crsApplication = new CRSApplication();

        Scanner sc = new Scanner(System.in);

        try {

			int input = 0;
            //until user do not exit the application
            do {
                mainMenu();
                System.out.print(ANSI_RED+"Enter your choice :-"+ANSI_RESET);
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
                        System.out.println(ANSI_CYAN+"Thank you ! Bye ! "+ANSI_RESET);
                        break;
                    default:
                        System.out.println(ANSI_RED+"Invalid Input"+ANSI_RESET);
                }
            }while (input != 4);
        } catch (Exception ex) {
            System.out.println(ANSI_RED+"Error occured " + ex+ANSI_RESET);
        } finally {
            sc.close();
        }

    }

    /**
     * Method to Create Main menu of the CRS Application
     */

    public static void mainMenu() {

        System.out.println(RED_BRIGHT+"---- Menu ----"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"1."+CYAN_BRIGHT+"   Login"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"2."+CYAN_BRIGHT+"   Change Password"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"3."+CYAN_BRIGHT+"   Student Registration"+ANSI_RESET);
        System.out.println(ANSI_CYAN+"4."+CYAN_BRIGHT+"   Exit"+ANSI_RESET);
    }
    /**
     * Method for Login functionality
     */

    public void loginUser() throws Exception {
        Scanner sc = new Scanner(System.in);

        String userId, password;

        System.out.println(ANSI_CYAN + "---- Login ----" + ANSI_RESET);
        System.out.print(ANSI_RED + "UserId:-" + ANSI_RESET);
        userId = sc.next();
        System.out.print(ANSI_RED + "Password:-" + ANSI_RESET);
        password = sc.next();
        loggedIn = userInterface.verifyCredentials(userId, password);
        //2 cases
        //true->role->student->approved
        if (loggedIn) {
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            LocalDateTime myDateObj = LocalDateTime.now();

            String formattedDate = myDateObj.format(myFormatObj);

            //System.out.println("Welcome "+userId);
            String role = userInterface.getRole(userId);
            Role userRole = Role.stringToName(role);
            switch (userRole) {
                case ADMIN:
                    System.out.println(CYAN_BRIGHT + "Login Successful:-" + formattedDate + ANSI_RESET);
                    AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                    adminCRSMenu.createMenu();
                    break;
                case PROFESSOR:
                    System.out.println(CYAN_BRIGHT + "Login Successful:-" + formattedDate + ANSI_RESET);
                    ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                    professorMenu.createMenu(userId);
                    break;
                case STUDENT:
                    boolean isApproved = userInterface.verifyApproval(userId);
                    if(isApproved) {
                        System.out.println(CYAN_BRIGHT + "Login Successful:-" + formattedDate + ANSI_RESET);
                        StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                        studentCRSMenu.createMenu(userId);
                    }
                    else {
                        System.out.println(RED_BRIGHT + "You are not approved by admin yet!!" + ANSI_RESET);
                    }
                    break;
            }
        } else {
            System.out.println(RED_BRIGHT + "Invalid Credentials!!" + ANSI_RESET);
        }
    }

    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {

        Scanner sc=new Scanner(System.in);

        String userId,name,password,email,role,phone,department;
        int semester ;
        role = "STUDENT";
        try
        {
            //input all the student details
            System.out.println(ANSI_CYAN+"----Student Registration----"+ANSI_RESET);
            System.out.print(ANSI_RED+"User ID   :-"+ANSI_RESET);
            userId=sc.next();

            System.out.print(ANSI_RED+"Name      :-"+ANSI_RESET);
            name=sc.next();
            System.out.print(ANSI_RED+"Email     :-"+ANSI_RESET);
            email=sc.next();
            System.out.print(ANSI_RED+"Password  :-"+ANSI_RESET);
            password=sc.next();

            System.out.print(ANSI_RED+"Phone     :-"+ANSI_RESET);
            phone=sc.next();
            System.out.print(ANSI_RED+"Semester  :-"+ANSI_RESET);
            semester=sc.nextInt();
            sc.nextLine();
            System.out.print(ANSI_RED+"Department:-"+ANSI_RESET);
            department=sc.next();

            StudentInterface studentInterface = new StudentOperations();
            String newStudentId=studentInterface.register(name, userId, password,semester,department,email,phone,role);
            if(newStudentId != null) {
                // notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, ModeOfPayment.CREDIT_CARD, 1000);
                System.out.println(GREEN_BRIGHT+"Student Sucessfully Registered" + ANSI_RESET);
            }
            else{
                System.out.println(RED_BRIGHT+"Some Error occured!! try again"+ANSI_RESET);
            }

        }
        catch(Exception ex)
        {
            System.out.println(RED_BRIGHT+"Something went wrong! "+ex.getMessage()+ANSI_RESET );
        }
    }


    /**
     * Method to update password of User
     */
    public void changePassword() {

        Scanner sc = new Scanner(System.in);
        String userId, newPassword;
        try {
            System.out.println(ANSI_CYAN+"----Change Password----"+ANSI_RESET);
            System.out.print(ANSI_RED+"UserId      :-"+ANSI_RESET);
            userId = sc.next();
            System.out.print(ANSI_RED+"New Password:-"+ANSI_RESET);
            newPassword = sc.next();
            boolean isUpdated = userInterface.updatePassword(userId, newPassword);

            if (isUpdated)
                System.out.println(GREEN_BRIGHT+"Password updated successfully!"+ANSI_RESET);

            else
                System.out.println(RED_BRIGHT+"Something went wrong, please try again!"+ANSI_RESET);
        } catch (Exception ex) {
            System.out.println(RED_BRIGHT+"Error Occured " + ex.getMessage()+ANSI_RESET);
        }
    }
}
