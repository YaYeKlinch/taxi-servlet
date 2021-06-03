package com.example.TaxiServlet.service.taxiOrder;

import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.util.List;

public interface TaxiOrderService {
    boolean createTaxiOrder(TaxiOrderDto taxiOrderDto, long userId , long carId);
    List<OrderCarUserDto> getAllTaxiOrders(int count, int size ,String filter);
    List<OrderCarUserDto> getAllTaxiOrdersByUser(long user_id , int count , int size);
    long getNumberOfOrders();
    List<OrderCarUserDto> getAllTaxiOrderSortedByTime(int count, int size,String sort ,String filter);
    List<OrderCarUserDto> getAllTaxiOrderSortedByCosts(int count, int size,String sort ,String filter);
    long getNumberOfOrdersByUser(long userId);
}
