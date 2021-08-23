package com.flipkart.bean;

public class Professor extends User {
    private String designation;
    private String department;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

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
