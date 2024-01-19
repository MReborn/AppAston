<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Макс
  Date: 29.11.2022
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Employees</title>
</head>
<body>
<h1>All Employees</h1>
<br><br>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Department</th>
        <th>Salary</th>
    </tr>
    <c:forEach var="emps" items="${allEmpls}">
        <tr>
            <td>${emps.name}</td>
            <td>${emps.surname}</td>
            <td>${emps.department}</td>
            <td>${emps.salary}</td>
        </tr>

    </c:forEach>
</table>
<br>
<input type="button" name="Add"
onclick="window.location.href='addNewEmployee'">
</body>
</html>
