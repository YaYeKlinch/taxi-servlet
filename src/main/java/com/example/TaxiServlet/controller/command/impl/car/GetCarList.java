package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;

public class GetCarList implements Command {
    private final CarService carService = new CarServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        List<Car> cars = carService.getAllCars();
        request.setAttribute("cars", cars);
        return "/cars.jsp";
    }
}
