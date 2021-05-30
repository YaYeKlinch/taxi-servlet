package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetActiveCarList implements Command {
    private final CarService carService = new CarServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        List<Car> cars = carService.getAllActiveCars();
        request.setAttribute("cars", cars);
        request.setAttribute("statuses" , CarStatus.values());
        return "activeCars.jsp";
    }
}
