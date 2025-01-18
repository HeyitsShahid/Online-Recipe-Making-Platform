<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>

    <!-- Use JSTL to iterate over the users list -->
    <c:if test="${not empty users}">
        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through users using <c:forEach> -->
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- If the users list is empty -->
    <c:if test="${empty users}">
        <p>No users found!</p>
    </c:if>
</body>
</html>
