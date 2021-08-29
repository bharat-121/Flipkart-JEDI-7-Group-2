package com.flipkart.constants;
/**
 *
 * @author JEDI-07
 * Enumeration class for Role
 *
 */
public enum Role {
    ADMIN,PROFESSOR,STUDENT;

    public static Role stringToName(String role)
    {
        Role userRole=null;

        if(role.equalsIgnoreCase("ADMIN"))
            userRole=Role.ADMIN;
        else if(role.equalsIgnoreCase("PROFESSOR"))
            userRole=Role.PROFESSOR;
        else if(role.equalsIgnoreCase("STUDENT"))
            userRole=Role.STUDENT;
        return userRole;
    }
}