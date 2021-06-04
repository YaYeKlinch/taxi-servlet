package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.impl.uttils.PaginationUtils;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.TaxiServlet.controller.command.impl.uttils.SessionUtils.getUserId;

public class GetUsersTaxiOrderList implements Command {
    TaxiOrderService taxiOrderService = new TaxiOrderServiceImpl();
    private final PaginationUtils pagination = new PaginationUtils();
    @Override
    public String execute(HttpServletRequest request) {
        User user = getUserId(request);
        long numberOfOrders = taxiOrderService.getNumberOfOrdersByUser(user.getId());
        pagination.setAttributes(request , numberOfOrders);
        List<OrderCarUserDto> taxiOrders = taxiOrderService.getAllTaxiOrdersByUser(user.getId(),pagination.getPage(),pagination.getRecordsPerPage());
        request.setAttribute("orders", taxiOrders);
        return "/usersOrders.jsp";
    }
}
