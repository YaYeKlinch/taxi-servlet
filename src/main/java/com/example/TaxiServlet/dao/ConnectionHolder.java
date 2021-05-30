package com.example.TaxiServlet.dao;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class ConnectionHolder {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/taxi_servlet?useUnicode=true&serverTimezone=UTC&useSSL=false");
                    ds.setUsername("klisa");
                    ds.setPassword("62418asdfa");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}