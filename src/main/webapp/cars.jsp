<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css"/>
    <title>Title</title>
</head>
<body>
<div class="container">
    <%@include file="fragments/navbar.jsp"%>
    <a class="btn btn-outline-primary" href="/taxi/add-car"><fmt:message key="addCar.button"/></a>
    <div class="grid-container">
        <div class="grid-item car-item">
        <c:forEach items="${cars}" var="car" >
            <img src="${car.photo}" class="car-image" />
            <div>   <fmt:message key="car.name" /><span >  </span><span>${car.name} </span></div>
            <div>   <fmt:message key="car.capacity" /><span >  </span><span > ${car.capacity}</span></div>
            <div>   <fmt:message key="car.carType" /><span >  </span><span>${car.carType} </span></div>
            <div>
            <a class="btn btn-outline-primary" href="/taxi/cars/change-activity?car_id=${car.id}">
                    <c:if test="${car.active}">
                        <fmt:message key="deleteCar.button"/>
                    </c:if>
                    <c:if test="${!car.active}">
                        <fmt:message key="returnCar.button"/>
                    </c:if>
            </a>
                <a class="btn btn-outline-primary" href="/taxi/cars/update-car?car_id=${car.id}"><fmt:message key="update.button"/></a>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
