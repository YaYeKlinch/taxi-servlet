package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetMakeOrderPage implements Command {

    private static final String URL = "/makeOrder.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        return URL;
    }
}
