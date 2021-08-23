package com.flipkart.bean.payment;

public class Payment {
    private String studentId;
    private String referenceId;
    private boolean status;
    private float amount;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "studentId='" + studentId + '\'' +
                ", referenceId='" + referenceId + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
