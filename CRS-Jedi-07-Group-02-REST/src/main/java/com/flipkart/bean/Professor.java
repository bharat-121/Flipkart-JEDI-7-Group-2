package com.flipkart.bean;

/**
 * @author JEDI-07
 * Class to store Professor information
 */

public class Professor extends User {
    private String designation;
    private String department;
    /**
     * Method to get the designation
     * @return: get the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Method to get the designation
     * @param designation: set the designation
     */

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Method to get the department
     * @return departmet
     */

    public String getDepartment() {
        return department;
    }

    /**
     * Method to set the designation
     * @param department
     */

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
