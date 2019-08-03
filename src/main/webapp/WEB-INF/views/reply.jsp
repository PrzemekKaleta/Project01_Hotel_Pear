<%--
  Created by IntelliJ IDEA.
  User: przemo
  Date: 03.08.19
  Time: 09:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>reply</title>
</head>
<body>
<%@ include file="header.jspf" %>

<h1>REPLY PAGE</h1>

<p>Lorem ipsum dolor sit amet</p>

<c:if test="${reply==true}">
    <h2>Jest dostępny pokój w wybranym terminie</h2>
    Koszt pobytu wynosi: ${cost} PLN
    <br>
    <c:if test="${emptyLog==false}">

        <input type="submit" value="Zatwierdź">
    </c:if>
    <c:if test="${emptyLog==true}">
         Zarejestruj lub Zaloguj jeżeli chcesz potwierdzić rezerwację)
        <br>

        <button onclick="location.href='/person/register'" type="button">Zarejestruj</button>
        <button onclick="location.href='/person/login'" type="button">Zaloguj</button>

    </c:if>
</c:if>

<c:if test="${reply==false}">
    <h2>Nie mamy dostępnego pokoju w wybranym terminie.</h2>
</c:if>





<%@ include file="footer.jspf" %>

</body>
</html>
