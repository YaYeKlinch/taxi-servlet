package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.RestCommand;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.FoundCarDto;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.exceptions.JsonParseException;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RestFindCar implements RestCommand {
    CarService carService = new CarServiceImpl();
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String execute(HttpServletRequest request)  {
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        CarType carType = CarType.valueOf(request.getParameter("carType"));
        List<Car> cars = carService.findCarsByCapacityAndType(capacity,carType);
        boolean isAlternate = false;
        if(cars.isEmpty()){
            cars = carService.findCarsByCapacity(capacity);
            isAlternate = true;
        }
        FoundCarDto foundCarDto = new FoundCarDto(cars , isAlternate );
        try {
            return objectMapper.writeValueAsString(foundCarDto);
        }catch (JsonProcessingException ex){
            throw new JsonParseException("Cant parse object");
        }
    }

}
