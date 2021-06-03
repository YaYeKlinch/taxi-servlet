package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.impl.uttils.PaginationUtils;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetStatisticsPage implements Command {
    TaxiOrderService taxiOrderService = new TaxiOrderServiceImpl();
    private final PaginationUtils pagination = new PaginationUtils();
    @Override
    public String execute(HttpServletRequest request) {
        long numberOfCars = taxiOrderService.getCarsCountInOrder();
        pagination.setAttributes(request ,numberOfCars);
        request.setAttribute("cars", taxiOrderService.getStatistics(pagination.getPage(),pagination.getRecordsPerPage()));
        return "/statistics.jsp";
    }
}
