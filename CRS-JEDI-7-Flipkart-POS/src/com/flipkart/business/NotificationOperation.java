package com.flipkart.business;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperations;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

/**
 *
 * @author JEDI-07
 * This method implements all the method related to the notification system
 */

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

    /**
     * Method to make NotificationDaoOperation Singleton
     * @return notificationId
     */

    @Override
    public int sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount){

        int notificationId=0;
        try
        {
            notificationId=notificationDaoInterface.sendNotification(type, studentId,modeOfPayment,amount);

        }
        catch(SQLException ex)
        {
            System.out.println("Error occured "+ex.getMessage());
        }
        return notificationId;

    }
    /**
     * Method to return UUID for a transaction
     * @param notificationId: notification id added in the database
     * @return transaction id of the payment
     */
    @Override
    public UUID getReferenceId(int notificationId) {
        return new UUID(100,100); //To-Do
    }


}
