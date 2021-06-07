package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.impl.uttils.PaginationUtils;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class GetTaxiOrderList implements Command {
    public static final String FILTER = "filter";
    public static final String SAVE_FILTER = "prevFilter";
    private static final String URL =  "/orders.jsp";
    TaxiOrderService taxiOrderService = new TaxiOrderServiceImpl();
    private final PaginationUtils pagination = new PaginationUtils();
    private static final String DEFAULT_FILTER = "";

    @Override
    public String execute(HttpServletRequest request) {
        String filter = DEFAULT_FILTER;
        if(request.getParameter(FILTER)!=null){
            filter = request.getParameter(FILTER);
            request.setAttribute(SAVE_FILTER , filter);
        }
        long numberOfOrders = taxiOrderService.getNumberOfOrders();
        pagination.setAttributes(request , numberOfOrders);
        if(request.getParameter("sort")==null){
            request.setAttribute("orders",taxiOrderService.getAllTaxiOrders(pagination.getPage(),pagination.getRecordsPerPage(),filter));
            return URL;
        }
        if("costs".equals(request.getParameter("nameBy"))){
            request.setAttribute("orders", taxiOrderService.getAllTaxiOrderSortedByCosts(pagination.getPage(),pagination.getRecordsPerPage(),request.getParameter("sort"),filter));
            return URL;
        }
        request.setAttribute("orders", taxiOrderService.getAllTaxiOrderSortedByTime(pagination.getPage(),pagination.getRecordsPerPage(),request.getParameter("sort"),filter));
        return URL;
    }
}
