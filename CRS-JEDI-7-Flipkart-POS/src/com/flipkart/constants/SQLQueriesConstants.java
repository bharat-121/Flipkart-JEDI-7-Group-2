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


    // Student Queries
    public static final String ADD_COURSE="insert into registeredcourse (studentId,courseCode) values ( ? , ? )";
    public static final String DECREMENT_COURSE_SEATS="update course set seats = seats-1 where courseCode = ? ";
    public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from registeredcourse where studentId = ? ";
    public static final String GET_SEATS = "select seats from course where courseCode = ?;";
    public static final String IS_REGISTERED=" select courseCode from registeredcourse where courseCode=? and studentId=? ";
    public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
    public static final String INCREMENT_SEAT_QUERY  = "update course set seats = seats + 1 where  courseCode = ?;";
    public static final String CALCULATE_FEES  = "select sum(courseFee) from course where courseCode in (select courseCode from registeredcourse where studentId = ?);";
    public static final String VIEW_AVAILABLE_COURSES=" select * from course where courseCode not in  (select courseCode  from registeredcourse where studentId = ?) and course.isOffered = ? and seats > 0";
    public static final String VIEW_REGISTERED_COURSES=" select * from course inner join registeredcourse on course.courseCode = registeredcourse.courseCode where registeredcourse.studentId = ?";
    public static final String GET_REGISTRATION_STATUS=" select isRegistered from student where studentId = ? ";
    public static final String SET_REGISTRATION_STATUS="update student set isRegistered = true  where studentId=?";

}
