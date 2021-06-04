<%@include file="fragments/locale.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="/css/grid.css"/>

    <title>Title</title>
</head>
<body>
<div class="container">
    <%@include file="fragments/navbar.jsp" %>
    <div class="grid-container">
        <div class="grid-item car-item">
            <c:forEach items="${cars}" var="car">
                <img src="${car.photo}" class="car-image"/>
                <div><fmt:message key="car.name"/><span>  </span><span>${car.name} </span></div>
                <div><fmt:message key="car.capacity"/><span>  </span><span> ${car.capacity}</span></div>
                <div><fmt:message key="car.carType"/><span>  </span><span>${car.carType} </span></div>
                             <c:if test="${sessionScope.LoggedUser.role.name()=='USER'}">
                                  <div><fmt:message key="car.carStatus"/><span>  </span><span>${car.carStatus} </span></div>
                          </c:if>
                <c:if test="${sessionScope.LoggedUser.role.name()=='ADMIN'}">
                 <div><fmt:message key="car.carStatus"/>
                    <form action="/taxi/active-cars/change-status">
                        <input type="hidden" name="car_id" value="${car.id}">
                        <label>
                            <select name="status">
                                <c:forEach var="item" items="${statuses}">
                                    <option <c:if test="${car.carStatus.name()==item}">selected</c:if>>${item}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-primary"><fmt:message key="submit.button"/></button>
                        </div>
                    </form>
                </div>
                </c:if>
                <c:if test="${car.carStatus.name()=='READY'}">
                <a class="btn btn-outline-primary" href="/taxi/active-cars/make-order?car_id=${car.id}"><fmt:message key="makeOrder.button"/></a>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
