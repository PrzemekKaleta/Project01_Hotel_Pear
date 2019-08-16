<%--
  Created by IntelliJ IDEA.
  User: przemo
  Date: 15.08.19
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>reservations</title>
</head>
<body>


<%@ include file="header.jspf" %>

<h1>RESERVATIONS PAGE</h1>

<p>Rezerwacje:...</p>


<table style="background-color: lavender" border="solid">
    <tr>
        <td>Id pokoju</td>
        <c:forEach items="${fullmap}" var="mapUp2" begin="0" end="0">
            <c:forEach items="${mapUp2.value}" var="ooo">
                <td>${ooo.key}</td>
            </c:forEach>

        </c:forEach>

    </tr>


    <c:forEach items="${fullmap}" var="mapUp">
        <tr>
            <c:forEach items="${mapUp.value}" var="mapUpValue">

            </c:forEach>

        </tr>
        <tr>
            <td>${mapUp.key}</td>
            <c:forEach items="${mapUp.value}" var="mapUpValue">
                <td>${mapUpValue.value.name}<br>
                        ${mapUpValue.value.stayState}</td>
            </c:forEach>

        </tr>

    </c:forEach>
</table>


<%@ include file="footer.jspf" %>

</body>
</html>
