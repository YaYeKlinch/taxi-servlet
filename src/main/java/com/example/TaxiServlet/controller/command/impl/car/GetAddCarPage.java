package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.enums.CarType;

import javax.servlet.http.HttpServletRequest;

public class GetAddCarPage implements Command {
    private static final String URL = "/addCar.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("types" , CarType.values());
        return URL;
    }
}
