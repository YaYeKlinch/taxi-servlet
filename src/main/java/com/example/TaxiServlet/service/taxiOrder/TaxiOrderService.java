package com.example.TaxiServlet.service.taxiOrder;

import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.util.List;

public interface TaxiOrderService {
    boolean createTaxiOrder(TaxiOrderDto taxiOrderDto, long userId , long carId);
    List<OrderCarUserDto> getAllTaxiOrders(int count, int size);
    List<OrderCarUserDto> getAllTaxiOrdersByUser(long user_id);
    long getNumberOfOrders();
}
