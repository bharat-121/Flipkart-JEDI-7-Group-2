package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

/**
 *
 * @author JEDI-02
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException; ;
    public String getRole(String userId);
    public boolean updatePassword(String userID,String newPassword);
}