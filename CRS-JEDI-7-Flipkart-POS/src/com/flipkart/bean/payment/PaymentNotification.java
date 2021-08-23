package com.flipkart.bean.payment;

public class PaymentNotification {
    private String studentId;
    private String referenceId;
    private String notificationId;
    private String message;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PaymentNotification{" +
                "studentId='" + studentId + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", notificationId='" + notificationId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
