package com.example.TaxiServlet.dao.car;

import com.example.TaxiServlet.dao.Mapper;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper  implements Mapper<Car> {

    @Override
    public Car extractFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong("car.id"));
        car.setName(rs.getString("car.name"));
        car.setPhoto(rs.getString("car.photo"));
        car.setCarStatus(CarStatus.valueOf(rs.getString("car.carStatus")));
        car.setCarType(CarType.valueOf(rs.getString("car.carType")));
        car.setActive(rs.getBoolean("car.active"));
        car.setCapacity(rs.getInt("car.capacity"));
        return car;
    }
}
