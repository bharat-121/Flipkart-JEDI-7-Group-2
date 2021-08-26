package com.flipkart.dao;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

import java.sql.SQLException;

public interface NotificationDaoInterface {

    public void sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount) throws SQLException;
}
