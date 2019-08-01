
<%--
  Created by IntelliJ IDEA.
  User: przemo
  Date: 01.08.19
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>registration</title>
</head>
<body>

<%@ include file="../header.jspf" %>

<h1>REGISTARTION PAGE</h1>

<form:form method="POST" modelAttribute="guest">

    E-mail: <form:input path="email"/><br>
    <form:errors path="email" cssClass="error"/><br>
    Hasło: <form:password path="password"/><br>
    <form:errors path="password" cssClass="error"/><br>
    Imie: <form:input path="name"/><br>
    <form:errors path="name" cssClass="error"/><br>
    Nazwisko: <form:input path="surname"/><br>
    <form:errors path="surname" cssClass="error"/><br>
    <input type="submit" value="Zarejestruj">
</form:form>
<br>

<%@ include file="../footer.jspf" %>

</body>
</html>
