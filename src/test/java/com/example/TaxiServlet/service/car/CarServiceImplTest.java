package com.example.TaxiServlet.service.car;

import com.example.TaxiServlet.dao.DaoFactory;
import com.example.TaxiServlet.dao.car.CarDao;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.enums.CarStatus;
import com.example.TaxiServlet.entity.enums.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {
    @Mock
    DaoFactory factory;

    @Mock
    CarDao carDao;

    @Mock
    Car car;

    @Mock
    List<Car> allCars;



    @InjectMocks
    CarServiceImpl carService;

    private final static long ID  = 1;
    private final static int CAPACITY = 2;
    private final static String NAME = "name";
    private final static String PHOTO = "photo";


    @BeforeEach
    void init() {
        when(factory.createCarDao()).thenReturn(carDao);
    }

    @Test
    void shouldReturnAllCars(){
        when(carDao.findAll()).thenReturn(allCars);

        assertSame(allCars, carService.getAllCars());
    }

    @Test
    void shouldReturnAllActiveCars(){
        when(carDao.getActiveCars()).thenReturn(allCars);

        assertSame(allCars, carService.getAllActiveCars());
    }

    @Test
    void shouldReturnCarsByCapacity(){
        when(carDao.getCarsByCapacity(CAPACITY)).thenReturn(allCars);

        assertSame(allCars, carService.findCarsByCapacity(CAPACITY));
    }

    @Test
    void shouldReturnCarsByCapacityAndType(){
        when(carDao.getCarsByCapacityAndType(CarType.CABRIOLET, CAPACITY)).thenReturn(allCars);

        assertSame(allCars, carService.findCarsByCapacityAndType(CAPACITY, CarType.CABRIOLET));
    }

    @Test
    void shouldUpdateCar_WhenChangeActivity(){
        when(carDao.update(car)).thenReturn(true);
        assertTrue(carService.changeCarActivity(car));
    }

    @Test
    void shouldChangeActivity(){
        Car testCar = new Car();
        testCar.setActive(false);
        carService.changeCarActivity(testCar);
        assertTrue(testCar.isActive());
    }

    @Test
    void shouldUpdateCar_WhenChangeStatus(){
        when(carDao.update(car)).thenReturn(true);
        assertTrue(carService.changeCarStatus( CarStatus.IN_RACE , car ));
    }

    @Test
    void shouldChangeStatus(){
        Car testCar = new Car();
        testCar.setCarStatus(CarStatus.IN_RACE);
        carService.changeCarStatus(CarStatus.READY , testCar);
        assertEquals(CarStatus.READY , testCar.getCarStatus());
    }

    @Test
    void shouldReturnCarById_WhenCrExists(){
        when(carDao.findById(ID)).thenReturn(Optional.of(car));
        assertSame(car , carService.getCarById(ID).get());
    }

    @Test
    void shouldCreateCar(){
        CarDto toCreate = new CarDto();
        toCreate.setCarType(CarType.CABRIOLET);
        toCreate.setName(NAME);
        toCreate.setCapacity(CAPACITY);
        toCreate.setPhoto(PHOTO);
        Car carToCreate = new Car();
        carToCreate.setCarType(CarType.CABRIOLET);
        carToCreate.setName(NAME);
        carToCreate.setCapacity(CAPACITY);
        carToCreate.setPhoto(PHOTO);
        when(carDao.create(carToCreate)).thenReturn(true);
        assertTrue(carService.createCar(toCreate));
    }

    @Test
    void shouldUpdateCar(){
        CarDto toUpdate = new CarDto();
        toUpdate.setCarType(CarType.CABRIOLET);
        toUpdate.setName(NAME);
        toUpdate.setCapacity(CAPACITY);
        toUpdate.setPhoto(PHOTO);
        Car carToUpdate = new Car();
        carToUpdate.setCarType(CarType.CABRIOLET);
        carToUpdate.setName(NAME);
        carToUpdate.setCapacity(CAPACITY);
        carToUpdate.setPhoto(PHOTO);
        when(carDao.update(carToUpdate)).thenReturn(true);
        assertTrue(carService.updateCar(toUpdate,carToUpdate));
    }
}