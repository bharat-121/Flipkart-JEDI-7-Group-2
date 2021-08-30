<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CRS REST API</title>
</head>
<body>
<h1 style="text-align: center"><%= "Welcome to CRS REST API" %>
</h1>
<hr>
<h1>1. User</h1>
<ul>
    <li>
        <h3>Login : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/user/login?studentId=Student1&password=Bharat@123</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Update Password : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/user/updatePassword?userId=Student1&newPassword=123</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Get Role : Get</h3>
        <h3>EndPoint : <span style="color: green">localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/user/getRole?userId=Student1</span>
        </h3>
        <h3>Request : {}</h3>
    </li>
</ul>
<hr>
<h1>2. Admin</h1>
<ul>
    <li>
        <h3>Add Course : POST</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin/addCourse</span>
        </h3>
        <h3>Request : { "courseCode" : "FK10" ,
            "instructorId" : "Prof1",
            "courseName" : “Maths"
            }</h3>
    </li>

    <li>
        <h3>View Pending Admissions : GET</h3>
        <h3>EndPoint : <span style="color: green">localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin/viewPendingAdmissions</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>View Courses in Catalog : GET</h3>
        <h3>EndPoint : <span style="color: green">localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin/viewCoursesInCatalogue</span>
        </h3>
        <h3>Request : { "courseCode" : "FK10" ,
            "instructorId" : "Prof1",
            "courseName" : “Maths"
            }</h3>
    </li>

    <li>
        <h3>Delete Course : PUT</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin/deleteCourse?courseCode=FK10</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Approve Student : PUT</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin//approveStudent?studentId=Student2</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Add Professor : POST</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/admin/addProfessor</span>
        </h3>
        <h3>Request : {
            "userID" : "prof3",
            "name" : "Jashpreet" ,
            "role" : "PROFESSOR" ,
            "password" : "prof3@123" ,
            "email" : "jashpreet@gmail.com" ,
            "phone" : "9876543210" ,
            "designation" : "HOD" ,
            "department" : "CSE"
            } </h3>
    </li>
</ul>

<hr>
<h1>3. Professor</h1>
<ul>
    <li>
        <h3>View Enrolled Students : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/professor/getEnrolledStudents?professorId=Prof1</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Get Courses : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/professor/getCourses?professorId=Prof1</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Add Grades : POST</h3>
        <h3>EndPoint : <span style="color: green">localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/professor/addGrade?studentId=Student1&courseCode=FK1&professorId=Prof1&grade=A</span>
        </h3>
        <h3>Request : {}</h3>
    </li>
</ul>

<h1>1. Student</h1>

<ul>
    <li>
        <h3>View Grades : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewGradeCard?studentId=Student1</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>CalulateFee - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/calculateFees?studentId=Student2</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>ViewRegisteredCourses - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewRegisteredCourses?studentId=Student2</span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>viewAvailabeCourses - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewAvailableCourses?studentId=Student1</span>
        </h3>
        <h3>Request : {}</h3>
    </li>
    <li>
        <h3>Make_payment - POST</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/make_payment?studentId=Student2&paymentMode=1
    </span></h3>
        <h3>Request : {}</h3>
    </li>


    <li>
        <h3>Add Course - PUT</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/addCourse?courseCode=FK1&studentId=Student2
    </span></h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Drop Course - DELETE</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/dropCourse?courseCode=FK1&studentId=Student2 </span>
        </h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>Register Courses - POST</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/registerCourses?studentId=Student3    </span>
        </h3>
        <h3>Request : {["FK1","FK2","FK3","FK4","FK5","FK6"]}</h3>
    </li>

</ul>


</body>
</html>