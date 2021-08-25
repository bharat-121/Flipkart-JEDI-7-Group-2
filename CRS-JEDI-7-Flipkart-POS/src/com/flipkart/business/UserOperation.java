package com.flipkart.business;

import com.flipkart.bean.User;

public class UserOperation implements UserInterface{
    private static UserOperation instance=null;
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
        return false;
    }

    /**
     * Method to verfiy  User credentials
     * @param userID
     * @param password
     * @return boolean to indicate verifyCredentails or not
     */
    @Override
    public boolean verifyCredentials(String userID, String password) {
        return true;
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
        return "ADMIN";
    }
}
