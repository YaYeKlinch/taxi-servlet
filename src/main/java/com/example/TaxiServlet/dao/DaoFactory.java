package com.example.TaxiServlet.dao;


import com.example.TaxiServlet.dao.car.CarDao;
import com.example.TaxiServlet.dao.taxiOrder.TaxiOrderDao;
import com.example.TaxiServlet.dao.user.UserDao;
import com.example.TaxiServlet.dao.user.UserDaoImpl;
import com.example.TaxiServlet.entity.User;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;


    public abstract UserDao createUserDao();
    public abstract CarDao createCarDao();
    public abstract TaxiOrderDao createTaxiOrderDao();
    public static synchronized DaoFactory getInstance(){
        if( daoFactory == null){
            synchronized (DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}