package com.example.TaxiServlet.controller.command.impl.user;

import com.example.TaxiServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class GetLogin implements Command {

    private static final String URL = "/login.jsp";
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("logout" , true);
        return URL;
    }
}
