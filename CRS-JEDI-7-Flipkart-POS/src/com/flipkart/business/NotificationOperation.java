package com.flipkart.business;

import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;

public class NotificationOperation implements NotificationInterface{

    //fetch notification id from the database for particular student Id
    //return notification Id

    static NotificationOperation instance = null;

    public static NotificationInterface getInstance() {
        if (instance == null) {
            instance = new NotificationOperation();
        }
        return instance;
    }
    @Override
    public int sendNotification(NotificationType type, String studentId, ModeOfPayment modeOfPayment, double amount){



        return 0;
    }


}
