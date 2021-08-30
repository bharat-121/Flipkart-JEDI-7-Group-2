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
<h1>Student</h1>

<ul>
    <li>
        <h3>View Grades : GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewGradeCard?studentId=Student1</span></h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>CalulateFee - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/calculateFees?studentId=Student2</span></h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>ViewRegisteredCourses - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewRegisteredCourses?studentId=Student2/span></h3>
        <h3>Request : {}</h3>
    </li>

    <li>
        <h3>viewAvailabeCourses - GET</h3>
        <h3>EndPoint : <span style="color: green">http://localhost:8080/CRS_Jedi_07_Group_02_REST_war_exploded/rest/student/viewAvailableCourses?studentId=Student1</span></h3>
        <h3>Request : {}</h3>
    </li>
</ul>
<!--<form method="get" action="./rest/user/login">

    <label for="userId">UserID:</label><br>
    <input type="text" id="userId" name="userId"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br><br>
    <input type="submit" value="Login">

    <br/>
    <br/>
    <h1>Admin</h1>
    <button>Add Professor</button>
    <br/>
    <br/>
    <button>Add New Course</button>
    <br/>
    <br/>
    <button>Approve Student</button>
    <br/>
    <br/>
    <button>Assign Course To professor</button>
    <br/>
    <br/>
    <button>View Pending Admissions</button>
    <br/>
    <br/>
    <button>View Courses in catalog</button>
    <br/>
    <br/>
    <h1>Student</h1>
    <br/>
    <h1>Professor</h1>

    <button></button>
</form>

<form action="./rest/student/viewRegisteredCourses" method="get">
    <label for="studentId">Student ID:</label><br>
    <input type="text" id="studentId" name="studentId"><br>
    <input type="submit" value="viewRegisteredCourses">
</form> -->

</body>
</html>