package com.flipkart.exception;

public class FeeAlreadyPaidException extends Exception {
    private String userId;

    /**
     * constructor for UserID
     */

    public FeeAlreadyPaidException(String userId) {
        this.userId = userId;
    }

    /**
     * setter function for UserID
     */

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * getter function for UserID
     * @return userID
     */
    public String getUserId() {
        return userId;
    }

    @Override
    public String getMessage() {
        return "userId: " + userId + " has already paid the fees.";
    }

}
