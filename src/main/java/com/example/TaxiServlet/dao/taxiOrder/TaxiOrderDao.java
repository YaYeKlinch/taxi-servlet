package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.GenericDao;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.util.List;

public interface TaxiOrderDao extends GenericDao<TaxiOrder> {
    List<OrderCarUserDto> getOrderCarUserNotSortedList(int count, int size ,String filter);
    List<OrderCarUserDto>getOrderCarUserListByUser(long user_id);
    List<OrderCarUserDto> getOrderCarUserListSortedByDataDesc(int count, int size ,String filter);
    List<OrderCarUserDto> getOrderCarUserListSortedByDataAsc(int count, int size ,String filter);
    List<OrderCarUserDto> getOrderCarUserListSortedByCostsAsc(int count, int size ,String filter);
    List<OrderCarUserDto> getOrderCarUserListSortedByCostsDesc(int count, int size ,String filter);
}
