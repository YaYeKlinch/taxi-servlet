package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderCarUserDtoMapper implements Mapper<OrderCarUserDto> {
    @Override
    public OrderCarUserDto extractFromResultSet(ResultSet rs) throws SQLException {
        OrderCarUserDto orderCarUserDto = new OrderCarUserDto();
        orderCarUserDto.setCarName(rs.getString("car.name"));
        orderCarUserDto.setArrival(rs.getString("taxi_order.arrival"));
        orderCarUserDto.setDeparture(rs.getString("taxi_order.departure"));
        orderCarUserDto.setCosts(rs.getLong("taxi_order.costs"));
        orderCarUserDto.setTime(rs.getTimestamp("taxi_order.time").toLocalDateTime());
        orderCarUserDto.setUsername(rs.getString("user.username"));
        return orderCarUserDto;
    }
}
