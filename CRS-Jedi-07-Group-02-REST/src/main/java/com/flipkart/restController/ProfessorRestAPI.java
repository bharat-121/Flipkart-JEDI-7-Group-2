package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperations;
import com.flipkart.validator.ProfessorValidator;

import javax.validation.ValidationException;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 * @author JEDI - 07
 * Professor API File
 */



@Path("/professor")
public class ProfessorRestAPI {
    ProfessorInterface professorInterface= ProfessorOperations.getInstance();

    /**
     * Method to get enrolled students
     * @param professorId
     * @return
     * @throws ValidationException
     */

    @GET
    @Path("/getEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnrolledStudent> viewEnrolledStudents(
            @NotNull
            @QueryParam("professorId") String professorId) throws ValidationException {

        List<EnrolledStudent> students;
        try
        {
            students=professorInterface.viewEnrolledStudents(professorId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return students;
    }

    /**
     * Mrthod to get courses
     * @param profId
     * @return
     * @throws ValidationException
     */

    @GET
    @Path("/getCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(
            @NotNull
            @QueryParam("professorId") String profId) throws ValidationException	{

        List<Course> courses;
        try
        {
            courses=professorInterface.getCourses(profId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return courses;

    }

    /**
     * Method to add grades
     * @param studentId
     * @param courseCode
     * @param professorId
     * @param grade
     * @return
     * @throws ValidationException
     */

    @POST
    @Path("/addGrade")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @QueryParam("studentId") String studentId,
            @NotNull
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("professorId") String professorId,
            @QueryParam("grade") String grade) throws ValidationException   	{

        try
        {
            System.out.println(professorId);
            List<EnrolledStudent> enrolledStudents;
            enrolledStudents=professorInterface.viewEnrolledStudents(professorId);
            List<Course> coursesEnrolled;
            coursesEnrolled	=professorInterface.getCourses(professorId);
            if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))
            {
                professorInterface.addGrades(studentId, courseCode, grade);
            }
            else
            {
                return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
            }
        }
        catch(Exception ex)
        {
            return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
        }
        return Response.status(200).entity( "Grade updated for student: "+studentId).build();

    }
}