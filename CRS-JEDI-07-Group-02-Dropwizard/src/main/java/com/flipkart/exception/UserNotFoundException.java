package com.flipkart.exception;

/**
 * Exception to check if user exists
 * @author JEDI-07-Group2
 *
 */
public class UserNotFoundException extends Exception {

    private String userId;



    /***
     * Constructor
     * @param userId
     */
    public UserNotFoundException(String userId) {
        this.userId = userId;
    }

    /***
     * Getter function for UserId
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Message thrown by exception
     */
    @Override
    public String getMessage() {
        return "User with userId: " + userId + " not found.";
    }
}