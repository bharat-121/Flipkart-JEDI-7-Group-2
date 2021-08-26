package com.flipkart.exception;

/**
 * @author Jedi-07-Group2
 *
 */
public class UserIdAlreadyInUseException extends Exception{
    private String userId;

    /**
     * constructor for UserID
     */

    public UserIdAlreadyInUseException(String userId) {
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
        return "userId: " + userId + " is already in use. Please try some other.";
    }

}