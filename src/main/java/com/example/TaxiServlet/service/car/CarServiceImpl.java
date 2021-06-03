package com.example.TaxiServlet.service.car;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.car.CarDao;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.enums.CarStatus;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl implements CarService{
    private final DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public List<Car> getAllCars() {
        try ( CarDao carDao = daoFactory.createCarDao()){
            return carDao.findAll();
        }
    }

    @Override
    public boolean createCar(CarDto carDto) {
        try (CarDao carDao = daoFactory.createCarDao()) {
            Car car = new Car();
            car.setName(carDto.getName());
            car.setPhoto(carDto.getPhoto());
            car.setCarType(carDto.getCarType());
            car.setCarStatus(CarStatus.INACTIVE);
            car.setActive(true);
            car.setCapacity(carDto.getCapacity());
            return carDao.create(car);
        }
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        try (CarDao carDao = daoFactory.createCarDao()) {
            return carDao.findById(id);
        }
    }

    @Override
    public void changeCarActivity(Car car) {
        try (CarDao carDao = daoFactory.createCarDao()) {
            car.setActive(!car.isActive());
            carDao.update(car);
        }

    }

    @Override
    public List<Car> getAllActiveCars() {
        try ( CarDao carDao = daoFactory.createCarDao()){
            return carDao.getActiveCars();
        }
    }

    @Override
    public void changeCarStatus(CarStatus carStatus , Car car) {
        try (CarDao carDao = daoFactory.createCarDao()) {
            car.setCarStatus(carStatus);
            carDao.update(car);
        }
    }

    @Override
    public boolean updateCar(CarDto carDto, Car car) {
        try (CarDao carDao = daoFactory.createCarDao()){
            car.setCarType(carDto.getCarType());
            car.setCapacity(carDto.getCapacity());
            car.setPhoto(carDto.getPhoto());
            car.setName(carDto.getName());
            return carDao.update(car);
        }
    }
}
