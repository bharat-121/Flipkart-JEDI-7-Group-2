package com.flipkart.business;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperations;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class NotificationOperation implements NotificationInterface{

    //fetch notification id from the database for particular student Id
    //return notification Id

    static NotificationOperation instance = null;

    NotificationDaoInterface notificationDaoInterface= NotificationDaoOperations.getInstance();
    Scanner scn = new Scanner(System.in);

    public static NotificationInterface getInstance() {
        if (instance == null) {
            instance = new NotificationOperation();
        }
        return instance;
    }
    @Override
    public void sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount){

        try
        {
            notificationDaoInterface.sendNotification(type, studentId,modeOfPayment,amount);

        }
        catch(SQLException ex)
        {
            System.out.println("Error occured "+ex.getMessage());
        }

    }

    @Override
    public UUID getReferenceId(int notificationId) {
        return new UUID(100,100); //To-Do
    }


}
