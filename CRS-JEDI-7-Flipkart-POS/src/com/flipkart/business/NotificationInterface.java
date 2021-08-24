package com.flipkart.business;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

public interface NotificationInterface {

    public int sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount);
}
