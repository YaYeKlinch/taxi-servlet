package com.example.TaxiServlet.controller.command.impl.taxiOrder;

import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.impl.valdators.TaxiOrderValidator;
import com.example.TaxiServlet.entity.User;
import com.example.TaxiServlet.entity.dto.CarDto;
import com.example.TaxiServlet.entity.dto.TaxiOrderDto;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderService;
import com.example.TaxiServlet.service.taxiOrder.TaxiOrderServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static com.example.TaxiServlet.controller.command.impl.uttils.SessionUtils.getUserId;

public class PostMakeOrder implements PostCommand {
    TaxiOrderService taxiOrderService= new TaxiOrderServiceImpl();
    private boolean allMatches;
    private static final String URL_ERROR = "/makeOrder.jsp";
    private static final String URL_SUCCESS = "/cars";
    @Override
    public String execute(HttpServletRequest request) {
        User user = getUserId(request);
        long carId = Long.parseLong(request.getParameter("car_id"));
        TaxiOrderDto taxiOrderDto = getValidateDto(request);
        boolean created = createAndAddErrorAttributes(taxiOrderDto, request,  carId, user.getId());
        if (created) {
            request.setAttribute("values", taxiOrderDto);
            return URL_SUCCESS;
        }
        return URL_ERROR;
    }
    private boolean createAndAddErrorAttributes(TaxiOrderDto taxiOrderDto, HttpServletRequest request , long carId , long userId) {
        boolean created = false;
        if (allMatches) {
            created = tryCreateOrAddCreateErrorAttr(taxiOrderDto, request,  carId, userId);

        }
        return created;
    }
    private boolean tryCreateOrAddCreateErrorAttr(TaxiOrderDto taxiOrderDto, HttpServletRequest request , long carId , long userId) {
        if (taxiOrderService.createTaxiOrder(taxiOrderDto, userId , carId)) {
            return true;
        } else {
            request.setAttribute("creationError", true);
            return false;
        }
    }
    private TaxiOrderDto  getValidateDto(HttpServletRequest request){
        TaxiOrderDto taxiOrderDto = null;
        try {
            taxiOrderDto = new TaxiOrderDto(request.getParameter("departure"),
                    request.getParameter("arrival"));
            allMatches = TaxiOrderValidator.validateOrder(request,taxiOrderDto);
        }catch (IllegalArgumentException  ex) {
            allMatches = false;
        }
        return taxiOrderDto;
    }
    @Override
    public boolean isError(String url) {
        return url.equals(URL_ERROR);
    }
}
