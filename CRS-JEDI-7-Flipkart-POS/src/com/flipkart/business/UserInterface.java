package com.flipkart.business;

import com.flipkart.bean.User;

public interface UserInterface {
    boolean updatePassword(String userID, String newPassword);

    public boolean verifyCredentials(String userID, String password);

    public void updateDetails(String userId, User user);

    String getRole(String userId);
}
