package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.GenericDao;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import java.util.List;

public interface TaxiOrderDao extends GenericDao<TaxiOrder> {
    List<OrderCarUserDto> getOrderCarUserList(int count, int size);
    List<OrderCarUserDto>getOrderCarUserListByUser(long user_id);
}
