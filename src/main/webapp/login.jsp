<%@include file="fragments/locale.jsp"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

    <title>Title</title>
</head>
<body>
<form method="POST" >
    <c:if test="${!empty requestScope.UserIsBanned}">
        <div class="alert-danger error-message" >
            <fmt:message key="user.banned" />
        </div>
    </c:if>
    <c:if test="${!empty requestScope.UserEx}">
        <div class="alert-danger error-message" >
            <fmt:message key="user.error" />
        </div>
    </c:if>
    <div class="form-group">
        <input type="text" name="email" class="form-control" placeholder="<fmt:message key="text.email" />" value="" />
    </div>
    <div class="form-group">
        <input type="password" name="password" class="form-control" placeholder="<fmt:message key="text.password" />" value="" />
    </div>
    <div class="form-group">
        <input type="submit" class="form-control"  value="<fmt:message key="login.button" />" />
    </div>
    <p class="text-center"><fmt:message key="login.registrationInvitation"/>
        <a href="/registration"><fmt:message key="login.registrationLink"/></a></p>
</form>
</body>
</html>