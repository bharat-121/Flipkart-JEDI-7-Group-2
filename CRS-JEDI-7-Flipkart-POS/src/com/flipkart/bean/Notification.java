package com.flipkart.bean;

import com.flipkart.constants.NotificationType;

public class Notification {
    private int notificationId;
    private int studentId;
    private NotificationType type;
    private String referenceId;


    public Notification(int notificationId, int studentId, NotificationType type, String referenceId) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.type = type;
        this.referenceId = referenceId;
    }

    public Notification() {
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
