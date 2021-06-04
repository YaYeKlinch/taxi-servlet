package com.example.TaxiServlet.controller.command.impl.car;

import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.impl.valdators.CarValidator;
import com.example.TaxiServlet.controller.command.impl.valdators.UserValidator;
import com.example.TaxiServlet.entity.Car;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.dto.UserDto;
import com.example.TaxiServlet.entity.enums.CarType;
import com.example.TaxiServlet.service.car.CarService;
import com.example.TaxiServlet.service.car.CarServiceImpl;
import com.example.TaxiServlet.service.user.EmailExistsException;
import com.example.TaxiServlet.service.user.UserService;
import com.example.TaxiServlet.service.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PostAddCar implements PostCommand {

    private final CarService carService = new CarServiceImpl();
    private boolean allMatches;
    private static final String URL_ERROR = "/addCar.jsp";
    private static final String URL_SUCCESS = "/taxi/cars";
    @Override
    public String execute(HttpServletRequest request) {
        allMatches = true;
        CarDto carDto = getValidateDto(request);
        boolean created = createAndAddErrorAttributes(carDto, request);
        if (created) {
            request.setAttribute("values", carDto);
            return URL_SUCCESS;
        }
        request.setAttribute("types" , CarType.values());
        return URL_ERROR;
    }

    private boolean createAndAddErrorAttributes(CarDto carDto, HttpServletRequest request) {
        boolean created = false;
        if (allMatches) {
            created = tryCreateOrAddCreateErrorAttr(carDto, request);

        }
        return created;
    }

    private boolean tryCreateOrAddCreateErrorAttr(CarDto carDto, HttpServletRequest request) {
        if (carService.createCar(carDto)) {
            return true;
        } else {
            request.setAttribute("creationError", true);
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
