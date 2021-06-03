package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetUpdateCarPage implements Command {
    CarService carService = new CarServiceImpl();
    private static final String URL = "/updateCar.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        long carId = Long.parseLong(request.getParameter("car_id"));
        Car car= carService.getCarById(carId).get();
        request.setAttribute("car", car);
        request.setAttribute("types" , CarType.values());
        return URL;
    }
}
