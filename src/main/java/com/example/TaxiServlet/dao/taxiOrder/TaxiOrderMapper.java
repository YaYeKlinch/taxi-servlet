package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.TaxiOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxiOrderMapper implements Mapper<TaxiOrder> {
    @Override
    public TaxiOrder extractFromResultSet(ResultSet rs) throws SQLException {
        TaxiOrder taxiOrder = new TaxiOrder();
        taxiOrder.setId(rs.getLong("taxi_order.id"));
        taxiOrder.setArrival(rs.getString("taxi_order.arrival"));
        taxiOrder.setDeparture(rs.getString("taxi_order.departure"));
        taxiOrder.setCosts(rs.getLong("taxi_order.costs"));
        taxiOrder.setDistance(rs.getDouble("taxi_order.distance"));
        taxiOrder.setCarId(rs.getLong("taxi_order.car_id"));
        taxiOrder.setUserId(rs.getLong("taxi_order.user_id"));
        taxiOrder.setTime(rs.getTimestamp("taxi_order.time").toLocalDateTime());
        return taxiOrder;
    }
}
