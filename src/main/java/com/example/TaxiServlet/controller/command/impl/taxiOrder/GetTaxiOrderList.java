package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.impl.uttils.PaginationUtils;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetTaxiOrderList implements Command {
    TaxiOrderService taxiOrderService = new TaxiOrderServiceImpl();
    private final PaginationUtils pagination = new PaginationUtils();

    @Override
    public String execute(HttpServletRequest request) {
        long numberOfOrders = taxiOrderService.getNumberOfOrders();
        pagination.setAttributes(request , numberOfOrders);
        if(request.getParameter("sort")==null){
            request.setAttribute("orders",taxiOrderService.getAllTaxiOrders(pagination.getPage(),pagination.getRecordsPerPage()));
            return "orders.jsp";
        }
        if("costs".equals(request.getParameter("nameBy"))){
            request.setAttribute("orders", taxiOrderService.getAllTaxiOrderSortedByCosts(pagination.getPage(),pagination.getRecordsPerPage(),request.getParameter("sort")));
            return "orders.jsp";
        }
        request.setAttribute("orders", taxiOrderService.getAllTaxiOrderSortedByTime(pagination.getPage(),pagination.getRecordsPerPage(),request.getParameter("sort")));
        return "orders.jsp";
    }
}
