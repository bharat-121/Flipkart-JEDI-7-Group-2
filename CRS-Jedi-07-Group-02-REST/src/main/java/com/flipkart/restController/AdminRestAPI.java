package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperations;
import com.flipkart.exception.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
public class AdminRestAPI {

    AdminInterface adminOperation = AdminOperations.getInstance();

    /**
     * /admin/assignCourseToProfessor
     * REST-service for assigning course to professor
     * @param courseCode
     * @param professorId
     * @return
     */
    @POST
    @Path("/assignCourseToProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response assignCourseToProfessor(
            @NotNull
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("professorId") String professorId) throws ValidationException {

        try {

            adminOperation.assignCourse(courseCode, professorId);
            return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + professorId).build();

        } catch (CourseNotFoundException | UserNotFoundException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }
    }

    /**
     * /admin/addProfessor
     * REST-service for addding a new professor
     *
     * @param professor
     * @return
     */
    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@Valid Professor professor) throws ValidationException {

        try {

            adminOperation.addProfessor(professor);
            return Response.status(201).entity("Professor with professorId: " + professor.getUserID() + " added").build();

        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }

    /**
     * /admin/viewPendingAdmissions
     * REST-service for getting all pending-approval of students
     *
     * @return
     */
    @GET
    @Path("/viewPendingAdmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingAdmissions() {

        return adminOperation.viewPendingAdmissions();

    }

    //approve student

    /**
     * /admin/approveStudent
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        List<Student> studentList = adminOperation.viewPendingAdmissions();

        try {

            adminOperation.approveStudent(studentId, studentList);
            return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();

        } catch (StudentNotFoundForApprovalException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }



    /**
     * /admin/viewCoursesInCatalogue
     * REST-service for getting courses in the catalog
     *
     * @return
     */
    @GET
    @Path("/viewCoursesInCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCoursesInCatalogue() {

        return adminOperation.viewCourses();

    }

    /**
     * /admin/deleteCourse
     * REST-services for dropping a course from catalog
     *
     * @param courseCode
     * @return
     */
    @PUT
    @Path("/deleteCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(
            @NotNull
            @QueryParam("courseCode") String courseCode) throws ValidationException {
        List<Course> courseList = adminOperation.viewCourses();

        try {

            adminOperation.deleteCourse(courseCode, courseList);
            return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();

        } catch (CourseNotDeletedException e) {
            e.printStackTrace();
        } catch (CourseNotFoundException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }

        return Response.serverError().build();
    }

    /**
     * /admin/addCourse
     * REST-service for adding a new course in catalog
     *
     * @param course
     * @return
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@Valid Course course) throws ValidationException {
        List<Course> courseList = adminOperation.viewCourses();

        try {

            adminOperation.addCourse(course, courseList);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();

        } catch (CourseFoundException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }
}
