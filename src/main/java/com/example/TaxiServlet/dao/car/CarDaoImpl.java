package com.example.TaxiServlet.dao.car;

import com.example.TaxiServlet.dao.JDBCDao;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.enums.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CarDaoImpl extends JDBCDao<Car> implements CarDao {
    private static final String ALL_CARS_BY_ACTIVE = "SELECT * FROM car WHERE active=true";

    private static final String CARS_BY_CAPACITY_AND_TYPE = "SELECT * FROM car WHERE capacity = ? and " +
            " carType = ?";

    private static  final String CARS_BY_CAPACITY = "SELECT * FROM car WHERE capacity>=?";

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

    @Override
    public List<Car> getActiveCars() {
        List<Car> cars = null;
        try (PreparedStatement statement = connection.prepareStatement(ALL_CARS_BY_ACTIVE)) {
            cars = getAllFromStatement(statement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByCapacityAndType(CarType carType, int capacity) {
        List<Car> cars = null;
        try (PreparedStatement statement = connection.prepareStatement(CARS_BY_CAPACITY_AND_TYPE)) {
            statement.setInt(1, capacity);
            statement.setString(2, carType.name());
            cars = getAllFromStatement(statement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByCapacity(int capacity) {
        List<Car> cars = null;
        try (PreparedStatement statement = connection.prepareStatement(CARS_BY_CAPACITY)) {
            statement.setInt(1, capacity);
            cars = getAllFromStatement(statement);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cars;
    }
}
