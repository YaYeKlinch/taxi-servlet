package com.example.TaxiServlet.controller.command.impl.valdators;

import com.example.TaxiServlet.entity.dto.TaxiOrderDto;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

public class TaxiOrderValidator {
    public static boolean validateOrder(HttpServletRequest request, TaxiOrderDto taxiOrderDto){
        boolean isDepartureEmpty = checkNotEmpty(taxiOrderDto.getDeparture(), "departureEmpty", request);
        boolean isArrivalEmpty = checkNotEmpty(taxiOrderDto.getArrival(), "arrivalEmpty", request);
        return isArrivalEmpty && isDepartureEmpty;
    }
    private static boolean  checkNotEmpty(String param, String emptyAttribute, ServletRequest request){
        if(isNull(param) || param.isEmpty()){
            request.setAttribute(emptyAttribute,true);
            return false;
        }
        return true;
    }
}
