package com.example.TaxiServlet.service.car;

import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {
   List<Car> getAllCars();
   boolean createCar(CarDto carDto);
   Optional<Car> getCarById(Long id);
   boolean changeCarActivity(Car car);
   List<Car> getAllActiveCars();
}
