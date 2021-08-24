package com.flipkart.business;

import com.flipkart.bean.User;

public class UserOperation implements UserInterface{
    private static UserOperation instance=null;
    public UserOperation() {
        super();
    }

    public static UserInterface getInstance() {
        if(instance == null){
            instance=  new UserOperation();
        }
        return instance;
    }

    @Override
    public boolean updatePassword(String userID, String newPassword) {
        return false;
    }

    @Override
    public boolean verifyCredentials(String userID, String password) {
        return true;
    }


    @Override
    public void updateDetails(String userId, User user) {

    }

    @Override
    public String getRole(String userId) {
        return "ADMIN";
    }
}
