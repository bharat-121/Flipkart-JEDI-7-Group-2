package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.business.*;
import com.flipkart.constants.ModeOfPayment;
import com.flipkart.constants.NotificationType;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import org.apache.log4j.Logger;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
                                    @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {
        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }

        try {
            List<Course> availableCourseList = registrationInterface.viewAvailableCourses(studentId);
            Set<String> hash_set = new HashSet<String>();

            for (String courseCode : courseList) {
                registrationInterface.checkCourse(courseCode, studentId, availableCourseList);

                if (!hash_set.add(courseCode))
                    return Response.status(500).entity("Duplicate value  : " + courseCode).build();
            }
            List<Course> registeredCourseList = registrationInterface.viewRegisteredCourses(studentId);

            for (String courseCode : courseList)
                registrationInterface.addCourse(courseCode, studentId, availableCourseList);

            registrationInterface.setRegistrationStatus(studentId);
        } catch (CourseLimitExceedException | SQLException | SeatNotAvailableException | CourseNotFoundException | CourseAlreadyRegisteredException e) {
            logger.info(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
        }


        return Response.status(201).entity("Registration Successful").build();

    }

    /**
     * Handles api request to add a course
     *
     * @param courseCode
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @PUT
    @Path("/addCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(
            @NotNull
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }


        try {
            if (registrationInterface.getRegistrationStatus(studentId) == false)
                return Response.status(200).entity("Student course registration is pending").build();

            List<Course> registeredCourseList = registrationInterface.viewRegisteredCourses(studentId);

            List<Course> availCourseList = registrationInterface.viewAvailableCourses(studentId);
            registrationInterface.checkCourse(courseCode, studentId, availCourseList);
            registrationInterface.addCourse(courseCode, studentId, availCourseList);

            return Response.status(201).entity("You have successfully added Course : " + courseCode).build();

        } catch (Exception e) {
            logger.info(e.getMessage());

            return Response.status(500).entity(e.getMessage()).build();
        }


    }


    /**
     * Handles API request to drop a course
     *
     * @param courseCode
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @DELETE
    @Path("/dropCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(
            @NotNull
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {


        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }

        try {
            if (registrationInterface.getRegistrationStatus(studentId) == false)
                return Response.status(200).entity("Student course registration is pending").build();

            List<Course> registeredCourseList = registrationInterface.viewRegisteredCourses(studentId);
            registrationInterface.dropCourse(courseCode, studentId, registeredCourseList);
            return Response.status(201).entity("You have successfully dropped Course : " + courseCode).build();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return Response.status(501).entity("You have not registered for course : " + courseCode).build();
        }

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
    public Object viewCourse(
            @NotNull
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }

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
    public Object viewRegisteredCourses(
            @NotNull
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }
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
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }
        try {
            double fee = registrationInterface.calculateFee(studentId);
            return Response.status(200).entity("Your total fee  = " + fee + "\n").build();
        } catch (Exception ex) {
            return Response.status(500).entity(" DB is down. " + "\n").build();
        }
    }

    /**
     * Method handles API request to make payment for registered courses
     *
     * @param studentId
     * @param paymentMode
     * @return
     * @throws ValidationException
     */
    @POST
    @Path("/make_payment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment(
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @Min(value = 1)
            @Max(value = 3)
            @QueryParam("paymentMode") int paymentMode,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }
        try {

            double fee = registrationInterface.calculateFee(studentId);
            if (fee == 0.0) {
                return Response.status(200).entity("You have not  registered for any courses yet" + "\n").build();
            }
            logger.info("Your total fee  = " + fee);
            boolean paymentStatus = registrationInterface.getPaymentStatus(studentId);
            if (paymentStatus == true) {
                return Response.status(200).entity("Fees has already been paid" + "\n").build();
            }

            ModeOfPayment mode = ModeOfPayment.getModeofPayment(paymentMode);


            boolean payment = studentInterface.payFees(studentId);
            if (payment == true) {
                int notificationId = notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee);
                logger.info("Your Payment is successful");
                logger.info("Your transaction id : " + notificationInterface.getReferenceId(notificationId));
                return Response.status(201).entity("Your total fee  = " + fee + "\n" + "Your Payment is successful\n" + "Your transaction id : " + notificationInterface.getReferenceId(notificationId) +"\n").build() ;
            } else {
                return Response.status(500).entity("Payment Unsuccessful . Internal Server Error. " + "\n").build();
            }

        } catch (Exception e) {
            return Response.status(500).entity("Payment Unsuccessful . Internal Server Error. " + "\n").build();
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
    public Object viewGradeCard(
            @NotNull
            @QueryParam("studentId") String studentId,@HeaderParam("authKey") String authKey) throws ValidationException {

        if(com.flipkart.restController.UserAuth.isStudentLogin(authKey) == null){
            return Response.status(403).entity("Access Denied").build();
        }

        List<GradeCard> gradeCards = studentInterface.viewGradeCard(studentId);

        return gradeCards;

    }

}
