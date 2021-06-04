package com.example.TaxiServlet.controller.servlet;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.RestCommand;
import com.example.TaxiServlet.controller.command.impl.car.*;
import com.example.TaxiServlet.controller.command.impl.taxiOrder.*;
import com.example.TaxiServlet.controller.command.impl.user.*;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class AbstractServlet extends HttpServlet {

    protected Map<String, Command> urlToGetCommand = new HashMap<>();
    protected Map<String, PostCommand> urlToPostCommand=  new HashMap<>();
    protected Map<String , RestCommand> urlToRestCommand = new HashMap<>();

    @Override
    public void init(){
        urlToGetCommand.put("/taxi/login-page" , new GetLogin());
        urlToGetCommand.put("/taxi/registration" , new GetRegister());
        urlToGetCommand.put("/taxi/logout" , new GetLogout());
        urlToGetCommand.put("/taxi/cars" , new GetCarList());
        urlToGetCommand.put("/taxi/add-car" , new GetAddCarPage());
        urlToGetCommand.put("/taxi/cars/change-activity", new GetChangeCarActivity());
        urlToGetCommand.put("/taxi/active-cars",new GetActiveCarList());
        urlToGetCommand.put("/taxi/active-cars/change-status",new GetChangeCarStatus());
        urlToGetCommand.put("/taxi/active-cars/make-order", new GetMakeOrderPage());
        urlToGetCommand.put("/taxi/all-orders", new GetTaxiOrderList());
        urlToGetCommand.put("/taxi/user-orders", new GetUsersTaxiOrderList());
        urlToGetCommand.put("/taxi/cars/update-car", new GetUpdateCarPage());
        urlToGetCommand.put("/taxi/all-orders/statistics" , new GetStatisticsPage());
        urlToGetCommand.put("/taxi/find-cars" , new FindCarPage());

        urlToPostCommand.put("/taxi/login-page" , new PostLogin());
        urlToPostCommand.put("/taxi/logout" , new PostLogin());
        urlToPostCommand.put("/taxi/registration" , new PostRegister());
        urlToPostCommand.put("/taxi/add-car" , new PostAddCar());
        urlToPostCommand.put("/taxi/active-cars/make-order" , new PostMakeOrder());
        urlToPostCommand.put("/taxi/cars/update-car" , new PostCarUpdate());

        urlToRestCommand.put("/taxi/found-cars" , new RestFindCar());
    }

}
