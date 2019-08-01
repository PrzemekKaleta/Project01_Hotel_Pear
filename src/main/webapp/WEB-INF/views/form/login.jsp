
<%--
  Created by IntelliJ IDEA.
  User: przemo
  Date: 01.08.19
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Login</title>
</head>
<body>

<%@ include file="../header.jspf" %>

<h1>LOGIN PAGE</h1>

<form:form method="POST" modelAttribute="personDTO">

    E-mail: <form:input path="email"/><br>
    <form:errors path="email" cssClass="error"/><br>
    Hasło: <form:password path="password"/><br>
    <form:errors path="password" cssClass="error"/><br>

    <input type="submit" value="Zaloguj">
</form:form>
<br>

<%@ include file="../footer.jspf" %>

</body>
</html>
