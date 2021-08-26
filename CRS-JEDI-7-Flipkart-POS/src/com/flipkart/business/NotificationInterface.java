package com.flipkart.business;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

import java.util.UUID;

public interface NotificationInterface {

    public void sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount);
    public UUID getReferenceId(int notificationId);
}
