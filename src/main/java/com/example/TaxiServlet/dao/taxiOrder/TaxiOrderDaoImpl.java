package com.example.TaxiServlet.dao.taxiOrder;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.dao.user.UserMapper;
import com.example.TaxiServlet.entity.TaxiOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TaxiOrderDaoImpl extends JDBCDao<TaxiOrder> implements TaxiOrderDao {

    public TaxiOrderDaoImpl(Connection connection) {
        super(
                connection,
                "INSERT INTO taxi_order(departure, arrival, distance, costs, time, car_id , user_id) VALUES(?,?,?,?,?,?)",
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
        statement.setLong(3, entity.getDistance());
        statement.setLong(4, entity.getCosts());
        statement.setTimestamp(5, Timestamp.valueOf(entity.getTime()));
        statement.setLong(6, entity.getCarId());
        statement.setLong(7, entity.getUserId());
    }
}
