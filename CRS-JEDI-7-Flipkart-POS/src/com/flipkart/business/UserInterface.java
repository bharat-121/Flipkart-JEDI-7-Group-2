package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {
    /**
     * Method to updatePassword using userID
     * @param userID
     * @param newPassword
     * @return boolean to indicate password update or not
     */
    boolean updatePassword(String userID, String newPassword);

    /**
     * Method to verfiy  User credentials
     * @param userID
     * @param password
     * @return boolean to indicate verifyCredentails or not
     */
    public boolean verifyCredentials(String userID, String password)throws UserNotFoundException;

    /**
     * Method to updateDetail using userId
     * @param userId
     * @param user
     */
    public void updateDetails(String userId, User user);

    /**
     * Method to get role of a specific User
     * @param userId
     * @return Role of the User
     */
    String getRole(String userId);
}
