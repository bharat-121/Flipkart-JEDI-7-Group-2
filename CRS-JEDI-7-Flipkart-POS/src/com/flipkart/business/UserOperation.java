package com.flipkart.business;

import com.flipkart.bean.User;

public class UserOperation implements UserInterface{
    public UserOperation() {
        super();
    }

    @Override
    public boolean updatePassword(String userID, String newPassword) {
        return false;
    }

    @Override
    public boolean verifyCredentials(String userID, String password) {
        return false;
    }


    @Override
    public void updateDetails(String userId, User user) {

    }
}
