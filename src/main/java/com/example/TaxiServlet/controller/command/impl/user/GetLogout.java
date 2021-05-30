package com.example.TaxiServlet.controller.command.impl.user;

import com.example.TaxiServlet.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetLogout implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("logout" , true);
        return "login-page";
    }
}