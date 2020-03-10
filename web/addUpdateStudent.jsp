<%--
  Created by IntelliJ IDEA.
  User: linuxmachine
  Date: 2020-03-09
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:include page="header.jsp"/>
    <style>
        .button {
            background-color: #008CBA; /* Blue */
            border: none;
            color: white;
            padding: 12px 18px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
        }
    </style>
    <body>
        <h2> Add/Update student </h2> <br>
        <form action="StudentManagementAddUpdateServlet" method="POST">
            <label for="firstname">First name:</label><br>
            <input type="text" id="firstname" name="firstname" value="John"><br>
            <label for="lastname">Last name:</label><br>
            <input type="text" id="lastname" name="lastname" value="Doe"><br>
            <label for="universityGroup">University group:</label><br>
            <input type="text" id="universityGroup" name="universityGroup" value="IT4"><br>
            <label for="email">Email:</label><br>
            <input type="text" id="email" name="email" value="email@example.com"><br><br>
            <input type="submit" class="button" value="Submit">
        </form>
    </body>
</html>
