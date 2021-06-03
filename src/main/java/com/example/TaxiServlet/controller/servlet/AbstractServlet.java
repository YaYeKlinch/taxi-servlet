package com.example.TaxiServlet.controller.servlet;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.impl.car.*;
import com.example.TaxiServlet.controller.command.impl.taxiOrder.GetMakeOrderPage;
import com.example.TaxiServlet.controller.command.impl.taxiOrder.GetTaxiOrderList;
import com.example.TaxiServlet.controller.command.impl.taxiOrder.GetUsersTaxiOrderList;
import com.example.TaxiServlet.controller.command.impl.taxiOrder.PostMakeOrder;
import com.example.TaxiServlet.controller.command.impl.user.*;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

public class AbstractServlet extends HttpServlet {

    protected Map<String, Command> urlToGetCommand = new HashMap<>();
    protected Map<String, PostCommand> urlToPostCommand=  new HashMap<>();

    @Override
    public void init(){
        urlToGetCommand.put("/login-page" , new GetLogin());
        urlToGetCommand.put("/registration" , new GetRegister());
        urlToGetCommand.put("/logout" , new GetLogout());
        urlToGetCommand.put("/cars" , new GetCarList());
        urlToGetCommand.put("/add-car" , new GetAddCarPage());
        urlToGetCommand.put("/cars/change-activity", new GetChangeCarActivity());
        urlToGetCommand.put("/active-cars",new GetActiveCarList());
        urlToGetCommand.put("/active-cars/change-status",new GetChangeCarStatus());
        urlToGetCommand.put("/active-cars/make-order", new GetMakeOrderPage());
        urlToGetCommand.put("/all-orders", new GetTaxiOrderList());
        urlToGetCommand.put("/user-orders", new GetUsersTaxiOrderList());
        urlToGetCommand.put("/cars/update-car", new GetUpdateCarPage());

        urlToPostCommand.put("/login-page" , new PostLogin());
        urlToPostCommand.put("/logout" , new PostLogin());
        urlToPostCommand.put("/registration" , new PostRegister());
        urlToPostCommand.put("/add-car" , new PostAddCar());
        urlToPostCommand.put("/active-cars/make-order" , new PostMakeOrder());
        urlToPostCommand.put("/cars/update-car" , new PostCarUpdate());

    }

}
