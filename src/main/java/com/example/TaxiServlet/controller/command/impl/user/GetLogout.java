package com.example.TaxiServlet.controller.command.impl.user;

import com.example.TaxiServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetLogout implements Command {
    private static final String URL =  "/taxi/login-page";
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("logout" , true);
        return URL;
    }
}