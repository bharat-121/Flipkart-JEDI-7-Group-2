package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperations;
import com.flipkart.exception.UserNotFoundException;

/**
 *
 * @author JEDI-07
 * Implementations of User Operations
 *
 */

public class UserOperation implements UserInterface{
    private static UserOperation instance=null;

    UserDaoInterface userDaoInterface= UserDaoOperations.getInstance();

    public UserOperation() {
        super();
    }

    /**
     * Method to get instance of UserInterface
     * @return
     */
    public static UserInterface getInstance() {
        if(instance == null){
            instance=  new UserOperation();
        }
        return instance;
    }

    /**
     * Method to updatePassword using userID
     * @param userID
     * @param newPassword
     * @return boolean to indicate password update or not
     */
    @Override
    public boolean updatePassword(String userID, String newPassword) {
        return userDaoInterface.updatePassword(userID, newPassword);
    }

    /**
     * Method to verfiy  User credentials
     * @param userID
     * @param password
     * @return boolean to indicate verifyCredentails or not
     * @throws UserNotFoundException
     */
    @Override
    public boolean verifyCredentials(String userID, String password)  throws UserNotFoundException {
        try
        {
            return userDaoInterface.verifyCredentials(userID, password);
        }
        finally
        {

        }

    }


    /**
     * Method to updateDetail using userId
     * @param userId
     * @param user
     */
    @Override
    public void updateDetails(String userId, User user) {

    }

    /**
     * Method to get role of a specific User
     * @param userId
     * @return Role of the User
     */
    @Override
    public String getRole(String userId) {
        return userDaoInterface.getRole(userId);
    }
}
