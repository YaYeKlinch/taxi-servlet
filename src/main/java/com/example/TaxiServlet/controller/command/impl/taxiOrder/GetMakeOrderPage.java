package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetMakeOrderPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/makeOrder.jsp";
    }
}
