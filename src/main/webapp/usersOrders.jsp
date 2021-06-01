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
  <table class="table">
    <tr>
      <th >  <fmt:message key="departure.order" /></th>
      <th >  <fmt:message key="arrival.order" /></th>
      <th>  <fmt:message key="time.order" /></th>
      <th>  <fmt:message key="order.costs" /></th>
      <th>  <fmt:message key="order.car" /></th>
    </tr>
    <c:forEach items="${orders}" var="o" >
      <tr>
        <td>${o.departure}</td>
        <td>${o.arrival}</td>
        <td>${o.time}</td>
        <td>${o.costs}</td>
        <td>${o.carName}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
