<%@include file="fragments/locale.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>JSP - Hello World</title>
</head>
<body>
<div class="container">
<%@include file="fragments/navbar.jsp"%>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</div>
</body>
</html>