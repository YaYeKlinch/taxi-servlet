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
  <select name="carType">
    <c:forEach var="item" items="${types}">
      <option>${item}</option>
    </c:forEach>
  </select>
  <div class="form-group">
    <label><fmt:message key="car.photo"/></label>
    <c:if test="${!empty requestScope.photoEmpty}">
      <div class="alert-danger error-message" >
        <fmt:message key="field.empty" />
      </div>
    </c:if>
    <input type="text" class="form-control" name="photo"  value="${values.photo}"/>
  </div>
    <label><fmt:message key="car.name"/></label>
    <c:if test="${!empty requestScope.nameEmpty}">
      <div class="alert-danger error-message" >
        <fmt:message key="field.empty" />
      </div>
    </c:if>
    <input type="text" class="form-control" name="name"  value="${values.name}"/>
  </div>
  <div class="form-group">
    <label><fmt:message key="car.capacity"/></label>
    <c:if test="${!empty requestScope.capacityIncorrect}">
      <div class="alert-danger error-message" >
        <fmt:message key="field.empty" />
      </div>
    </c:if>
    <c:if test="${!empty requestScope.capacityEmpty}">
      <div class="alert-danger error-message" >
        <fmt:message key="field.incorrect" />
      </div>
    </c:if>
    <input type="number" class="form-control" name="capacity"  value="${values.capacity}"/>
  </div>
  <div class="form-group">
    <button type="submit" class="btn btn-primary btn-block btn-lg"><fmt:message key="submit.button"/></button>
  </div>
</div>
</form>
</body>
</html>
