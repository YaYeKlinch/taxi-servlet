package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetChangeCarActivity implements Command {
    private static final String URL = "/taxi/cars";
    private final CarService carService = new CarServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        long carId = Long.parseLong(request.getParameter("car_id"));
        Car car= carService.getCarById(carId).get();
        carService.changeCarActivity(car);
        return URL;
    }
}
