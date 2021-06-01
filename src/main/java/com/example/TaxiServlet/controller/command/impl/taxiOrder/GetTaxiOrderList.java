package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.impl.uttils.PaginationUtils;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetTaxiOrderList implements Command {
    TaxiOrderService taxiOrderService = new TaxiOrderServiceImpl();
    private final PaginationUtils pagination = new PaginationUtils();

    @Override
    public String execute(HttpServletRequest request) {
        long numberOfOrders = taxiOrderService.getNumberOfOrders();
        pagination.setAttributes(request , numberOfOrders);
        List<OrderCarUserDto> taxiOrders = taxiOrderService.getAllTaxiOrders(pagination.getPage(),pagination.getRecordsPerPage());
        request.setAttribute("orders", taxiOrders);
        return "orders.jsp";
    }
}
