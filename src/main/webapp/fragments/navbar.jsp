<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav  class="navbar navbar-expand-lg ">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
            <a class="nav-link" href="/"><fmt:message key="home.page"/></a>
        </li>
        <c:if test="${sessionScope.LoggedUser!=null}">
        <li class="nav-item">
            <a class="nav-link" href="/active-cars"><fmt:message key="taxi.page"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/user-orders"><fmt:message key="myOrders.page"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/all-orders"><fmt:message key="allOrders.page"/></a>
        </li>
            <li class="nav-item">
                <a class="nav-link" href="/cars"><fmt:message key="allCars.text"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout"><fmt:message key="signOut.button"/></a>
            </li>
        <li class="nav-item">
            <a href="?language=en">EN</a>
            <a href="?language=ua">UA</a>
        </li>
        </c:if>
        <c:if test="${sessionScope.LoggedUser==null}">
        <li class="nav-item">
            <a class="nav-link" href="/login-page"><fmt:message key="login.button"/></a>
        </li>
        </c:if>
    </ul>
</nav>