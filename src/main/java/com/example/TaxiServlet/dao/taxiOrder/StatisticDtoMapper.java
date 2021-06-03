package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.dto.StatisticDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticDtoMapper implements Mapper<StatisticDto> {
    @Override
    public StatisticDto extractFromResultSet(ResultSet rs) throws SQLException {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setCarName(rs.getString("car.name"));
        statisticDto.setTotalDistance(rs.getDouble("SUM(taxi_order.distance)"));
        statisticDto.setTotalCosts(rs.getInt("SUM(taxi_order.costs)"));
        return statisticDto;
    }
}
