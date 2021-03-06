package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JEDI-07
 * Dao Class Operations for Admin
 *
 */
public class AdminDaoOperations implements AdminDaoInterface{

    private static volatile AdminDaoOperations instance = null;
    private static Logger logger = Logger.getLogger(AdminDaoOperations.class);
    private PreparedStatement statement = null;

    /**
     * Default Constructor
     */
    private AdminDaoOperations(){}

    /**
     * Method to make AdminDaoOperation Singleton
     * @return
     */
    public static AdminDaoOperations getInstance()
    {
        if(instance == null)
        {
            synchronized(AdminDaoOperations.class){
                instance = new AdminDaoOperations();
            }
        }
        return instance;
    }

    Connection connection = DBUtil.getConnection();

    /**
     * Delete Course using SQL commands
     * @param courseCode
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    @Override
    public void deleteCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException {

        statement = null;
        try {
            String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1,courseCode);
            int row = statement.executeUpdate();

            logger.info(row + " entries deleted.");
            if(row == 0) {
                logger.info(courseCode + " not in catalog!");
                throw new CourseNotFoundException(courseCode);
            }

            logger.info("Course with courseCode: " + courseCode + " deleted.");

        }catch(SQLException se) {

            logger.error(se.getMessage());
            throw new CourseNotDeletedException(courseCode);
        }

    }

    /**
     * Add Course using SQL commands
     * @param course
     * @throws  CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException {

        statement = null;
        try {

            String sql = SQLQueriesConstants.ADD_COURSE_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setString(3,course.getInstructorId());
            statement.setInt(4, course.getSeats());

            int row = statement.executeUpdate();

            logger.info(row + " course added");
            if(row == 0) {
                logger.info("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseCode());
            }

            logger.info("Course with courseCode: " + course.getCourseCode() + " is added to catalog.");

        }catch(SQLException se) {

            logger.error(se.getMessage());
            throw new CourseFoundException(course.getCourseCode());
        }

    }

    /**
     * Approve Student using SQL commands
     * @param studentId
     * @throws StudentNotFoundForApprovalException
     */
    @Override
    public void approveStudent(String studentId) throws StudentNotFoundForApprovalException {
        statement = null;
        try {
            String sql = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1,studentId);
            int row = statement.executeUpdate();

            logger.info(row + " student approved.");
            if(row == 0) {
                logger.info("Student with studentId: " + studentId + " not found.");
                throw new StudentNotFoundForApprovalException(studentId);
            }

            logger.info("Student with studentId: " + studentId + " approved by admin.");

        }catch(SQLException se) {

            logger.error(se.getMessage());
        }

    }

    /**
     *  Method to add user using SQL commands
     * @param user
     * @throws UserNotAddedException
     * @throws UserIdAlreadyInUseException
     */
    @Override
    public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException{

        statement = null;
        try {

            String sql = SQLQueriesConstants.ADD_USER_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserID());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getRole());
            statement.setString(6, user.getPhone());

            int row = statement.executeUpdate();

            logger.info(row + " user added.");
            if(row == 0) {
                logger.info("User with userId: " + user.getUserID() + " not added.");
                throw new UserNotAddedException(user.getUserID());
            }

            logger.info("User with userId: " + user.getUserID() + " added.");

        }
        catch(SQLException se) {

            logger.error(se.getMessage());
            throw new UserIdAlreadyInUseException(user.getUserID());
        }

    }

    /**
     * Add professor using SQL commands
     * @param professor
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     */
    @Override
    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException{

        try {
            this.addUser(professor);

        }catch (UserNotAddedException e) {

            logger.error(e.getMessage());
            throw new ProfessorNotAddedException(professor.getUserID());

        }catch (UserIdAlreadyInUseException e) {

            logger.error(e.getMessage());
            throw e;

        }

        statement = null;
        try {

            String sql = SQLQueriesConstants.ADD_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1, professor.getUserID());
            statement.setString(2, professor.getDepartment());
            statement.setString(3, professor.getDesignation());

            int row = statement.executeUpdate();

            logger.info(row + " professor added.");
            if(row == 0) {
                logger.info("Professor with professorId: " + professor.getUserID() + " not added.");
                throw new ProfessorNotAddedException(professor.getUserID());
            }
            logger.info("Professor with professorId: " + professor.getUserID() + " added.");

        }catch(SQLException se) {

            logger.error(se.getMessage());
            throw new UserIdAlreadyInUseException(professor.getUserID());
        }

    }

    /**
     * Assign courses to Professor using SQL commands
     * @param courseCode
     * @param professorId
     * @throws CourseNotFoundException
     * @throws UserNotFoundException
     */
    @Override
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{

        statement = null;
        try {
            String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1,professorId);
            statement.setString(2,courseCode);
            int row = statement.executeUpdate();

            logger.info(row + " course assigned.");
            if(row == 0) {
                logger.info(courseCode + " not found");
                throw new CourseNotFoundException(courseCode);
            }

            logger.info("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");

        }catch(SQLException se) {

            logger.error(se.getMessage());
            throw new UserNotFoundException(professorId);
        }

    }

    /**
     * Fetch Students yet to approved using SQL commands
     * @return List of Students yet to approved
     */
    @Override
    public List<Student> viewPendingAdmissions() {

        statement = null;
        List<Student> userList = new ArrayList<Student>();
        try {

            String sql = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Student user = new Student();
                user.setUserID(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole("STUDENT");
                user.setUserID(resultSet.getString(1));
                userList.add(user);

            }

            logger.info(userList.size() + " students have pending-approval.");

        }catch(SQLException se) {

            logger.error(se.getMessage());

        }

        return userList;

    }

    /**
     * View courses in the catalog
     * @return List of courses in the catalog
     */
    public List<Course> viewCourses() {

        statement = null;
        List<Course> courseList = new ArrayList<>();
        try {

            String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Course course = new Course();
                course.setCourseCode(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setInstructorId(resultSet.getString(3));
                courseList.add(course);

            }

//            logger.info(courseList);

        }catch(SQLException se) {

            logger.error(se.getMessage());

        }

        return courseList;

    }

    /**
     * View professor in the institute
     * @return List of the professors in the institute
     */
    @Override
    public List<Professor> viewProfessors() {

        statement = null;
        List<Professor> professorList = new ArrayList<>();
        try {

            String sql = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Professor professor = new Professor();
                professor.setUserID(resultSet.getString(1));
                professor.setName(resultSet.getString(2));
                professor.setDepartment(resultSet.getString(4));
                professor.setDesignation(resultSet.getString(5));
                professor.setRole("PROFESSOR");
                professor.setPassword("*********");
                professorList.add(professor);

            }

            logger.info(professorList.size() + " professors in the institute.");

        }catch(SQLException se) {

            logger.error(se.getMessage());

        }
        return professorList;
    }
}