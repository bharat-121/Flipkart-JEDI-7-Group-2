package com.flipkart.dao;

import com.flipkart.exception.UserNotApprovedException;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * @author JEDI-07
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {

    /**
     * Method to verify credentials of Users from DataBase
     * @param userId
     * @param password
     * @return Verify credentials operation status
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;
    /**
     * Method to verify if user is approved or not by admin
     * @param userId
     * @return Verify credentials operation status
     * @throws UserNotApprovedException
     */
    public boolean verifyApproved(String userId) throws UserNotApprovedException;

    /**
     * Method to get Role of User from DataBase
     * @param userId
     * @return Role
     */
    public String getRole(String userId);

    /**
     * Method to update password of user in DataBase
     * @param userID
     * @param newPassword
     * @return Update Password operation Status
     */

    public boolean updatePassword(String userID,String newPassword);
}