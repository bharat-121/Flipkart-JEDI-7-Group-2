package com.flipkart.dao;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.utils.DBUtil;

import java.sql.*;
import java.util.UUID;

/**
 *
 * @author JEDI-07
 * Class to implement Notification Dao Operations
 * Used for adding the notification to the database
 *
 */

public class NotificationDaoOperations implements  NotificationDaoInterface{
    private static volatile NotificationDaoOperations instance=null;

    /**
     * Default Constructor
     */
    private NotificationDaoOperations() {}

    /**
     * Method to make NotificationDaoOperation Singleton
     * @return
     */
    public static NotificationDaoOperations getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(NotificationDaoOperations.class){
                instance=new NotificationDaoOperations();
            }
        }
        return instance;
    }

    /**
     * Send Notification using SQL commands
     * @param type
     * @param studentId
     * @param modeOfPayment
     * @param amount
     * @return
     * @throws SQLException
     */
    @Override
    public int sendNotification(NotificationType type, String studentId,ModeOfPayment modeOfPayment,double amount) throws SQLException {
        int notificationId=0;
        Connection connection= DBUtil.getConnection();
        try
        {
            //INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(SQLQueriesConstants.INSERT_NOTIFICATION, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, studentId);
            ps.setString(2,type.toString());
            if(type==NotificationType.PAYMENT)
            {
                //insert into payment, get reference id and add here
                UUID referenceId=UUID.randomUUID();
                ps.setString(3, referenceId.toString());
                System.out.println("Payment successful, Reference ID: "+referenceId);
            }
            else
                ps.setString(3,"");

            ps.setDouble(4,amount);
            ps.executeUpdate();
            ResultSet results=ps.getGeneratedKeys();
            if(results.next())
                notificationId=results.getInt(1);

            switch(type)
            {
                case REGISTRATION:
                    System.out.println("Registration successfull. Administration will verify the details and approve it!");
                    break;
                case REGISTRATION_APPROVAL:
                    System.out.println("Student with id "+studentId+" has been approved!");
                    break;
                case PAYMENT:
                    System.out.println("Student with id "+studentId+" fee has been paid");
            }

        }
        catch(Exception ex)
        {

            throw ex;
        }
        return notificationId;
    }


}
