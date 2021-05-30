<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="grid-container">
        <div class="grid-item car-item">
        <c:forEach items="${cars}" var="car" >
            <img src="${c.photo}" class="car-image" />
            <div>   <fmt:message key="car.name" /><span >  </span><a text="${car.name}"> </a></div>
            <div>   <fmt:message key="car.capacity" /><span >  </span><a text="${car.capacity}"> </a></div>
            <div>   <fmt:message key="car.carType" /><span >  </span><a text="${car.carType}"> </a></div>
            <div>
            <a class="btn btn-outline-primary" href="/cars/change-activity?car_id=${c.id}">
                    <c:if test="${car.active}">
                        <fmt:message key="deleteCar.button"/>
                    </c:if>
                    <c:if test="${!car.active}">
                        <fmt:message key="returnCar.button"/>
                    </c:if>
            </a>
                </c:forEach></div>
        </div>
    </div>
</div>
</body>
</html>
