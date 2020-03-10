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
        <h2> ${AddOrUpdate} student </h2> <br>
        ${errorCode}<br>
        <form action="ControllerServlet?action=${addupdate}" method="POST">
            <input hidden type="text" name="id" value="${id}"><br>
            <label for="firstname">First name:</label><br>
            <input type="text" id="firstname" name="firstname" value="${firstName}"><br>
            <label for="lastname">Last name:</label><br>
            <input type="text" id="lastname" name="lastname" value="${lastName}"><br>
            <label for="universityGroup">University group:</label><br>
            <input type="text" id="universityGroup" name="universityGroup" value="${universityGroup}"><br>
            <label for="email">Email:</label><br>
            <input type="text" id="email" name="email" value="${email}"><br><br>
            <input type="submit" class="button" value="Submit">
        </form>
    </body>
</html>
