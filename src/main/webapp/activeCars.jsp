<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

  <title>Title</title>
</head>
<body>
<div class="container">
  <%@include file="fragments/navbar.jsp"%>
  <div class="grid-container">
    <div class="grid-item car-item">
      <c:forEach items="${cars}" var="car" >
      <img src="${car.photo}" class="car-image" />
      <div>   <fmt:message key="car.name" /><span >  </span><span>${car.name} </span></div>
      <div>   <fmt:message key="car.capacity" /><span >  </span><span > ${car.capacity}</span></div>
      <div>   <fmt:message key="car.carType" /><span >  </span><span>${car.carType} </span></div>
        <c:if test="${sessionScope.LoggedUser.role.name()==USER}">
        <div>   <fmt:message key="car.carType" /><span >  </span><span>${car.carStatus} </span></div>
        </c:if>
        </c:forEach>
    </div>
  </div>
</div>
</body>
</html>