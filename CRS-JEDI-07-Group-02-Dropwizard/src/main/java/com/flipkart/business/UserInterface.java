package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.exception.UserNotApprovedException;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * @author JEDI-03
 * Interface for User Operations
 *
 */

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
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userID, String password)throws UserNotFoundException;

    /**
     * Method to verfiy if the student is approved or not
     * @param userID
     * @return boolean to indicate verifyCredentails or not
     * @throws UserNotApprovedException
     */
    public boolean verifyApproval(String userID) throws UserNotApprovedException;

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
