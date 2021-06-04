package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.entity.enums.CarType;

import javax.servlet.http.HttpServletRequest;

public class FindCarPage implements Command {
    private static final String URL = "/findCar.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("types" , CarType.values());
        return URL;
    }
}
