package com.example.TaxiServlet.dao;

import com.example.TaxiServlet.dao.car.CarDao;
import com.example.TaxiServlet.dao.car.CarDaoImpl;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDao;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDaoImpl;
import com.example.TaxiServlet.dao.user.UserDao;
import com.example.TaxiServlet.dao.user.UserDaoImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionHolder.getDataSource();


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public CarDao createCarDao() {
        return new CarDaoImpl(getConnection());
    }

    @Override
    public TaxiOrderDao createTaxiOrderDao() {
        return new TaxiOrderDaoImpl(getConnection());
    }
}
