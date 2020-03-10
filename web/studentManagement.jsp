<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: linuxmachine
  Date: 2020-03-09
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }

    button {
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

<h2> Student management </h2>

<button>Add student</button>

<table>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>University Group</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>${student.universityGroup}</td>
            <td>${student.email}</td>
            <td>
                <a href="">Update</a> |
                <a href="">Delete</a>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td>John</td>
        <td>Doe</td>
        <td>IT4</td>
        <td>john.doe@example.com</td>
        <td>
            <a href="">Update</a> |
            <a href="">Delete</a>
        </td>
    </tr>
</table>
${test}

</body>
</html>
