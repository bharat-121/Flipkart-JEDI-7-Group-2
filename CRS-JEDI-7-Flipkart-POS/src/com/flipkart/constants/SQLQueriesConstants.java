package com.flipkart.constants;

public class SQLQueriesConstants {

    //User DAO Queries
    public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";


    public static final String GET_COURSES="select * from course where instructorId=?";
    public static final String GET_ENROLLED_STUDENTS="select course.courseCode,course.courseName,registeredcourse.studentId from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where course.instructorId = ? order by course.courseCode";
    public static final String ADD_GRADE="update registeredcourse set Grade=? where courseCode=? and studentId=?";
    public static final String GET_PROF_NAME = "select name from user where userId = ?";
}
