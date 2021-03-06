package com.example.TaxiServlet.service.car;

import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;

import java.util.List;
import java.util.Optional;

public interface CarService {
   List<Car> getAllCars();
   boolean createCar(CarDto carDto);
   Optional<Car> getCarById(Long id);
   boolean changeCarActivity(Car car);
   List<Car> getAllActiveCars();
   boolean changeCarStatus(CarStatus carStatus, Car car);
   boolean updateCar(CarDto carDto , Car car);
   List<Car> findCarsByCapacityAndType(int capacity, CarType carType);
   List<Car> findCarsByCapacity(int capacity);
}
