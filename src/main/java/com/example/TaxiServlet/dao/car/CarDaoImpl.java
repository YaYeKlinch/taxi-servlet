package com.example.TaxiServlet.dao.car;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDaoImpl extends JDBCDao<Car> implements CarDao {

    public CarDaoImpl(Connection connection) {
        super(
                connection,
                "INSERT INTO car(id,name, photo, carStatus, carType, active,capacity) VALUES(DEFAULT,?,?,?,?,?,?)",
                "SELECT * FROM car WHERE id = ?",
                "SELECT SQL_CALC_FOUND_ROWS * FROM car LIMIT ?,?",
                "SELECT * FROM car",
                "SELECT COUNT(*)FROM car",
                "COUNT(*)",
                "UPDATE car SET name = ?, photo = ?, carStatus = ?, carType = ?, active = ?, capacity=? WHERE id = ?",
                7,
                "DELETE FROM car WHERE id = ?",
                new CarMapper());
    }

    @Override
    protected long getId(Car entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Car entity, long Id) throws SQLException {
        entity.setId(Id);
    }

    @Override
    protected void setEntityValues(PreparedStatement statement, Car entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getPhoto());
        statement.setString(3, entity.getCarStatus().name());
        statement.setString(4, entity.getCarType().name());
        statement.setBoolean(5, entity.isActive());
        statement.setInt(6, entity.getCapacity());
    }
}
