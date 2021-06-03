package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.impl.valdators.CarValidator;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class PostCarUpdate implements PostCommand {
    private static final String URL_ERROR = "/updateCar.jsp";
    private static final String URL_SUCCESS = "/cars";
    CarService carService = new CarServiceImpl();
    private boolean allMatches;

    @Override
    public String execute(HttpServletRequest request) {
        long carId = Long.parseLong(request.getParameter("car_id"));
        Car car= carService.getCarById(carId).get();
        CarDto carDto = getValidateDto(request);
        boolean updated = updateAndAddErrorAttributes(carDto, request,car);
        if (updated) {
            request.setAttribute("values", carDto);
            return URL_SUCCESS;
        }
        request.setAttribute("types" , CarType.values());
        return URL_ERROR;

    }

    private boolean updateAndAddErrorAttributes(CarDto carDto, HttpServletRequest request, Car car) {
        boolean updated = false;
        if (allMatches) {
            updated = tryUpdateOrAddUpdateErrorAttr(carDto, request,car);

        }
        return updated;
    }

    private boolean tryUpdateOrAddUpdateErrorAttr(CarDto carDto, HttpServletRequest request, Car car) {
        if (carService.updateCar(carDto, car)) {
            return true;
        } else {
            request.setAttribute("updateError", true);
            return false;
        }
    }
    private CarDto getValidateDto(HttpServletRequest request){
        CarDto car = null;
        try {
            car = new CarDto(
                    request.getParameter("name"),
                    request.getParameter("photo"),
                    CarType.valueOf(request.getParameter("carType")),
                    Integer.parseInt(request.getParameter("capacity"))
            );
            allMatches = CarValidator.validateCar(request, car);

        } catch (IllegalArgumentException  ex) {
            allMatches = false;
        }
        return car;
    }
    @Override
    public boolean isError(String url) {
        return url.equals(URL_ERROR);
    }
}
