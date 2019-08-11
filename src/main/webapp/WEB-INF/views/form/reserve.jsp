<%--
  Created by IntelliJ IDEA.
  User: przemo
  Date: 27.07.19
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>reserv</title>
</head>
    <body>

    <%@ include file="../header.jspf" %>

    <h1>RESERVATION PAGE</h1>

    <p>Velit egestas dui id ornare arcu odio. Montes nascetur ridiculus mus mauris vitae. Nunc eget lorem dolor sed viverra
        ipsum nunc aliquet. Tempus egestas sed sed risus pretium quam. Magna sit amet purus gravida quis blandit turpis
        cursus in. Pharetra vel turpis nunc eget. Urna nunc id cursus metus aliquam eleifend mi in. Ultrices dui sapien
        eget mi. Arcu odio ut sem nulla pharetra diam sit amet. Convallis tellus id interdum velit laoreet. Nulla posuere
        sollicitudin aliquam ultrices sagittis orci a. Sit amet justo donec enim diam vulputate ut pharetra sit.</p>

    <form:form method="post" modelAttribute="reserveAsk">

        Data Przyjazdu: <form:select path="dateFrom">
                            <form:options items="${dateFrom}"/>
                        </form:select>

        Data odjazdu:   <form:select path="dateUntil">
                            <form:options items="${dateUntil}"/>
                        </form:select>
        <form:errors path="dateFrom" cssClass="error" />
        <br>
        Ilość osób <form:radiobuttons items="${roomsCapacity}" path="persons" /><br>
        <form:errors path="persons" cssClass="error" />

        <input type="submit" value="Sprawdź dostępność">

    </form:form>

    <%@ include file="../footer.jspf" %>

    </body>
</html>
