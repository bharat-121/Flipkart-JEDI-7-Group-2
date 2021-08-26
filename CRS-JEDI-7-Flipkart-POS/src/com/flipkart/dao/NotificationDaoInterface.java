package com.flipkart.dao;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

import java.sql.SQLException;
/**
 *
 * @author JEDI-07
 * Interface for Notification Dao Operations
 * Used for adding the notification to the database
 *
 */
public interface NotificationDaoInterface {
    /**
     * Send Notification using SQL commands
     * @param type
     * @param studentId
     * @param modeOfPayment
     * @param amount
     * @return
     * @throws SQLException
     */
    public int sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount) throws SQLException;
}
