package com.flipkart.bean;

/**
 *
 * @author JEDI-07
 * User Class
 *
 */

public class User {
    private String userID;
    private String name;
    private String role;
    private String password;
    private String phone;
    private String email;
    /**
     * Method to get User's Role
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Method to set User's Role
     * @param role
     */

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method to set password
     * @return password
     */

    public String getPassword() {
        return password;
    }

    /**
     * Method to set password
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get User's name
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     * Method to set User's name
     * @param name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get User's id
     * @return userID
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * Method to set User's ID
     * @param userID
     */

    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Method to get User's phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method to set User's phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Method to get User's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to get User's Role
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
