package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetChangeCarStatus implements Command {
    private static final String URL = "/active-cars";
    CarService carService = new CarServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("statuses" , CarStatus.values());
        long carId = Long.parseLong(request.getParameter("car_id"));
        Car car= carService.getCarById(carId).get();
        carService.changeCarStatus( CarStatus.valueOf(request.getParameter("status")), car);
        return URL;
    }
}
