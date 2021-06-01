<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title>Title</title>
</head>
<div>
    <div class="container">
        <%@include file="fragments/navbar.jsp"%>
        <form method="POST">
            <c:if test="${!empty requestScope.creationError}">
            <div class="alert-danger error-message" >
                <fmt:message key="creation.error" />
            </div>
            </c:if>
            <div class="form-group">
                <label><fmt:message key="departure.order"/></label>
                <c:if test="${!empty requestScope.departureEmpty}">
                    <div class="alert-danger error-message" >
                        <fmt:message key="field.empty" />
                    </div>
                </c:if>
                <input type="text" class="form-control" name="departure"  value="${values.departure}"/>
            </div>
            <div class="form-group">
                <label><fmt:message key="arrival.order"/></label>
                <c:if test="${!empty requestScope.arrivalEmpty}">
                    <div class="alert-danger error-message" >
                        <fmt:message key="field.empty" />
                    </div>
                </c:if>
                <input type="text" class="form-control" name="arrival"  value="${values.arrival}"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block btn-lg"><fmt:message key="submit.button"/></button>
            </div>
        </form>
    </div>
</body>
</html>
