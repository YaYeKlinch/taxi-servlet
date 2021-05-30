package com.example.TaxiServlet.controller.command.impl.valdators;

import com.example.TaxiServlet.entity.dto.CarDto;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

public class CarValidator {
    public static boolean validateCar(HttpServletRequest request, CarDto carDto){
        boolean isNameEmpty = checkNotEmpty(carDto.getName() , "nameEmpty" , request);
        boolean isPhotoEmpty = checkNotEmpty(carDto.getPhoto() , "photoEmpty", request);
        boolean isCapacityEmpty = checkNotEmpty(carDto.getCapacity(),"capacityEmpty" , request);
        boolean isCapacityCorrect = checkCapacityCorrect(carDto.getCapacity(), "capacityIncorrect", request);
        return isCapacityCorrect && isCapacityEmpty && isNameEmpty && isPhotoEmpty;
    }
    private static boolean  checkNotEmpty(String param, String emptyAttribute, ServletRequest request){
        if(isNull(param) || param.isEmpty()){
            request.setAttribute(emptyAttribute,true);
            return false;
        }
        return true;
    }
    private static boolean  checkNotEmpty(Integer param, String emptyAttribute, ServletRequest request ){
        if(param == null){
            request.setAttribute(emptyAttribute,true);
            return false;
        }
        return  true;
    }
    private static boolean checkCapacityCorrect(Integer capacity , String attribute , ServletRequest request){
        if(capacity<1){

            request.setAttribute(attribute,true);
            return false;
        }
        return  true;
    }
}
