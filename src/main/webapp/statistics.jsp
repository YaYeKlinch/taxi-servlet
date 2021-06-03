<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title>Title</title>
</head>
<body>
<div class="container">
    <%@include file="fragments/navbar.jsp"%>
    <table class="table">
        <tr>
            <th >  <fmt:message key="car.name" /></th>
            <th >  <fmt:message key="totalDistance.statistics" /></th>
            <th>  <fmt:message key="totalCosts.statistics" /></th>
        </tr>
        <c:forEach items="${cars}" var="c" >
            <tr>
                <td>${c.carName}</td>
                <td>${c.totalDistance}</td>
                <td>${c.totalCosts}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="clearfix">
        <div class="hint-text"><fmt:message key="pagination.label.showing"/> <b><c:out
                value="${fn:length(requestScope.orders)}"/></b> <fmt:message key="pagination.label.outOf"/>
            <b>${requestScope.records}</b> <fmt:message key="pagination.label.entries"/></div>


        <ul class="pagination">
            <c:if test="${1 ne requestScope.page}">
                <li class="page-item"><a class="page-link"
                                         href="/all-orders/statisticspage=${requestScope.page - 1}&recordsPerPage=${requestScope.recordsPerPage}">
                    <fmt:message key="pagination.previous"/></a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${requestScope.page + 1 eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only"><fmt:message key="pagination.current"/></span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="/all-orders/statistics?page=${i}&recordsPerPage=${requestScope.recordsPerPage}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${requestScope.page lt requestScope.numberOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="/all-orders/statistics?page=${requestScope.page + 1}&recordsPerPage=${requestScope.recordsPerPage}"><fmt:message key="pagination.next"/></a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
