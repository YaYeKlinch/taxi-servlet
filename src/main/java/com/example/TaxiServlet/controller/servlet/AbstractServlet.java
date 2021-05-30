package com.example.TaxiServlet.controller.servlet;

import com.example.TaxiServlet.controller.command.Command;
import com.example.TaxiServlet.controller.command.PostCommand;
import com.example.TaxiServlet.controller.command.impl.car.GetAddCarPage;
import com.example.TaxiServlet.controller.command.impl.car.GetCarList;
import com.example.TaxiServlet.controller.command.impl.car.GetChangeCarActivity;
import com.example.TaxiServlet.controller.command.impl.car.PostAddCar;
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

        urlToPostCommand.put("/login-page" , new PostLogin());
        urlToPostCommand.put("/registration" , new PostRegister());
        urlToPostCommand.put("/add-car" , new PostAddCar());

    }

}
