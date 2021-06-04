package com.example.TaxiServlet.dao.car;

import com.example.TaxiServlet.dao.GenericDao;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.listener.Listener;

import java.util.List;

public interface CarDao extends GenericDao<Car> {
    List<Car> getActiveCars();
    List<Car> getCarsByCapacityAndType(CarType carType , int capacity);
    List<Car> getCarsByCapacity(int capacity);
}
