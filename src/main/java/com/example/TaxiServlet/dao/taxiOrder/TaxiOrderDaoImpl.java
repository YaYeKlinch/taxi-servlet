package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxiOrderDaoImpl extends JDBCDao<TaxiOrder> implements TaxiOrderDao {
private static final String GET_CARS_USERS_BY_ORDER = "SELECT taxi_order.time , taxi_order.arrival," +
        " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
        " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
        " taxi_order.user_id = user.id LIMIT ?,? ";

    private static final String GET_CARS_USERS_BY_ORDER_BY_USER_ID = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id WHERE user.id = ?";
    public TaxiOrderDaoImpl(Connection connection) {
        super(
                connection,
                "INSERT INTO taxi_order(departure, arrival, distance, costs, time, car_id , user_id) VALUES(?,?,?,?,?,?,?)",
                "SELECT * FROM taxi_order WHERE id = ?",
                "SELECT SQL_CALC_FOUND_ROWS * FROM taxi_order LIMIT ?,?",
                "SELECT * FROM taxi_order",
                "SELECT COUNT(*)FROM taxi_order",
                "COUNT(*)",
                "UPDATE taxi_order SET departure = ?,  arrival = ?, distance = ?, costs = ?, time = ?,  car_id = ?,  user_id=? WHERE id = ?",
                8,
                "DELETE FROM taxi_order WHERE id = ?",
                new TaxiOrderMapper());
    }

    @Override
    protected long getId(TaxiOrder entity) {
        return entity.getId();
    }

    @Override
    protected void setId(TaxiOrder entity, long Id) throws SQLException {
        entity.setId(Id);
    }

    @Override
    protected void setEntityValues(PreparedStatement statement, TaxiOrder entity) throws SQLException {
        statement.setString(1, entity.getDeparture());
        statement.setString(2, entity.getArrival());
        statement.setDouble(3, entity.getDistance());
        statement.setLong(4, entity.getCosts());
        statement.setTimestamp(5, Timestamp.valueOf(entity.getTime()));
        statement.setLong(6, entity.getCarId());
        statement.setLong(7, entity.getUserId());
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserList(int count, int size) {
        Mapper<OrderCarUserDto> orderCarUserDtoMapper = new OrderCarUserDtoMapper();
        List<OrderCarUserDto> orderCarUserDtoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CARS_USERS_BY_ORDER)) {
            statement.setInt(1, count);
            statement.setInt(2, size);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderCarUserDtoList.add(orderCarUserDtoMapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderCarUserDtoList;
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserListByUser(long user_id) {
        Mapper<OrderCarUserDto> orderCarUserDtoMapper = new OrderCarUserDtoMapper();
        List<OrderCarUserDto> orderCarUserDtoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CARS_USERS_BY_ORDER_BY_USER_ID)) {
            statement.setLong(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderCarUserDtoList.add(orderCarUserDtoMapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderCarUserDtoList;
    }
}
