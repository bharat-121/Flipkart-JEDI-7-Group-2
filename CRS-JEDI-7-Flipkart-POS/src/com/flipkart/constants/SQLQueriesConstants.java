package com.flipkart.constants;

public class SQLQueriesConstants {
    public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
}
