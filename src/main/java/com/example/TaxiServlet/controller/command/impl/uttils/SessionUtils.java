package com.example.TaxiServlet.controller.command.impl.uttils;

import com.example.TaxiServlet.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static User getUserId(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("LoggedUser");
        return user;
    }
}