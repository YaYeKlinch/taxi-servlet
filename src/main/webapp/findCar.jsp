<%@include file="fragments/locale.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/grid.css"/>
    <title>Title</title>
    <c:set var="carName">
        <fmt:message  key="car.name"/>
    </c:set>
    <fmt:message var="carCapacity" key="car.capacity"/>
    <fmt:message var="carType" key="car.carType"/>
    <fmt:message var="makeOrder" key="makeOrder.button"/>
</head>
<body>
<div class="container">
    <%@include file="fragments/navbar.jsp"%>
<form>
    <select name="carType" id="dropOperator">
        <c:forEach var="item" items="${types}">
            <option>${item}</option>
        </c:forEach>
    </select>
    <input type="number" id = "capacity" class="form-control" name="capacity">
    <button id="find-car-btn" class="btn" type="button"><fmt:message key="submit.button"/></button>
</form>
    <div class="message" id="msg">
        <span><fmt:message key="text.alternate"/></span>
    </div>
    <div class="grid-container" id="grid-car-container">

    </div>
    <script src="/webjars/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
</div>
</body>
</html>

<script >
    $(document).ready(function (){
        $("#find-car-btn").click(()=>{
            let carValues={};
            carValues.capacity= $("#capacity").val()
            carValues.carType= $("#dropOperator").val()
            $.ajax({
                type: 'GET',
                url: "/taxi/found-cars",
                contentType: 'application/json',
                dataType: 'json',
                data: carValues,
                success: function (data) {
                    console.log(data)
                    addCar(data);
                },
                error: function (data) {
                    console.log("error")
                    console.log(data);
                    //    todo: implement
                }
            });
        })
    });

    function addCar(dto) {
        let cars = dto.cars;
        if(dto.alternate){
            $("#msg").show();
        }
        else{
            $("#msg").hide();
        }
        const container = $("#grid-car-container")[0];
        container.innerHTML = "";
        cars.forEach((car)=> {
            console.log(car.name);
            console.log(car.photo);

            let carDiv = document.createElement("div");
            let carDivClasses = carDiv.classList;
            carDivClasses.add("grid-item");
            carDivClasses.add("car-item");

            let photo = document.createElement("img");
            photo.className= "car-image";
            photo.setAttribute("src" , car.photo);

            let nameDiv = document.createElement("div");
            let nameText = document.createElement("label");
            let nameWhiteSpace = document.createElement("span");
            nameWhiteSpace.innerText = " "
            nameText.innerText = "${carName}";
            let name = document.createElement("span");
            name.innerText= car.name;

            let typeDiv = document.createElement("div");
            let typeText = document.createElement("label");
            let typeWhiteSpace = document.createElement("span");
            typeWhiteSpace.innerText = " "
            typeText.innerText = "${carType}";
            let carType = document.createElement("span");
            carType.innerText= car.carType;

            let capacityDiv = document.createElement("div");
            let capacityText = document.createElement("label");
            let capacityWhiteSpace = document.createElement("span");
            capacityWhiteSpace.innerText = " "
            capacityText.innerText = "${carCapacity}";
            let capacity = document.createElement("span");
            capacity.innerText= car.capacity;

            let makeOrderLink = document.createElement("a");
            let makeOrderClasses = makeOrderLink.classList;
            makeOrderClasses.add("btn");
            makeOrderClasses.add("btn-outline-primary");
            makeOrderLink.setAttribute("href", "/taxi/active-cars/make-order?car_id="+car.id);
            makeOrderLink.innerText= "${makeOrder}";

            carDiv.appendChild(photo);

            nameDiv.appendChild(nameText);
            nameDiv.appendChild(nameWhiteSpace)
            nameDiv.appendChild(name);

            carDiv.appendChild(nameDiv);

            typeDiv.appendChild(typeText);
            typeDiv.appendChild(typeWhiteSpace)
            typeDiv.appendChild(carType);

            carDiv.appendChild(typeDiv);

            capacityDiv.appendChild(capacityText);
            capacityDiv.appendChild(capacityWhiteSpace)
            capacityDiv.appendChild(capacity);
            carDiv.appendChild(capacityDiv);

            carDiv.appendChild(makeOrderLink);
            container.appendChild(carDiv);
        })

    }
</script>