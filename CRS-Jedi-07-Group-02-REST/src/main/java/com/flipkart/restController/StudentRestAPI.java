package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.*;

import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import org.apache.log4j.Logger;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JEDI - 07
 * Student API File
 */

@Path("/student")
public class StudentRestAPI {
    RegistartionInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperations.getInstance();

    StudentInterface studentInterface = StudentOperations.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    private static Logger logger = Logger.getLogger(StudentRestAPI.class);

    /**
     * Method to handle API request for course registration
     *
     * @param courseList
     * @param studentId
     * @return
     * @throws ValidationException
     */

    @POST
    @Path("/registerCourses")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourses(List<String> courseList,
                                    @NotNull
                                    @QueryParam("studentId") String studentId) throws ValidationException {

        try {
            List<Course> availableCourseList = registrationInterface.viewAvailableCourses(studentId);
            Set<String> hash_set = new HashSet<String>();

            for (String courseCode : courseList) {
                registrationInterface.checkCourse(courseCode, studentId, availableCourseList);

                if (!hash_set.add(courseCode))
                    return Response.status(500).entity("Duplicate value  : " + courseCode).build();
            }

            for (String courseCode : courseList)
                registrationInterface.addCourse(courseCode, studentId, null);

            registrationInterface.setRegistrationStatus(studentId);
        } catch (CourseLimitExceedException | SQLException | SeatNotAvailableException | CourseNotFoundException | CourseAlreadyRegisteredException e) {
            logger.info(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }


        return Response.status(201).entity("Registration Successful").build();

    }

    /**
     * Method handles API request to view the list of available courses for a student
     *
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewAvailableCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {

        return registrationInterface.viewAvailableCourses(studentId);

    }

    /**
     * Method handles API request to view the list of registered courses for a student
     *
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewRegisteredCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourses(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {
        return registrationInterface.viewRegisteredCourses(studentId);
    }

    /**
     * Method handles request to display the total fees for student
     *
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/calculateFees")
    public Response calculateFee(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {
        try {
            double fee = registrationInterface.calculateFee(studentId);
            return Response.status(200).entity("Your total fee  = " + fee + "\n").build();
        } catch (Exception ex) {
            return Response.status(500).entity(" DB is down. " + "\n").build();
        }
    }

    /**
     * Method handles request to display the grade card for student
     *
     * @param studentId
     * @return
     * @throws ValidationException
     */

    @GET
    @Path("/viewGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<GradeCard> viewGradeCard(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {


        List<GradeCard> gradeCards = studentInterface.viewGradeCard(studentId);

        return gradeCards;

    }

}
