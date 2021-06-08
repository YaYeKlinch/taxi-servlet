package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.TaxiOrder;
import com.example.TaxiServlet.entity.dto.OrderCarUserDto;
import com.example.TaxiServlet.entity.dto.StatisticDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaxiOrderDaoImpl extends JDBCDao<TaxiOrder> implements TaxiOrderDao {
private static final String GET_CARS_USERS_BY_ORDER = "SELECT taxi_order.time , taxi_order.arrival," +
        " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
        " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
        " taxi_order.user_id = user.id WHERE  user.username LIKE ?  ESCAPE '!' LIMIT ?,? ";
    private static final String GET_CARS_USERS_BY_ORDER_SORTED_BY_TIME_DESC = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id " +
            "WHERE user.username LIKE ? ORDER BY taxi_order.time DESC LIMIT ?,? ";

    private static final String GET_CARS_USERS_BY_ORDER_SORTED_BY_TIME_ASC = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id " +
            "WHERE user.username LIKE ? ORDER BY taxi_order.time ASC LIMIT ?,? ";

    private static final String GET_CARS_USERS_BY_ORDER_SORTED_BY_COSTS_ASC = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id " +
            "WHERE user.username LIKE ? ORDER BY taxi_order.costs ASC LIMIT ?,? ";

    private static final String GET_CARS_USERS_BY_ORDER_SORTED_BY_COSTS_DESC = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id " +
            "WHERE  user.username LIKE ? ORDER BY taxi_order.costs DESC LIMIT ?,? ";

    private static final String GET_CARS_USERS_BY_ORDER_BY_USER_ID = "SELECT taxi_order.time , taxi_order.arrival," +
            " taxi_order.departure, car.name , user.username, taxi_order.costs  from " +
            " (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) LEFT JOIN user ON" +
            " taxi_order.user_id = user.id WHERE user.id = ? LIMIT ?,?";

    private static final String GET_COUNT_ORDERS_BY_USER = "SELECT COUNT(*)FROM taxi_order" +
            " WHERE taxi_order.user_id = ?";

    private static final String GET_DISTANCE_AND_COSTS_SUM_OF_CARS = "SELECT  car.name " +
            ", SUM(taxi_order.distance) , SUM(taxi_order.costs)" +
            "  from (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id) " +
            " GROUP BY car.name limit ?,?";

    private static final String GET_CAR_COUNT_IN_ORDER = "SELECT  COUNT(*) from (select car.name " +
            " FROM (car LEFT JOIN  taxi_order ON car.id = taxi_order.car_id)  GROUP BY car.name ) distinct_cars";
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
    public List<OrderCarUserDto> getOrderCarUserListByUser(long user_id , int count , int size) {
        Mapper<OrderCarUserDto> orderCarUserDtoMapper = new OrderCarUserDtoMapper();
        List<OrderCarUserDto> orderCarUserDtoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_CARS_USERS_BY_ORDER_BY_USER_ID)) {
            statement.setLong(1, user_id);
            statement.setInt(2, count);
            statement.setInt(3, size);
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
    public List<OrderCarUserDto> getOrderCarUserNotSortedList(int count, int size ,String filter) {
        filter = filter.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
        return getOrderCarUsersDtoList(count,size,"%"+filter+"%",GET_CARS_USERS_BY_ORDER);
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserListSortedByDataDesc(int count, int size ,String filter) {
        return getOrderCarUsersDtoList(count,size,"%"+filter+"%", GET_CARS_USERS_BY_ORDER_SORTED_BY_TIME_DESC);
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserListSortedByDataAsc(int count, int size ,String filter) {
        return getOrderCarUsersDtoList(count,size,"%"+filter+"%", GET_CARS_USERS_BY_ORDER_SORTED_BY_TIME_ASC);
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserListSortedByCostsAsc(int count, int size ,String filter) {
        return getOrderCarUsersDtoList(count,size,"%"+filter+"%", GET_CARS_USERS_BY_ORDER_SORTED_BY_COSTS_ASC);
    }

    @Override
    public List<OrderCarUserDto> getOrderCarUserListSortedByCostsDesc(int count, int size ,String filter) {
        return getOrderCarUsersDtoList(count,size,"%"+filter+"%", GET_CARS_USERS_BY_ORDER_SORTED_BY_COSTS_DESC);
    }

    @Override
    public long getOrdersCountByUser(long userId) {
       long ordersCountByUser = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_COUNT_ORDERS_BY_USER)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                ordersCountByUser = rs.getInt("COUNT(*)");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  ordersCountByUser;
    }

    @Override
    public List<StatisticDto> getTotalCostsAndDistance(int count, int size) {
        Mapper<StatisticDto> statisticDtoMapper = new StatisticDtoMapper();
        List<StatisticDto> statisticDtoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_DISTANCE_AND_COSTS_SUM_OF_CARS)) {
            statement.setInt(1, count);
            statement.setInt(2, size);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                statisticDtoList.add(statisticDtoMapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return statisticDtoList;
    }

    @Override
    public long getCarCountInOrders() {
        long carsCount = 0;
        try (PreparedStatement statement = connection.prepareStatement(GET_CAR_COUNT_IN_ORDER)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                carsCount = rs.getInt("COUNT(*)");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  carsCount;
    }


    private List<OrderCarUserDto> getOrderCarUsersDtoList(int count, int size ,String filter, String query){
        Mapper<OrderCarUserDto> orderCarUserDtoMapper = new OrderCarUserDtoMapper();
        List<OrderCarUserDto> orderCarUserDtoList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, filter);
            statement.setInt(2, count);
            statement.setInt(3, size);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderCarUserDtoList.add(orderCarUserDtoMapper.extractFromResultSet(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return orderCarUserDtoList;
    };
}
